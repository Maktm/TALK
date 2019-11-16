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

    /*String mood;
    ResourcesActivity() {}
    ResourcesActivity(String m) {
        mood = m;
    }  //Keeps track of which mood the user is in */

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
        if(id == 0) {
            startActivity(new Intent(ResourcesActivity.this, Article1.class));
        }
        else if (id == 1) {
            startActivity(new Intent(ResourcesActivity.this, Fitness1.class));
        }
        else if (id == 2) {
            startActivity(new Intent(ResourcesActivity.this, VideoActivity.class));
        }
}

}
