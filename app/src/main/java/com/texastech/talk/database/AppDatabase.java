package com.texastech.talk.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Mood.class, Resources.class, Journal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * The app's "single source of truth" is this database used
     * across all the activities and fragments. This database
     * provides Daos to all the different tables used by the
     * app with both read/write abilities.
     */
    private static AppDatabase mSingleInstance;
    public static final String DATABASE_NAME = "database";

    public abstract MoodDao moodDao();
    public abstract ResourcesDao resourcesDao();
    public abstract JournalDao journalDao();

    public static AppDatabase getDatabase(final Context context) {
        /**
         * Returns the single instance of the database that lives
         * across the lifetime of teh application. AppDatabase
         * objects are expensive so only one instance should exist.
         *
         * TODO: Disable the allowing on the main thread and create
         *  a separate class for asynchronous operations.
         */
        if (mSingleInstance == null) {
            synchronized (AppDatabase.class) {
                if (mSingleInstance == null) {
                    mSingleInstance = Room.databaseBuilder(
                            context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return mSingleInstance;
    }
}
