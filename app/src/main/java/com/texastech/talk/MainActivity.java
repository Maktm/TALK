package com.texastech.talk;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.texastech.talk.database.AppDatabase;
import com.texastech.talk.database.Mood;
import com.texastech.talk.database.MoodDao;
import com.texastech.talk.database.Resources;
import com.texastech.talk.database.ResourcesDao;
import com.texastech.talk.intro.IntroActivity;
import com.texastech.talk.navigation.JournalFragment;
import com.texastech.talk.navigation.ResourcesFragment;
import com.texastech.talk.navigation.SettingsFragment;
import com.texastech.talk.navigation.StatisticsFragment;
import com.texastech.talk.notification.AlarmReceiver;

import java.util.List;

public class MainActivity extends AppCompatActivity implements JournalFragment.OnFragmentInteractionListener,
        ResourcesFragment.OnFragmentInteractionListener,
        StatisticsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    // Temporarily used to store the current mood
    // before saving it to the database.
    int mCurrentMood = 0;
    int mCurrentMoodLevel = 0;
    int mSeverityLevel = 0;
    //Temporary values for creating resource database
    String titles = "";
    String contents = "";
    String links = "";
    String imgs = "";

    /**
     * This is the core, single activity that runs throughout the lifetime of
     * the application. The rest of the UI consists of fragments embedded
     * into the UI for this activity using the Navigation component or
     * AlertDialogs and other Android components for requesting for input.
     */
    public static final String QUERY_MOOD_PARAMETER = "MainActivity.QueryMood";
    public static final String NOTIFICATION_CHANNEL_ID = "MainActivity.NotificationChan";

    class MoodDialogListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            /**
             * Listens for the event in which the chooses a radio button for
             * their mood then hits 'Next' to select the level of their mood.
             */
            dialog.dismiss();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DarkAlertDialog);
            SeekBar seekBar = new SeekBar(MainActivity.this);
            seekBar.setMax(4);
            seekBar.setOnSeekBarChangeListener(new MoodLevelOptionListener());

            builder.setTitle("How intense is this feeling?");
            builder.setView(seekBar);
            builder.setCancelable(false);
            builder.setPositiveButton("Save", new MoodLevelListener());

            builder.show();
        }
    }

    class MoodDialogOptionListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mCurrentMood = which + 1;
        }
    }

    class MoodLevelListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            /**
             * Called when the user enters the severity of their mood. This should
             * save the user's current mood to the database then show the message
             * saying that the information was saved.
             */
            saveCurrentMood();
            Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    class MoodLevelOptionListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mCurrentMoodLevel = progress + 1;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**
         * Runs when the activity is first launched. For more information about
         * the Android activity lifecycle, please refer to https://bit.ly/2q7i3eK.
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPrefs.getBoolean(IntroActivity.LAUNCHED_APP_BEFORE, false)) {
            // The user is launching the app for the first time
            Intent intent = new Intent(this, IntroActivity.class);
            finish();
            startActivity(intent);
        }

        registerNotificationChannel();
        setupBottomNavigation();

    }

    @Override
    protected void onResume() {
        /**
         * Called whenever the app is opened when it was not open before or it
         * was paused and is being restored. In either case, it could also be the
         * result of hitting the notification so we check to see if that's the
         * case and, if so, prompt the user with an AlertDialog.
         */
        super.onResume();

        boolean resumeFromNotification = getIntent().getBooleanExtra(
                QUERY_MOOD_PARAMETER, false);
        if (resumeFromNotification) {
            promptForCurrentMood();
        } else {
            // The user is just opening the app so for demo purposes, show
            // them a notification that they can click.
            // TODO: Remove.
            showNotification();
        }
    }

    void showNotification() {
        /**
         * Sets the alarm to display a notification in the notification bar asking the user to hit
         * the notification so that they get prompted to enter their mood. The notification is
         * shown 3 seconds after requested for demo purposes.
         * TODO: Remove.
         */
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmMgr.set(
                AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 3 * 1000, pendingIntent
        );
    }

    void promptForCurrentMood() {
        /**
         * Shows the user a series of AlertDialog popups to ask them what their
         * current mood is then stores the gathered information into the SQLite database.
         */
        CharSequence items[] = {
                "Happy", "Sad", "Scared", "Disgusted", "Angry", "Depressed"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DarkAlertDialog);
        builder.setTitle("How are you feeling?");
        builder.setSingleChoiceItems(items, 0, new MoodDialogOptionListener());
        builder.setPositiveButton("Next", new MoodDialogListener());
        builder.setCancelable(false);
        builder.show();
    }

    void registerNotificationChannel() {
        /**
         * Registers a notification channel which is required to post notifications
         * to the user. This is done repeatedly whenever the app is started but
         * there is not problem with calling .createNotificationChannel repeatedly.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID, "DailyNotification", importance);
            channel.setDescription("Talk.Notifications");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    void setupBottomNavigation() {
        /**
         * Links the BottomNavigationView element to the NavController that controls
         * the NavHost containing all the top-level UI fragments. The NavController
         * is then used to switch between UIs/fragments.
         */
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    void saveCurrentMood() {
        /**
         * Saves the user's current mood to the database. It should only be called
         * after the user just got done entering in their severity level.
         */
        Mood currentMood = new Mood(mCurrentMood, mCurrentMoodLevel, mSeverityLevel);

        AppDatabase database = AppDatabase.getDatabase(getApplicationContext());
        MoodDao moodDao = database.moodDao();
        moodDao.insert(currentMood);

        /**
         * Create the Resources database and insert the user's current mood
         */
        Resources resource = new Resources(mCurrentMood, titles, contents, links, imgs);
        ResourcesDao resourcesDao = database.resourcesDao();
        resourcesDao.insert(mCurrentMood);

        // Log all the data in the database to make sure it's correct
        List<Mood> allMoods = moodDao.getAll();
        Log.d("Database", String.format("The database contains %d entries", allMoods.size()));
        for (Mood mood : allMoods) {
            Log.d("Database", String.format("Row: Date=%d, Mood=%d", mood.date, mood.value));
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        /**
         * Danger: This is required to include the fragments used by the Android
         * Navigation component (e.g. JournalFragment). It may not have any code
         * but you can leave it alone so that the app doesn't crash.
         */
    }
}