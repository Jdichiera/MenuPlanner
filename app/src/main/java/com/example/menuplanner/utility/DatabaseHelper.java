package com.example.menuplanner.utility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.menuplanner.application.MenuPlanner;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;
    public DatabaseHelper(Context context) {
        super(context, MenuPlanner.DATABASE_NAME, null, MenuPlanner.DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }

        return instance;
    }

    public boolean validateLogin(String username, String password) {
        boolean isValid;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(MenuPlanner.USERS_TABLE, new String[]{"userName"},
                "userName = ? AND userPassword = ?", new String[]{username, password},
                null, null, null, null);

        isValid = (cursor != null) && (cursor.getCount() == 1);
        if (cursor != null) {
            cursor.close();
        }

        return isValid;
    }

    public void deleteSequence() {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("delete from sqlite_sequence");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
