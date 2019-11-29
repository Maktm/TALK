package com.texastech.talk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.texastech.talk.intro.IntroActivity;
import com.texastech.talk.navigation.JournalFragment;
import com.texastech.talk.navigation.ResourcesFragment;
import com.texastech.talk.navigation.SettingsFragment;
import com.texastech.talk.navigation.StatisticsFragment;

public class MainActivity extends AppCompatActivity
        implements JournalFragment.OnFragmentInteractionListener,
            ResourcesFragment.OnFragmentInteractionListener,
            StatisticsFragment.OnFragmentInteractionListener,
            SettingsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPrefs.getBoolean(IntroActivity.LAUNCHED_APP_BEFORE, false)) {
            Intent introIntent = new Intent(this, IntroActivity.class);
            startActivity(introIntent);
        }

        /**
         * The following code links the BottomNavigationView to the NavController that
         * controls the NavHost displaying all the top-level activties. It works by
         * matching the IDs from the <menu> to the IDs in the navigation definition so
         * those IDs have to be consistent.
         */
        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavView, navController);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        /**
         * Required to run. Please do not remove.
         */
    }
}
