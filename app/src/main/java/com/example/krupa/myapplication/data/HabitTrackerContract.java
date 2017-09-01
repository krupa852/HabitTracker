package com.example.krupa.myapplication.data;

/**
 * Created by krupa on 1/9/17.
 */

import android.provider.BaseColumns;
public class HabitTrackerContract {
    private HabitTrackerContract(){}

    //Constant values for habit tracker database table

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habit";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_HABIT_NAME = "name";

        public final static String COLUMN_HABIT_DESC = "desc";

        public final static String COLUMN_HABIT_STATUS = "status";

        public static final int HABIT_STATUS_UNKNOWN = 0;

        public static final int HABIT_STATUS_DONE = 1;

        public static final int HABIT_STATUS_NOT_DONE = 2;
    }
}
