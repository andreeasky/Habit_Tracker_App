package com.example.android.habittracker.data;

import android.provider.BaseColumns;

public class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the Habit Tracker database table.
     * Each entry in the table represents a single Habit instance.
     */
    public static final class HabitEntry implements BaseColumns {

        /** Name of database table for habits */
        public final static String TABLE_NAME = "habit_tracker";

        /**
         * Name of the Habit.
         *
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_NAME ="name";

        /**
         * Date of the Habit event.
         *
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_DATE = "date";

        /**
         * Result of the Habit event.
         *
         * The only possible values are: GOAL ACHIEVED, GOAL NOT ACHIEVED
         *
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_RESULT = "result";

        /**
         * Possible values for the Result of the Habit event.
         */
        public static final int RESULT_UNKNOWN = 0;
        public static final int RESULT_GOAL_ACHIEVED = 1;
        public static final int RESULT_GOAL_NOT_ACHIEVED = 2;
    }

}
