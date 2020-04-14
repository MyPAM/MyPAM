package com.comp231.mypam.model;

import android.content.ContentValues;

import com.comp231.mypam.database.AccountsTable;
import com.comp231.mypam.database.EntriesTable;

import java.util.Date;
import java.util.UUID;

public class Entry {
    private String entryId;
    private String accountId;
    private String entryDate;
    private String entryDescription;
    private String entryType;
    private double amount;

    public Entry() {
    }

    public Entry(String entryId, String accountId, String entryDate, String entryDescription, String entryType, double amount) {

        if (entryId == null){
            entryId = UUID.randomUUID().toString();
        }
        this.entryId = entryId;
        this.accountId = accountId;
        this.entryDate = entryDate;
        this.entryDescription = entryDescription;
        this.entryType = entryType;
        this.amount = amount;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //Converts the model properties to database format
    public ContentValues toValues(){
        ContentValues values = new ContentValues(5); //qty of fields in the table
        values.put(EntriesTable.COLUMN_ENTRYTID, entryId);
        values.put(EntriesTable.COLUMN_ACCOUNTID, accountId);
        values.put(EntriesTable.COLUMN_ENTRYDATE, String.valueOf(entryDate));
        values.put(EntriesTable.COLUMN_ENTRYDESC, entryDescription);
        values.put(EntriesTable.COLUMN_ENTRYTYPE, entryType);
        values.put(EntriesTable.COLUMN_ENTRYAMOUNT, amount);
        return values;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "entryId='" + entryId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", entryDate=" + entryDate +
                ", entryDescription='" + entryDescription + '\'' +
                ", entryType='" + entryType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
