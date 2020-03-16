package com.comp231.mypam.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "categories.db";

    public static final int DB_FILE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB_FILE_NAME, null, DB_FILE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriesTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if necessary, save the existing data before drop
        db.execSQL(CategoriesTable.SQL_DELETE);
        db.execSQL(CategoriesTable.SQL_CREATE);
    }
}