package com.texastech.talk.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface
JournalDao {
    /**
     * A Data Access Object (Dao) is the bridge between the
     * user attempting to interact with the lower-level
     * database and the raw database. The access object
     * allows you to perform operations, retrieve data etc.
     */
    @Query("SELECT * FROM journal")
    List<Journal> getAll();

    @Insert
    void insert(Journal journal);

    @Insert
    void insertAll(Journal... journals);

    @Delete
    void delete(Journal journal);

    @Insert
    void insert(int tjournal);
}