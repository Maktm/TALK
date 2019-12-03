package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.view.View;
import android.util.Log;

import java.util.ArrayList;

public class ResourcesActivity extends Activity implements OnItemClickListener {
    String mood = "Sad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        ListView listView = (ListView) findViewById(R.id.listViewR);
        listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        Intent inten = new Intent();
        inten.setClass(ResourcesActivity.this, ListItemDetail.class);
        inten.putExtra("position", position);
        inten.putExtra("id", id);
        if(mood.equals("sad") && id == 0) {
            startActivity(new Intent(ResourcesActivity.this, Article1.class));
        }
        else if (mood.equals("sad") && id == 1) {
            startActivity(new Intent(ResourcesActivity.this, Fitness1.class));
        }
        else if (mood.equals("sad") && id == 2) {
            startActivity(new Intent(ResourcesActivity.this, VideoActivity.class));
        }
        else if (mood.equals("anxious") && id == 0) {
            startActivity(new Intent(ResourcesActivity.this, Article2.class));
        }
        else if (mood.equals("anxious") && id == 1) {
            startActivity(new Intent(ResourcesActivity.this, Fitness1.class));
        }
        else if (mood.equals("anxious") && id == 2) {
            startActivity(new Intent(ResourcesActivity.this, VideoActivity.class));
        }
        else if (mood.equals("angry") && id == 0) {
            startActivity(new Intent(ResourcesActivity.this, Article3.class));
        }
        else if (mood.equals("angry") && id == 1) {
            startActivity(new Intent(ResourcesActivity.this, Fitness2.class));
        }
        else if (mood.equals("angry") && id == 2) {
            startActivity(new Intent(ResourcesActivity.this, VideoActivity.class));
        }
        else if (mood.equals("happy") && id == 0) {
            startActivity(new Intent(ResourcesActivity.this, Article4.class));
        }
        else if (mood.equals("happy") && id == 1) {
            startActivity(new Intent(ResourcesActivity.this, Fitness2.class));
        }
        else {
            startActivity(new Intent(ResourcesActivity.this, VideoActivity.class));
        }
}

}
