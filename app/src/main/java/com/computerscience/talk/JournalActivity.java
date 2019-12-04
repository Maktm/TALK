package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class JournalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
    }

    public void makeEntry(View view)
    {
        //Does stuff to make the entry
    }

    public void saveEntry(View view)
    {
        /**There was an attempt.
         * This is code for when the charlotte-resources branch merges w the database branch:
         * date =
         * text = "Dear diary..."; (Take the text as input for the function, ideally
         * title = "Hello World"; (Also take this as input)
         *
         * Journal journal = new Journal(date, text, title);
         * JournalDao journalDao = database.journalDao;
         * journalDao.insert(journal);
         */
    }
}
