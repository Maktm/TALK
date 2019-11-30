package com.texastech.talk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.texastech.talk.intro.IntroActivity;
import com.texastech.talk.navigation.JournalFragment;
import com.texastech.talk.navigation.ResourcesFragment;
import com.texastech.talk.navigation.SettingsFragment;
import com.texastech.talk.navigation.StatisticsFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements JournalFragment.OnFragmentInteractionListener,
            ResourcesFragment.OnFragmentInteractionListener,
            StatisticsFragment.OnFragmentInteractionListener,
            SettingsFragment.OnFragmentInteractionListener {

    private AlarmManager mAlarmMgr;
    private PendingIntent mAlarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPrefs.getBoolean(IntroActivity.LAUNCHED_APP_BEFORE, false)) {
            Intent introIntent = new Intent(this, IntroActivity.class);
            startActivity(introIntent);
        }

        // Register the notification channel
        createNotificationChannel();

        /**
         * The following code links the BottomNavigationView to the NavController that
         * controls the NavHost displaying all the top-level activties. It works by
         * matching the IDs from the <menu> to the IDs in the navigation definition so
         * those IDs have to be consistent.
         */
        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavView, navController);

        // Set up the alarm to fire off at 7:00 PM every day
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 19);

        mAlarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        mAlarmIntent = PendingIntent.getBroadcast(this, 0, intent,0);
//        mAlarmMgr.setInexactRepeating(
//                AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mAlarmIntent);
        mAlarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        5 * 1000, mAlarmIntent);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel
                    channel = new NotificationChannel(AlarmReceiver.CHANNEL_ID, "DailyNotification", importance);
            channel.setDescription("Notification for daily queries");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        /**
         * Required to run. Please do not remove.
         */
    }
}
