package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
//Hi world

public class MainActivity extends AppCompatActivity {

    String mood = "Sad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureSadButton();
        configureAnxiousButton();
        configureAngryButton();
        configureHappyButton();
    }
    
    private void configureSadButton() {
        Button sadButton = (Button) findViewById(R.id.sadButton);
        sadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
                 String mood = "Sad";
            }
        });
    }
    
    private void configureAnxiousButton() {
        Button anxiousButton = (Button) findViewById(R.id.anxiousButton);
        anxiousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
                String mood = "Anxious";
            }
        });
    }
    
    private void configureAngryButton() {
        Button angryButton = (Button) findViewById(R.id.angryButton);
        angryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
                String mood = "Angry";
            }
        });
    }
    
    private void configureHappyButton() {
        Button happyButton = (Button) findViewById(R.id.happyButton);
        happyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
                String mood = "Happy";
            }
        });
    }
}
