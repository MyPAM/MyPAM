package com.comp231.mypam;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class CategoryDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("new activity","super ok");
        setContentView(android.R.layout.activity_list_item);
        Log.i("new activity","setContentView");
        Toolbar toolbar = findViewById(R.id.toolbar);
        Log.i("new activity","toolbar");
        setSupportActionBar(toolbar);
        Log.i("new activity","find fab");

        FloatingActionButton fab = findViewById(R.id.fab);
        Log.i("new activity","found fab");

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
