// Database handler file
package com.comp231.mypam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.comp231.mypam.model.Account;
import com.comp231.mypam.model.Category;
import com.comp231.mypam.model.Entry;
import com.comp231.mypam.ui.entries.EntriesFragment;

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
    // count Categories saved
    public long getDataItemsCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, CategoriesTable.TABLE_ITEMS);
    }
    // Populate Db
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
    // Get list of all items in the table
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
    // Category creation handler
    public Category createCategory(Category category) {
        ContentValues values = category.toValues();
        mDatabase.insert(CategoriesTable.TABLE_ITEMS,null,values);
        return category;
    }
    // Category update handler
    public Category updateCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put(CategoriesTable.COLUMN_NAME,category.getCategoryName());
        values.put(CategoriesTable.COLUMN_STATUS,category.getCategoryStatus());
        values.put(CategoriesTable.COLUMN_TYPE,category.getCategoryType());

        String [] args = {category.getCategoryId()};

        mDatabase.update(CategoriesTable.TABLE_ITEMS,values,CategoriesTable.COLUMN_ID + "=?", args);
        return category;
    }
    // Category deletion handler
    public String deleteCategory(String categoryId) {

        String [] args = {categoryId};
        String result = getCategory(categoryId).getCategoryName();

        mDatabase.delete(CategoriesTable.TABLE_ITEMS,CategoriesTable.COLUMN_ID + "=?", args);

        return result;

    }
    // Single category data fetch handler
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
    //Search Category by Name
    public Category getCategoryByName(String categoryName) {

        Category category = new Category();

        String [] search = {categoryName};
        Cursor cursor = mDatabase.query(CategoriesTable.TABLE_ITEMS,CategoriesTable.ALL_COLUMNS,CategoriesTable.COLUMN_NAME + "=?", search,null,null,CategoriesTable.COLUMN_NAME);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            category.setCategoryId(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_ID)));
            category.setCategoryName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
            category.setCategoryStatus(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_STATUS)));
            category.setCategoryType(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_TYPE)));
        }
        return category;

    }

    //Accounts
    // Populate accounts
    public void seedDataBaseAccounts(List<Account> accountItemList) {
        long numItems = getAccountItemsCount();
        if (numItems == 0) {
            for (Account item : accountItemList) {
                try {
                    createItemAccount(item);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //add a single item to database
    public Account createItemAccount(Account item) {
        ContentValues values = item.toValues();
        mDatabase.insert(AccountsTable.TABLE_ACCOUNT_ITEMS, null, values);
        return item;
    }
    // Fetch accounts data
    public List<Account> getAllAccounts() {
        List<Account> accountItems = new ArrayList<>();
        Cursor cursor = mDatabase.query(AccountsTable.TABLE_ACCOUNT_ITEMS, AccountsTable.ALL_COLUMNS_ACCOUNT, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Account item = new Account();
            item.setAccountId(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTID)));
            item.setAccountName(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTNAME)));
            item.setAccountDescription(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTDESC)));
            item.setAccountType(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTTYPE)));
            accountItems.add(item);
        }
        return accountItems;
    }
    // Account update handler
    public Account updateAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put(AccountsTable.COLUMN_ACCOUNTNAME,account.getAccountName());
        values.put(AccountsTable.COLUMN_ACCOUNTDESC,account.getAccountDescription());
        values.put(AccountsTable.COLUMN_ACCOUNTTYPE,account.getAccountType());

        String [] args = {account.getAccountId()};

        mDatabase.update(AccountsTable.TABLE_ACCOUNT_ITEMS,values, AccountsTable.COLUMN_ACCOUNTID + "=?", args);
        return account;
    }
    // Account deletion handler
    public String deleteAccount(String accountId) {

        String [] args = {accountId};
        String result = getAccount(accountId).getAccountName();

        mDatabase.delete(AccountsTable.TABLE_ACCOUNT_ITEMS,AccountsTable.COLUMN_ACCOUNTID + "=?", args);

        return result;

    }
    // Single Account fetch handler
    public Account getAccount(String accountId) {

        Account account = new Account();

        String [] search = {accountId};
        Cursor cursor = mDatabase.query(AccountsTable.TABLE_ACCOUNT_ITEMS,AccountsTable.ALL_COLUMNS_ACCOUNT,AccountsTable.COLUMN_ACCOUNTID + "=?", search,null,null,AccountsTable.COLUMN_ACCOUNTNAME);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            account.setAccountId(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTID)));
            account.setAccountName(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTNAME)));
            account.setAccountDescription(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTDESC)));
            account.setAccountType(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTTYPE)));
        }
        return account;

    }

    // get count of Accounts
    public long getAccountItemsCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, AccountsTable.TABLE_ACCOUNT_ITEMS);
    }

    //Entries
    // Db population
    public void seedDataBaseEntries(List<Entry> entryList) {
        long numItems = getEntriesItemsCount();
        if (numItems == 0) {
            for (Entry item : entryList) {
                try {
                    createItemEntry(item);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //add a single item to database
    public Entry createItemEntry(Entry item) {
        ContentValues values = item.toValues();
        mDatabase.insert(EntriesTable.TABLE_ENTRY_ITEMS, null, values);
        return item;
    }
    // Entry update handler
    public Entry updateEntry(Entry entry) {
        ContentValues values = new ContentValues();
        values.put(EntriesTable.COLUMN_ACCOUNTID,entry.getAccountId());
        values.put(EntriesTable.COLUMN_ENTRYDATE,entry.getEntryDate());
        values.put(EntriesTable.COLUMN_ENTRYDESC,entry.getEntryDescription());
        values.put(EntriesTable.COLUMN_ENTRYTYPE,entry.getEntryType());
        values.put(EntriesTable.COLUMN_ENTRYAMOUNT,entry.getAmount());

        String [] args = {entry.getEntryId()};

        mDatabase.update(EntriesTable.TABLE_ENTRY_ITEMS,values,EntriesTable.COLUMN_ENTRYTID  + "=?", args);
        return entry;
    }
    // Entry deletion handler
    public String deleteEntry(String entryId) {
        String [] args = {entryId};
        String result = getEntry(entryId).getEntryDescription();
        mDatabase.delete(EntriesTable.TABLE_ENTRY_ITEMS,EntriesTable.COLUMN_ENTRYTID + "=?", args);
        return result;

    }

    //gets one specific entry by key
    public Entry getEntry(String entryId) {

        Entry entry = new Entry();

        String [] search = {entryId};
        Cursor cursor = mDatabase.query(EntriesTable.TABLE_ENTRY_ITEMS,EntriesTable.ALL_COLUMNS_ENTRY,EntriesTable.COLUMN_ENTRYTID + "=?", search,null,null,EntriesTable.COLUMN_ENTRYDESC);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            entry.setEntryId(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYTID)));
            entry.setAccountId(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ACCOUNTID)));
            entry.setEntryDescription(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYDESC)));
            entry.setEntryDate(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYDATE)));
            entry.setAmount(cursor.getDouble(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYAMOUNT)));
        }
        return entry;

    }

    //gets a list of expenses by date
    public List<Entry> getExpensesByDate(String date) {

        List<Entry> entryItems = new ArrayList<>();

        String xSearch = "%" + date.substring(3,10);
        String auxQuery = String.format("select entryId,accountId,entryDate,entryDescription,entryType,sum(entryAmount) as amount from entries where entryDate LIKE '%s' and entryType='D' group by entryDescription",xSearch);

        Cursor cursor = mDatabase.rawQuery(auxQuery, null);

        while (cursor.moveToNext()) {

            Entry item = new Entry();
            item.setEntryId(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYTID)));
            item.setEntryDescription(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYDESC)));
            item.setEntryDate(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYDATE)));
            item.setEntryType(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYTYPE)));
            item.setAmount(cursor.getDouble(cursor.getColumnIndex("amount")));
            entryItems.add(item);
        }

        return entryItems;
    }

    //gets a list of expenses by date
    public List<Entry> getIncomeByDate(String date) {

        List<Entry> entryItems = new ArrayList<>();

        String xSearch = "%" + date.substring(3,10);
        String auxQuery = String.format("select entryId,accountId,entryDate,entryDescription,entryType,sum(entryAmount) as amount from entries where entryDate LIKE '%s' and entryType='C' group by entryDescription",xSearch);

        Cursor cursor = mDatabase.rawQuery(auxQuery, null);

        while (cursor.moveToNext()) {

            Entry item = new Entry();
            item.setEntryId(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYTID)));
            item.setEntryDescription(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYDESC)));
            item.setEntryDate(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYDATE)));
            item.setEntryType(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYTYPE)));
            item.setAmount(cursor.getDouble(cursor.getColumnIndex("amount")));
            entryItems.add(item);
        }

        return entryItems;
    }
    //select all entries
    public List<Entry> getAllEntries() {
        List<Entry> entryItems = new ArrayList<>();
        Cursor cursor = mDatabase.query(EntriesTable.TABLE_ENTRY_ITEMS, EntriesTable.ALL_COLUMNS_ENTRY, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Entry item = new Entry();
            item.setEntryId(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYTID)));
            item.setEntryDescription(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYDESC)));
            item.setEntryDate(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYDATE)));
            item.setEntryType(cursor.getString(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYTYPE)));
            item.setAmount(cursor.getDouble(cursor.getColumnIndex(EntriesTable.COLUMN_ENTRYAMOUNT)));
            entryItems.add(item);
        }
        return entryItems;
    }

    // count entries saved in db
    public long getEntriesItemsCount() {
        long query = 0;
        try {
            query = DatabaseUtils.queryNumEntries(mDatabase, EntriesTable.TABLE_ENTRY_ITEMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;
    }


}
