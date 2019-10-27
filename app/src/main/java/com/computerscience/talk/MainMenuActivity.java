package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        configuretoJournalButton();
        configuretoResourcesButton();
        configuretoGraphButton();
    }
    
    private void configuretoJournalButton() {
        CardView toJournal = (CardView) findViewById(R.id.toJournal);
        toJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, JournalActivity.class));
            }
        });
    }
    
    private void configuretoResourcesButton() {
        CardView toResources = (CardView) findViewById(R.id.toResources);
        toResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, ResourcesActivity.class));
            }
        });
    }
    
    private void configuretoGraphButton() {
        CardView toGraph = (CardView) findViewById(R.id.toGraph);
        toGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, GraphActivity.class));
            }
        });
    }
}
