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
        Cursor cursor = mDatabase.query(CategoriesTable.TABLE_ITEMS, CategoriesTable.ALL_COLUMNS, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Category item = new Category();
            item.setCategoryId(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_ID)));
            item.setCategoryName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
            item.setCategoryStatus(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_STATUS)));
            item.setCategoryType(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_TYPE)));
            categoryItems.add(item);
        }
        return categoryItems;
    }
    public Category createCategory(Category category) {
        ContentValues values = category.toValues();
        mDatabase.insert(CategoriesTable.TABLE_ITEMS,null,values);
        return category;
    }
    public Category updateCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put(CategoriesTable.COLUMN_NAME,category.getCategoryName());
        values.put(CategoriesTable.COLUMN_STATUS,category.getCategoryStatus());
        values.put(CategoriesTable.COLUMN_TYPE,category.getCategoryType());

        String [] args = {category.getCategoryId()};

        mDatabase.update(CategoriesTable.TABLE_ITEMS,values,CategoriesTable.COLUMN_ID + "=?", args);
        return category;
    }

    public String deleteCategory(String categoryId) {

        String [] args = {categoryId};
        String result = getCategory(categoryId).getCategoryName();

        mDatabase.delete(CategoriesTable.TABLE_ITEMS,CategoriesTable.COLUMN_ID + "=?", args);

        return result;

    }


    public Category getCategory(String categoryId) {

        Category category = new Category();

        String [] search = {categoryId};
        Cursor cursor = mDatabase.query(CategoriesTable.TABLE_ITEMS,CategoriesTable.ALL_COLUMNS,CategoriesTable.COLUMN_ID + "=?", search,null,null,CategoriesTable.COLUMN_NAME);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            category.setCategoryId(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_ID)));
            category.setCategoryName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
            category.setCategoryStatus(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_STATUS)));
            category.setCategoryType(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_TYPE)));
        }
        return category;

    }
}
