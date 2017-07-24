package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

    /**
     * Database helper for Habit Tracker app. Manages database creation and version management.
     */
    public class HabitDbHelper extends SQLiteOpenHelper {

        public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

        /** Name of the database file */
        private static final String DATABASE_NAME = "habit_tracker.db";

        /**
         * Database version. If the database schema is changed, the database version must be incremented.
         */
        private static final int DATABASE_VERSION = 1;

        /**
         * Constructs a new instance of {@link HabitDbHelper}.
         *
         * @param context of the app
         */
        public HabitDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * This is called when the database is created for the first time.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            // Create a String that contains the SQL statement to create the Habit Tracker table
            String SQL_CREATE_HABIT_TRACKER_TABLE =  "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                    + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                    + HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL, "
                    + HabitEntry.COLUMN_EXERCISE_MINUTES + " INTEGER NOT NULL DEFAULT 0 "
                    + HabitEntry.COLUMN_HABIT_RESULT + " TEXT NOT NULL, );";

            // Execute the SQL statement
            db.execSQL(SQL_CREATE_HABIT_TRACKER_TABLE);
        }

        /**
         * This is called when the database needs to be upgraded.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // The database is still at version 1, so there's nothing to do here.
        }
}
