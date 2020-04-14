package com.comp231.mypam.sample;

import com.comp231.mypam.model.Account;
import com.comp231.mypam.model.Category;
import com.comp231.mypam.model.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//Creates default data for the app in the first use
public class SampleDataProvider {

    //Creates a list of default Categories of Entries
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

    //Creates a list of default Accounts
    public static List<Account> accountItemList;
    public static Map<String, Account> accountItemMap;

    static {
        accountItemList = new ArrayList<>();
        accountItemMap = new HashMap<>();

        addAccount(new Account(null, "Chequing 1234","Chequing Account at RBC","Chequing"));
        addAccount(new Account(null, "Savings 5678","Savings Account at RBC","Saving"));


    }

    private static void addAccount(Account item) {
        accountItemList.add(item);
        accountItemMap.put(item.getAccountId(), item);
    }

    //Creates a list of default entries
    public static List<Entry> entryList;
    public static Map<String, Entry> entryItemMap;

    static {
        entryList = new ArrayList<>();
        entryItemMap = new HashMap<>();
        String account = accountItemList.get(0).toString();

        addEntry(new Entry(null, account, "04/01/2020", "Rent","D",1200));
        addEntry(new Entry(null, account, "04/01/2020", "Salary","C",2200));
        addEntry(new Entry(null, account, "04/02/2020", "Groceries","D",250));
        addEntry(new Entry(null, account, "04/03/2020", "Pharmacy","D",20));

    }

    private static void addEntry(Entry item) {
        entryList.add(item);
        entryItemMap.put(item.getEntryId(), item);
    }
}
