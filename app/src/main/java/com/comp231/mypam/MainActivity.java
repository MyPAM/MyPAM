package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Category;
import com.comp231.mypam.sample.SampleDataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Category> categoryList = SampleDataProvider.categoryItemList;
    DataSource mDataSource;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating the database
        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.seedDataBase(categoryList);

        final List<Category> listFromDb = mDataSource.getAllItems();
        List<String> itemNames = new ArrayList<>();

        for (Category item: listFromDb) {
            itemNames.add(item.getCategoryName());
        }

        Collections.sort(listFromDb, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getCategoryName().compareTo(o2.getCategoryName());
            }});

        //DataItemAdapter adapter = new DataItemAdapter(this,listFromDb);
        DataItemAdapterListView adapter = new DataItemAdapterListView(this,listFromDb);
        //DataItemAdapterListView adapter = new DataItemAdapterListView(this,categoryList);

        //RecyclerView listView = (RecyclerView) findViewById(android.R.id.list);
        ListView listView = (ListView) findViewById(android.R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
               Log.d("############","Items " +  listFromDb.get(arg2) );
                Intent intent = new Intent(getApplicationContext(),CategoryDetails.class);
                startActivity(intent);
                finish();
            }

        });

    }
}
