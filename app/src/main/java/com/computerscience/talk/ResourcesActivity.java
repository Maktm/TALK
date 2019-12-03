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

public class ResourcesActivity extends Activity {
    String mood = "Sad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
    }

    public void openArticle(View view)
    {
        startActivity(new Intent(ResourcesActivity.this, Article1.class));
    }

}
