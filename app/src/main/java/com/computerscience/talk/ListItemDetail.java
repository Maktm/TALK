package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.TextView;

public class ListItemDetail extends Activity {

    String mood = "Sad";
    String[] myKeys;

    //ListItemDetail() {}
    //ListItemDetail(String m) {
        //mood = m;
    //}

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
    else {
        myKeys = getResources().getStringArray(R.array.sectionsAngry);
    }

    TextView myTextView = (TextView) findViewById(R.id.my_textview);
    myTextView.setText(myKeys[position]);


    }

}
