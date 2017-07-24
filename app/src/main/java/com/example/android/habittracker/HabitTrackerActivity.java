package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittracker.data.HabitContract;
import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class HabitTrackerActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private HabitDbHelper habitDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        habitDbHelper = new HabitDbHelper(this);
    }

    /**
     * Helper method to insert hardcoded habit data into the database.
     */
    private void insertHabit() {
        // Gets the database in write mode
        SQLiteDatabase db = habitDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and the Strings are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Exercise at least 30 min per day");
        values.put(HabitEntry.COLUMN_HABIT_DATE, "24/07/2017");
        values.put(HabitEntry.COLUMN_HABIT_RESULT, "GOAL ACHIEVED");


        // Insert a new row for a new habit in the database, returning the ID of that new row.
        // The first argument for db.insert() is the Habit Tracker table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for the new habit.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    /**
     * Temporary helper method to return a Cursor object with information from the habit tracker daabase.
     */
    private Cursor createCursor() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = habitDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // will actually be used after this query.
        String[] projection = {
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DATE,
                HabitEntry.COLUMN_HABIT_RESULT};

        // Perform a query on the habits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,  // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                  // The sort order

        //Return the cursor that was created from the above query
        return cursor;

    }

    /**
     * Helper method to read data from the habit tracker database.
     */

    private void readData() {
        // Create a String to return the results of the query
        // Build the new String
        StringBuilder displayData = new StringBuilder();

        Cursor cursor = createCursor();

        try {
            // Create a header that looks like this:
            //
            // The Habit Tracker table contains <number of rows in Cursor> Habits.
            // name - date - result
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayData.append("The Habit Tracker table contains " + cursor.getCount() + " Habits.\n\n");
            displayData.append(HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_DATE + "\n" +
                    HabitEntry.COLUMN_HABIT_RESULT + " - ");

            // Figure out the index of each column
            int habitNameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);
            int resultColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_RESULT);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String currentHabitName = cursor.getString(habitNameColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentResult = cursor.getString(resultColumnIndex);
                // Display the values from each column of the current row in the cursor
                displayData.append(("\n" + currentHabitName + " - " +
                        currentDate + " - " +
                        currentResult));
                }
            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                cursor.close();
            }
        }
}




