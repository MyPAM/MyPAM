package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Category;
import com.comp231.mypam.R.layout.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
            tvLabel2.setText("Manage Category");
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
//                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(i);
                setResult(CategoryActivity.RESULT_OK);
                finish();
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
                setResult(CategoryActivity.RESULT_OK);
                finish();
//                Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
//                startActivity(i);
            }
        });

        FloatingActionButton fabDelete = findViewById(R.id.fabDelete);
        fabDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                EditText editCategory;
                editCategory = findViewById(R.id.test1);
                String name = editCategory.getText().toString();

                final Context here = getBaseContext();
                DataSource mDataSource = new DataSource(getApplicationContext());
                mDataSource.open();

                if (function == "U") {
                    mDataSource.deleteCategory(intent.getStringExtra("category"));
                }
                Toast.makeText(here,name + " Deleted", Toast.LENGTH_SHORT).show();
                setResult(CategoryActivity.RESULT_OK);
                finish();
//                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(i);
            }
        });


    }
    @Override
    public void onBackPressed(){
        finish();
    }
}
