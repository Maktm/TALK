package com.texastech.talk.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * TODO: Add conversion class for the dates if necessary. You can
 *  as a UNIX timestamp then convert to/from the normal date/time format.
 */

@Entity
public class Resources {
    /**
     * This is the lower-level table to store the information regarding the resources
     * available in the app.
     *
     * The lower-level table looks like the following:
     *
     * -----------------------------------
     * | ID | Mood | Title               |
     * -----------------------------------
     * | 0  | 2    | "How to..."         |
     * | 1  | 3    | "What to do..."     |
     * | .. | ...  | ...                 |
     * -----------------------------------
     */
    @PrimaryKey(autoGenerate = true)
    int mid;

    @ColumnInfo(name = "mood")
    public int mood;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "link")
    public String link;

    @ColumnInfo(name = "img")
    public String img;

    public Resources(int mood, String title, String content, String link, String img) {
        this.mood = mood;
        this.title = title;
        this.content = content;
        this.link = link;
        this.img = img;
    }
}
