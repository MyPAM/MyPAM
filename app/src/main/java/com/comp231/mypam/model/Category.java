package com.comp231.mypam.model;

import android.content.ContentValues;

import com.comp231.mypam.database.CategoriesTable;

import java.util.UUID;

public class Category {
    private String categoryId;
    private String categoryName;
    private String categoryStatus;
    private String categoryType;

    public Category() {
    }

    public Category(String categoryId, String categoryName, String categoryStatus, String categoryType) {

        //generates a random unique identifier to the new category
        if (categoryId == null){
            categoryId = UUID.randomUUID().toString();
        }

        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
        this.categoryType = categoryType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(4); //qty of fields in the table
        values.put(CategoriesTable.COLUMN_ID, categoryId);
        values.put(CategoriesTable.COLUMN_NAME, categoryName);
        values.put(CategoriesTable.COLUMN_STATUS, categoryStatus);
        values.put(CategoriesTable.COLUMN_TYPE, categoryType);
        return values;
    }

    @Override
    public String toString() {
        return "CategoryItem{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryStatus='" + categoryStatus + '\'' +
                ", categoryType='" + categoryType + '\'' +
                '}';
    }
}
