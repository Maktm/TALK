package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        Button sadButton = (Button) findViewById(R.id.sadButton);
        sadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
            }
        });
    }
    
    private void configureAnxiousButton() {
        Button anxiousButton = (Button) findViewById(R.id.anxiousButton);
        anxiousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
            }
        });
    }
    
    private void configureAngryButton() {
        Button angryButton = (Button) findViewById(R.id.angryButton);
        angryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
            }
        });
    }
    
    private void configureHappyButton() {
        Button happyButton = (Button) findViewById(R.id.happyButton);
        happyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
            }
        });
    }
}
