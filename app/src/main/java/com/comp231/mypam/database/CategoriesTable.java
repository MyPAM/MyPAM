package com.comp231.mypam.database;

public class CategoriesTable {
    public static final String TABLE_ITEMS = "categories";
    public static final String COLUMN_ID = "categoryId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TYPE = "type";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_STATUS, COLUMN_TYPE};
    public static final String[] UPDATE_COLUMNS = {COLUMN_NAME, COLUMN_STATUS, COLUMN_TYPE};
    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_STATUS + " TEXT," +
                    COLUMN_TYPE + " TEXT" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;

}
