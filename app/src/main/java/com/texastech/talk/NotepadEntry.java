package com.texastech.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.texastech.talk.database.AppDatabase;
import com.texastech.talk.database.Journal;
import com.texastech.talk.database.JournalDao;

public class NotepadEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_entry);

        Button saveBtn = findViewById(R.id.save_entry);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText titleEditText = findViewById(R.id.entry_title);
                String title = titleEditText.getText().toString();

                EditText bodyEditText= findViewById(R.id.entry_body);
                String body = bodyEditText.getText().toString();

                AppDatabase database = AppDatabase.getDatabase(NotepadEntry.this);
                JournalDao journalDao = database.journalDao();

                Journal journal = new Journal(title, body);
                journalDao.insert(journal);

                finish();
            }
        });
    }
}
