package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainMenuActivity extends AppCompatActivity {

    String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        configuretoJournalButton();
        configuretoResourcesButton();
        configuretoGraphButton();
    }
    
    private void configuretoJournalButton() {
        Button toJournal = (Button) findViewById(R.id.toJournal);
        toJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, JournalActivity.class));
            }
        });
    }
    
    private void configuretoResourcesButton() {
        Button toResources = (Button) findViewById(R.id.toResources);
        toResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResourcesActivity resourcesactivity = new ResourcesActivity();
                startActivity(new Intent(MainMenuActivity.this, ResourcesActivity.class));
            }
        });
    }
    
    private void configuretoGraphButton() {
        Button toGraph = (Button) findViewById(R.id.toGraph);
        toGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, GraphActivity.class));
            }
        });
    }
}
