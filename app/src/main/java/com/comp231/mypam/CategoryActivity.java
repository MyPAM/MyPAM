package com.comp231.mypam;

import androidx.annotation.CheckResult;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.widget.Toast.LENGTH_LONG;

public class CategoryActivity extends AppCompatActivity {

    String function = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        final Intent intent = getIntent();
        final TextView test1;
        DataSource mDataSource = new DataSource(getApplicationContext());
        mDataSource.open();

        TextView tvLabel2 = findViewById(R.id.tvLabel2);

        if (intent.getStringExtra("category") != null){
            tvLabel2.setText("Update Category");
            test1 = findViewById(R.id.test1);
            Category category = mDataSource.getCategory(intent.getStringExtra("category"));
            test1.setText(category.getCategoryName());
            function = "U";
        } else{
            if (intent.getStringExtra("add") != null) {
                tvLabel2.setText("Add Category");
                test1 = findViewById(R.id.test1);
                test1.setText("");
                function = "C";
            }
        }

        FloatingActionButton fabCancel = findViewById(R.id.fabCancel);

        fabCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        FloatingActionButton fabConfirm = findViewById(R.id.fabConfirm);
        fabConfirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                EditText editCategory;
                editCategory = findViewById(R.id.test1);
                String name = editCategory.getText().toString();

                final Context here = getBaseContext();
                DataSource mDataSource = new DataSource(getApplicationContext());
                mDataSource.open();

                    if (function == "C") {
                        Category newCategory = new Category(null,name,"A","D");
                        mDataSource.createItem(newCategory);
                    }

                    if (function == "U") {
                        Category newCategory = new Category(intent.getStringExtra("category"),name,"A","D");
                        mDataSource.updateCategory(newCategory);
                    }
                    Toast.makeText(here,"Changes Saved", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
