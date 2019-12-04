package com.texastech.talk;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
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
import com.texastech.talk.intro.IntroActivity;
import com.texastech.talk.notification.AlarmReceiver;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * This is the core, single activity that runs throughout the lifetime of
     * the application. The rest of the UI consists of fragments embedded
     * into the UI for this activity using the Navigation component or
     * AlertDialogs and other Android components for requesting for input.
     */
    public static final String QUERY_MOOD_PARAMETER = "MainActivity.QueryMood";
    public static final String NOTIFICATION_CHANNEL_ID = "MainActivity.NotificationChan";

    private int mCurrentMood = 1;
    private int mCurrentMoodIntensity = 1;

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
         * This callback is called when the app is being restored after being paused.
         * This could be due to one of two reasons: the user opened the app from
         * the app icon or by clicking a notification from Talk.
         */
        super.onResume();

        // TODO: Remove, this is for demo purposes
        boolean resumingFromNotification = getIntent().getBooleanExtra(QUERY_MOOD_PARAMETER, false);
        if (resumingFromNotification) {
            showCurrentMoodDialog();
        } else {
            // Show notification if opening the app
            showNotification();
        }
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
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
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

    void showCurrentMoodDialog() {
        /**
         * Shows the user a dialog that asks them for their current mood then
         * stores the result inside of an instance variable.
         */
        CharSequence moods[] = {
                "Depressed", "Sad", "Angry", "Sacred", "Moderate", "Happy"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DarkAlertDialog);
        builder.setTitle("How are you feeling?");
        builder.setSingleChoiceItems(moods, 0, new MoodDialogChoiceListener());
        builder.setPositiveButton("Next", new MoodDialogListener());
        builder.show();
    }

    void showMoodIntensityDialog() {
        /**
         * Shows the dialog that asks the user the intensity of the mood
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DarkAlertDialog);
        SeekBar seekBar = new SeekBar(MainActivity.this);
        seekBar.setMax(4);
        seekBar.setOnSeekBarChangeListener(new MoodIntensityDialogSeekListener());

        builder.setTitle("How intense is this feeling?");
        builder.setView(seekBar);
        builder.setPositiveButton("Save", new MoodIntesityDialogListener());

        builder.show();
    }

    void saveMoodToDatabase() {
        /**
         * Saves the current mood state to the SQLite database.
         */
        AppDatabase database = AppDatabase.getDatabase(getApplicationContext());
        MoodDao moodDao = database.moodDao();

        // Get the last entered date
        List<Mood> allMoods = moodDao.getAll();
        int numberOfMoods = allMoods.size();
        int lastEnteredDate = 0;
        if (numberOfMoods > 0) {
             lastEnteredDate = allMoods.get(numberOfMoods - 1).date;
        }

        // Create the new Mood
        Mood currentMood = new Mood(lastEnteredDate + 1, mCurrentMood, mCurrentMoodIntensity);
        moodDao.insert(currentMood);

        // Ask the user (again)
        showNotification();
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
        if (alarmMgr != null) {
            alarmMgr.set(
                    AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() + 2 * 1000,
                    pendingIntent
            );
        }
    }

    class MoodDialogListener implements DialogInterface.OnClickListener {
        /**
         * Listeners for the click event when the user chooses the mood
         * that they're in and hits the submit button.
         */
        @Override
        public void onClick(DialogInterface dialog, int which) {
            showMoodIntensityDialog();
        }
    }

    class MoodDialogChoiceListener implements DialogInterface.OnClickListener {
        /**
         * Listens for the event in which the user chooses a different mood
         * from the multiple choice menu.
         */
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // Offset the choice because it goes from 0-5
            mCurrentMood = which + 1;
        }
    }

    class MoodIntesityDialogListener implements DialogInterface.OnClickListener {
        /**
         * Listens for the event in which the user chooses a mood intensity
         * using the SeekBar then hits the submit button.
         */
        @Override
        public void onClick(DialogInterface dialog, int which) {
            saveMoodToDatabase();
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
        }
    }

    class MoodIntensityDialogSeekListener implements SeekBar.OnSeekBarChangeListener {
        /**
         * Listens for updates on the SeekBar used to get the user's current mood.
         * The data is stored which is then used to save to the local SQLite database.
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // Progress goes from 0-5 but we use 1-6
            mCurrentMoodIntensity = progress + 1;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}