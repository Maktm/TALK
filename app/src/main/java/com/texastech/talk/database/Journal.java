package com.texastech.talk.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Journal {
    @PrimaryKey(autoGenerate = true)
    int jid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "body")
    public String body;

    public Journal(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
