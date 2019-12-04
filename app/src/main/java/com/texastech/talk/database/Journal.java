package com.texastech.talk.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * TODO: Add conversion class for the dates if necessary. You can
 *  as a UNIX timestamp then convert to/from the normal date/time format.
 */

@Entity
public class Journal {
    /**
     * This is the lower-level table to store the information regarding the resources
     * available in the app.
     *
     * The lower-level table looks like the following:
     *
     * -------------------------------------------------
     * | ID | Date | Text               | Title
     * -------------------------------------------------
     * | 0  | 2131 | "This article..."  | "Stress: a.."|
     * | 1  | 2339 | "Stress is ..."    | "Title: ..." |
     * | .. | ...  | ...                |   ...
     * -------------------------------------------------
     */
    @PrimaryKey(autoGenerate = true)
    int jid;

    @ColumnInfo(name = "date")
    public int date;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "title")
    public String title;

    public Journal(int date,String text, String journal) {
        this.date = date;
        this.text = text;
        this.title= title;
    }
}
