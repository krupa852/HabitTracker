package com.example.krupa.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.krupa.myapplication.data.HabitTrackerContract;
import com.example.krupa.myapplication.data.HabitTrackerDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitTrackerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitTrackerDbHelper(this);
    }

    public Cursor readDatabase(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
               HabitTrackerContract.HabitEntry._ID,
                HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitTrackerContract.HabitEntry.COLUMN_HABIT_DESC,
                HabitTrackerContract.HabitEntry.COLUMN_HABIT_STATUS
        };

        Cursor cursor = db.query(
                HabitTrackerContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    private void displayDatabaseInfo(Cursor cursor){
        TextView displayView = (TextView) findViewById(R.id.habit_text_view);

        try{
            displayView.setText("The table contains " + cursor.getCount() + " exercises.\n\n");
            displayView.append(HabitTrackerContract.HabitEntry._ID + " - " +
                    HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitTrackerContract.HabitEntry.COLUMN_HABIT_DESC + " - " +
                    HabitTrackerContract.HabitEntry.COLUMN_HABIT_STATUS + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME);
            int descColumnIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DESC);
            int statusColumnIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_HABIT_STATUS);

            while (cursor.moveToNext()){
                int curID = cursor.getInt(idColumnIndex);
                String curName = cursor.getString(nameColumnIndex);
                String curDesc = cursor.getString(descColumnIndex);
                int curStatus = cursor.getInt(statusColumnIndex);

                displayView.append(("\n" + curID + " - " +
                        curName + " - " +
                        curDesc + " - " +
                        curStatus));
            }
        } finally {
            cursor.close();
        }
    }

    private void insertHabitDetails(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME, "Yoga");
        values.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_DESC, "Astanga Yoga");
        values.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_STATUS, HabitTrackerContract.HabitEntry.HABIT_STATUS_DONE);

        long newRowId = db.insert(HabitTrackerContract.HabitEntry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.insert_dummy_data:
                insertHabitDetails();
                Cursor c = readDatabase();
                displayDatabaseInfo(c);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
