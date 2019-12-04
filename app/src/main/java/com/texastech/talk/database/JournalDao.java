package com.texastech.talk.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JournalDao {
    @Query("SELECT * FROM journal")
    List<Journal> getAll();

    @Insert
    void insert(Journal journal);

    @Insert
    void insertAll(Journal... journals);

    @Delete
    void delete(Journal journal);
}
