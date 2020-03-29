package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Category;
import com.comp231.mypam.sample.SampleDataProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Category> categoryList = SampleDataProvider.categoryItemList;
    DataSource mDataSource;
    private Context mContext;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_category) {

        //commit 2
            Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
            i.putExtra("add", "add");
            startActivity(i);
        }

        else if (id == R.id.action_entry) {

            //commit 2
            Intent i = new Intent(getApplicationContext(), EntryActivity.class);
            //i.putExtra("add", "add");
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
