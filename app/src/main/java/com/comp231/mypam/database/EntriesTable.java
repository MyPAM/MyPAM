package com.comp231.mypam.database;

public class EntriesTable {
    public static final String TABLE_ENTRY_ITEMS = "entries";
    public static final String COLUMN_ENTRYTID = "entryId";
    public static final String COLUMN_ACCOUNTID = "entryId";
    public static final String COLUMN_ENTRYDESC = "entryDescription";
    public static final String COLUMN_ENTRYTYPE = "entryType";
    public static final String COLUMN_ENTRYAMOUNT = "entryType";

    public static final String[] ALL_COLUMNS_ENTRY = {COLUMN_ENTRYTID, COLUMN_ACCOUNTID, COLUMN_ENTRYDESC,COLUMN_ENTRYTYPE,COLUMN_ENTRYAMOUNT};
    public static final String[] UPDATE_COLUMNS_ENTRY = {COLUMN_ENTRYDESC, COLUMN_ENTRYTYPE,COLUMN_ENTRYAMOUNT};
    public static final String SQL_CREATE_ENTRY =
            "CREATE TABLE " + TABLE_ENTRY_ITEMS + "(" +
                    COLUMN_ENTRYTID +  " TEXT PRIMARY KEY, " +
                    COLUMN_ACCOUNTID + " TEXT, " +
                    COLUMN_ENTRYDESC + "TEXT," +
                    COLUMN_ENTRYTYPE + " TEXT," +
                    COLUMN_ENTRYAMOUNT + " REAL NOT NULL" + ");";

    public static final String SQL_DELETE_ENTRY =
            "DROP TABLE " + TABLE_ENTRY_ITEMS;
}
