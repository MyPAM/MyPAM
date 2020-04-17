// Accunts table definition
package com.comp231.mypam.database;

public class AccountsTable {
    public static final String TABLE_ACCOUNT_ITEMS = "accounts";
    public static final String COLUMN_ACCOUNTID = "accountId";
    public static final String COLUMN_ACCOUNTNAME = "accountName";
    public static final String COLUMN_ACCOUNTDESC = "accountDescription";
    public static final String COLUMN_ACCOUNTTYPE = "accountType";

    public static final String[] ALL_COLUMNS_ACCOUNT = {COLUMN_ACCOUNTID, COLUMN_ACCOUNTNAME, COLUMN_ACCOUNTDESC, COLUMN_ACCOUNTTYPE};
    public static final String[] UPDATE_COLUMNS_ACCOUNT = {COLUMN_ACCOUNTNAME, COLUMN_ACCOUNTDESC, COLUMN_ACCOUNTTYPE};
    public static final String SQL_CREATE_ACCOUNT =
            "CREATE TABLE " + TABLE_ACCOUNT_ITEMS + "(" +
                    COLUMN_ACCOUNTID + " TEXT PRIMARY KEY, " +
                    COLUMN_ACCOUNTNAME + " TEXT," +
                    COLUMN_ACCOUNTDESC + " TEXT," +
                    COLUMN_ACCOUNTTYPE + " TEXT" + ");";

    public static final String SQL_DELETE_ACCOUNT =
            "DROP TABLE " + TABLE_ACCOUNT_ITEMS;
}
