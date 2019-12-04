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

    public void openArticle1(View view)
    {
        startActivity(new Intent(ResourcesActivity.this, Article1.class));
    }

    public void openArticle2(View view)
    {
        startActivity(new Intent(ResourcesActivity.this, Article2.class));
    }

    public void openArticle3(View view)
    {
        startActivity(new Intent(ResourcesActivity.this, Article3.class));
    }

    public void openArticle4(View view)
    {
        startActivity(new Intent(ResourcesActivity.this, Article4.class));
    }

    public void openVideo(View view) {
        startActivity(new Intent(ResourcesActivity.this, VideoActivity.class));
    }

    public void openFit1(View view) {
        startActivity(new Intent(ResourcesActivity.this, Fitness1.class));
    }

    public void openFit2(View view)
    {
        startActivity(new Intent(ResourcesActivity.this, Fitness2.class));
    }

}
