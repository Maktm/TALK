package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

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
        ImageView sadButton = (ImageView) findViewById(R.id.sadMoodBtn);
        sadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
            }
        });
    }
    
    private void configureAnxiousButton() {
        ImageView anxiousButton = (ImageView) findViewById(R.id.anxiousMoodBtn);
        anxiousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
            }
        });
    }
    
    private void configureAngryButton() {
        ImageView angryButton = (ImageView) findViewById(R.id.angryMoodBtn);
        angryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
            }
        });
    }
    
    private void configureHappyButton() {
        ImageView happyButton = (ImageView) findViewById(R.id.happyMoodBtn);
        happyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
            }
        });
    }
}
