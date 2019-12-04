package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class JournalActivity extends AppCompatActivity {
    String text;
    int date = 1234;
    String title = "Something here.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
    }

    public void makeEntry(View view)
    {
        //Does stuff to make the entry
    }

    EditText inputText = (EditText)findViewById(R.id.edit_text);

    public void saveEntry(View view)
    {
        String text = inputText.getText().toString();
        /**There was an attempt.
         * This is code for when the charlotte-resources branch merges w the database branch:
         * What should happen is that there is a space in journal that allows users to enter text. The text needs to be saved to variables that
         * are then passed to this function, where the values are taken and added to the database. I have no idea how to do any of this.
         * date = 4754564
         * text = inputText.getText().toString(); (Take the text as input)
         *
         * Journal journal = new Journal(date, text, title);
         * JournalDao journalDao = database.journalDao;
         * journalDao.insert(journal);
         */
    }
}
