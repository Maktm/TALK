package com.texastech.talk.intro;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.github.paolorotolo.appintro.AppIntro2;
import com.texastech.talk.MainActivity;
import com.texastech.talk.R;
import com.texastech.talk.notification.AlarmReceiver;

public class IntroActivity extends AppIntro2 {
    public static String LAUNCHED_APP_BEFORE = "IntroActivityInitialLaunch";

    /**
     * The IntroActivity is launched the first time the app is
     * installed and the user is introduced to everything. It
     * goes through a series of slides and requests for permission
     * before presenting the user with the usual default activity.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add slides
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide1));
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide2));
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide3));
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide4));
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide5));
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide6));

        // Configure AppIntro
        showStatusBar(false);
        showSkipButton(false);

        // Permissions
        askForPermissions(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 5);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        // Save the fact that the user has finished the intro
        SharedPreferences.Editor sharedPrefsEditor =
                PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPrefsEditor.putBoolean(LAUNCHED_APP_BEFORE, true);
        sharedPrefsEditor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(AlarmReceiver.ASK_MOOD_INTENT_PARAM, true);
        finish();
        startActivity(intent);
    }
}
