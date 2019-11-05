package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
            Intent intent = new Intent();
            intent.setClass(this, ListItemDetail.class);
            intent.putExtra("position", position);
            intent.putExtra("id", id);
            startActivity(intent);
}
