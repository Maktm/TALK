package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResourcesActivity extends AppCompatActivity {

    //Demo Array
    String[] demoResources = {"Activity 1", "Activity 2", "Activity 3", "Activity 4", "Activity 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        ArrayList<String> arrayOfStuff = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_resources, arrayOfStuff);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }
}
