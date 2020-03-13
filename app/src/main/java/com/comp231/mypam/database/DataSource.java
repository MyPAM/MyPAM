package com.comp231.mypam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.comp231.mypam.model.Category;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public DataSource(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void open() {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    //add a single item to database
    public Category createItem(Category item) {
        ContentValues values = item.toValues();
        mDatabase.insert(CategoriesTable.TABLE_ITEMS, null, values);
        return item;
    }

    public long getDataItemsCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, CategoriesTable.TABLE_ITEMS);
    }

    public void seedDataBase(List<Category> categoryItemList) {
        long numItems = getDataItemsCount();
        if (numItems == 0) {
            for (Category item : categoryItemList) {
                try {
                    createItem(item);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Category> getAllItems() {
        List<Category> categoryItems = new ArrayList<>();
        Log.i("reading database ", "cheguei");
        Cursor cursor = mDatabase.query(CategoriesTable.TABLE_ITEMS, CategoriesTable.ALL_COLUMNS, null, null, null, null, null);
        Log.i("reading database ", cursor.toString());
        while (cursor.moveToNext()) {
            Log.i("reading database ", "inside");
            Category item = new Category();
            item.setCategoryId(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_ID)));
            item.setCategoryName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
            item.setCategoryStatus(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_STATUS)));
            item.setCategoryType(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_TYPE)));
            categoryItems.add(item);
        }
        return categoryItems;
    }
}
