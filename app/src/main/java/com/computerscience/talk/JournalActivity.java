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
         * What should happen is that there is a space in journal that allows users to enter text. The text needs to be saved to variables that
         * are then passed to this function, where the values are taken and added to the database. I have no idea how to do any of this.
         * date = 4754564
         * text = "Dear diary..."; (Take the text as input for the function, ideally
         * title = "Hello World"; (Also take this as input)
         *
         * Journal journal = new Journal(date, text, title);
         * JournalDao journalDao = database.journalDao;
         * journalDao.insert(journal);
         */
    }
}
