package com.comp231.mypam.model;

import java.util.UUID;
import android.content.ContentValues;

import com.comp231.mypam.database.AccountsTable;

public class Account {
    private String accountId;
    private String accountName;
    private String accountDescription;
    private String accountType;

    public Account(){
    }

    public Account(String accountId, String accountName, String accountDescription, String accountType) {

        if (accountId == null){
            accountId = UUID.randomUUID().toString();
        }
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountDescription = accountDescription;
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(4); //qty of fields in the table
        values.put(AccountsTable.COLUMN_ACCOUNTID, accountId);
        values.put(AccountsTable.COLUMN_ACCOUNTNAME, accountName);
        values.put(AccountsTable.COLUMN_ACCOUNTDESC, accountDescription);
        values.put(AccountsTable.COLUMN_ACCOUNTTYPE, accountType);
        return values;
    }
    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountDescription='" + accountDescription + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
