package com.texastech.talk.intro;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.github.paolorotolo.appintro.AppIntro2;
import com.texastech.talk.MainActivity;
import com.texastech.talk.R;

public class IntroActivity extends AppIntro2 {
    /**
     * Activity launched the first time the app is installed in order to
     * introduce the user to the application purpose/features. This activity
     * contains a series of slides that are implemented in layouts and asks
     * for permissions required to use certain features of the app.
     */
    public static final String LAUNCHED_APP_BEFORE = "IntroActivity.LaunchedAppBefore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide1));
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide2));
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide3));
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide4));
//        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide5));
        addSlide(SlideHostFragment.newInstance(R.layout.layout_intro_slide6));

        showSkipButton(false);
        showStatusBar(false);

/*        final int LOCATION_PERMISSION_SLIDE_INDEX = 5;
        askForPermissions(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
        }, LOCATION_PERMISSION_SLIDE_INDEX);*/
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        /**
         * Executed when the user clicks the button on the last slide
         * acknowledging that they have read the terms of use and
         * would like to exit the app introduction.
         */
        super.onDonePressed(currentFragment);

        setLaunchedAppBefore();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.QUERY_MOOD_PARAMETER, true);
        finish();
        startActivity(intent);
    }

    void setLaunchedAppBefore() {
        /**
         * Marks the user as having been introduced to the app. Therefore,
         * they will not have to go through the introduction in the future.
         */
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putBoolean(LAUNCHED_APP_BEFORE, true);
        editor.apply();
    }
}