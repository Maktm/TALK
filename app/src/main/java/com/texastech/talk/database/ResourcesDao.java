package com.texastech.talk.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ResourcesDao {
    /**
     * A Data Access Object (Dao) is the bridge between the
     * user attempting to interact with the lower-level
     * database and the raw database. The access object
     * allows you to perform operations, retrieve data etc.
     */
    @Query("SELECT * FROM resources")
    List<Resources> getAll();

    @Insert
    void insert(Resources resources);

    @Insert
    void insertAll(Resources... resources);

    @Delete
    void delete(Resources resource);
}