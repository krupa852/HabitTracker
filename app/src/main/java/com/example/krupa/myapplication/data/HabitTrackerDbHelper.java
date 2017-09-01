package com.example.krupa.myapplication.data;

/**
 * Created by krupa on 1/9/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HabitTrackerDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitTrackerDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "habittracker.db";

    private static final int DATABASE_VERSION = 1;

    public HabitTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitTrackerContract.HabitEntry.TABLE_NAME + " ("
                + HabitTrackerContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitTrackerContract.HabitEntry.COLUMN_HABIT_DESC + " TEXT NOT NULL, "
                + HabitTrackerContract.HabitEntry.COLUMN_HABIT_STATUS + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}