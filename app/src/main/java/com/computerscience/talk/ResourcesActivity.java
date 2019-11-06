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
        inten.setClass(this, ListItemDetail.class);
        inten.putExtra("position", position);
        inten.putExtra("id", id);
        //startActivity(inten);
    }

}
