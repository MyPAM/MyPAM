package com.comp231.mypam.sample;

import com.comp231.mypam.model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleDataProvider {
    public static List<Category> categoryItemList;
    public static Map<String, Category> categoryItemMap;

    static {
        categoryItemList = new ArrayList<>();
        categoryItemMap = new HashMap<>();

        addItem(new Category(null, "Salary","Active","C"));
        addItem(new Category(null, "Rent","Active","D"));
        addItem(new Category(null, "Transportation","Active","D"));
        addItem(new Category(null, "Tuition","Active","D"));
        addItem(new Category(null, "Insurance","Active","D"));

    }

    private static void addItem(Category item) {
        categoryItemList.add(item);
        categoryItemMap.put(item.getCategoryId(), item);
    }
}
