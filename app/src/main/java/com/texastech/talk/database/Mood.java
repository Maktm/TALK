package com.texastech.talk.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * TODO: Add conversion class for the dates if necessary. You can
 *  as a UNIX timestamp then convert to/from the normal date/time format.
 */

@Entity
public class Mood {
    /**
     * This entity is an abstraction of the lower-level table
     * used to store the moods that a user goes through every day.
     * Each mood is stored as a value between 1-6.
     *
     * The lower-level table looks like the following:
     *
     * ---------------------
     * | ID | Date | Value |
     * ---------------------
     * | 0  | 2131 | 2     |
     * | 1  | 2339 | 4     |
     * | .. | ...  | ...   |
     * ---------------------
     */
    @PrimaryKey(autoGenerate = true)
    int mid;

    @ColumnInfo(name = "date")
    public int date;

    @ColumnInfo(name = "value")
    public int value;

    @ColumnInfo (name="severity_level")
    public int severityLevel;


    public Mood(int date, int value, int severityLevel) {
        this.date = date;
        this.value = value;
        this.severityLevel= severityLevel;
    }
}
