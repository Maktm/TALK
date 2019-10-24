package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class SliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        
        configuretoMainMenuButton();
    }
    
    private void configuretoMainMenuButton() {
        Button toMainMenu = (Button) findViewById(R.id.toMainMenu);
        toMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SliderActivity.this, MainMenuActivity.class));
            }
        });
    }
}
