package com.texastech.talk.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MoodDao {
    /**
     * A Data Access Object (Dao) is the bridge between the
     * user attempting to interact with the lower-level
     * database and the raw database. The access object
     * allows you to perform operations, retrieve data etc.
     */
    @Query("SELECT * FROM mood")
    List<Mood> getAll();

    @Insert
    void insert(Mood mood);

    @Insert
    void insertAll(Mood... moods);

    @Delete
    void delete(Mood mood);
}