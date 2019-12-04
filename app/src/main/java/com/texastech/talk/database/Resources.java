package com.texastech.talk.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Resources {
    /**
     * Entity used to store the resources that are suggested to the user
     * based on their past moods. The suggested resources are displayed
     * as CardView objects with a "LEARN MORE" button.
     *
     * The lower-level table looks like the following:
     *
     * --------------------------------------------
     * | rid | title | content | hyperlink | mood |
     * |-------------------------------------------
     * | 0   | depr  | this is | https://  | 1    |
     * | ... | ...   | ...     | ...       | ...  |
     * |-------------------------------------------
     */
    @PrimaryKey(autoGenerate = true)
    int rid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "hyperlink")
    public String hyperlink;

    @ColumnInfo(name = "mood")
    public int mood;

    public Resources(String title, String content, String hyperlink, int mood) {
       this.title = title;
       this.content = content;
       this.hyperlink = hyperlink;
       this.mood = mood;
    }
}
