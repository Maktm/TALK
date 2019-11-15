package com.csproject.talk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if first time running
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean(IntroActivity.COMPLETED_INTRO_REF_NAME, false)) {
            // Run the user through the tutorial
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        } else {
            // This is just for demo purposes (myself, not actual demo)
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        }
    }
}
