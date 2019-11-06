package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.TextView;

public class ListItemDetail extends Activity {

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listitem);

    Intent intent = getIntent();
    int position = intent.getIntExtra("position", 0);

    // Here we turn your string.xml in an array
    String[] myKeys = getResources().getStringArray(R.array.sections);

    TextView myTextView = (TextView) findViewById(R.id.my_textview);
    myTextView.setText(myKeys[position]);


    }

}
