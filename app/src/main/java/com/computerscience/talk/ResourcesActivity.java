package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class ResourcesActivity extends AppCompatActivity {

    //Demo Array
    String[] demoResources = {"Activity 1", "Activity 2", "Activity 3", "Activity 4", "Activity 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

       // ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_resources, demoResources);

        //ListView listView = (ListView) findViewByID(R.id.mobile_list);
        //listView.setAdapter(adapter);
    }

    private Object findViewByID(int mobile_list) {
        return true;
    }
}
