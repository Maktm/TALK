package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.TextView;

public class ListItemDetail extends Activity {

    String mood;
    String[] myKeys;

    ListItemDetail(String m) {
        mood = m;
    }

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listitem);

    Intent intent = getIntent();
    int position = intent.getIntExtra("position", 0);

    if (mood.equals("Sad")) {
        myKeys = getResources().getStringArray(R.array.sectionsSad);
    }
    else if (mood.equals("Anxious")) {
        myKeys = getResources().getStringArray(R.array.sectionsAnxious);
    }
    else if (mood.equals("Angry")) {
        myKeys = getResources().getStringArray(R.array.sectionsAngry);
    }
    else {
        myKeys = getResources().getStringArray(R.array.sectionsHappy);
    }

    TextView myTextView = (TextView) findViewById(R.id.my_textview);
    myTextView.setText(myKeys[position]);


    }

}
