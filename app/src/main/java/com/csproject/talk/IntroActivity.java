package com.csproject.talk;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.github.paolorotolo.appintro.AppIntro2;

public class IntroActivity extends AppIntro2 {
    public static String COMPLETED_INTRO_REF_NAME = "TALK_COMPLETED_INFO_REFERENCE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add the slides for the introduction
        addSlide(IntroSlide.newInstance(R.layout.intro_slide_1));
        addSlide(IntroSlide.newInstance(R.layout.intro_slide_2));
        addSlide(IntroSlide.newInstance(R.layout.intro_slide_3));
        addSlide(IntroSlide.newInstance(R.layout.intro_slide_4));
        addSlide(IntroSlide.newInstance(R.layout.intro_slide_5));
        addSlide(IntroSlide.newInstance(R.layout.intro_slide_6));

        // Configure the intro slides
        showSkipButton(false);
        showStatusBar(false);

        // Ask for permissions(location and app activity features)
        askForPermissions(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 5);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        // Save the fact that the user is done with the intro
        SharedPreferences.Editor sharedPreferencesEditor =
                PreferenceManager.getDefaultSharedPreferences(getWindow().getContext()).edit();
        sharedPreferencesEditor.putBoolean(COMPLETED_INTRO_REF_NAME, true);
        sharedPreferencesEditor.apply();

        // Show welcome message
        Context context = getApplicationContext();
        Toast.makeText(context, "Welcome to TALK!", Toast.LENGTH_LONG).show();

        // Go to main menu (TODO: might have to change this to redirect to some other activity)
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        // The new fragment is null on the last slide action
        if (newFragment == null)
            return;

        // Change the color of the notification bar according to the current slide's background color
        ColorDrawable fragmentBackground = (ColorDrawable)newFragment.getView().getBackground();
        if (fragmentBackground != null) {
            int color = fragmentBackground.getColor();
            Window window = getWindow();
            window.setStatusBarColor(color);
        }
    }
}
