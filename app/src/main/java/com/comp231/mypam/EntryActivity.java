package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Account;
import com.comp231.mypam.model.Category;
import com.comp231.mypam.model.Entry;
import com.comp231.mypam.R.layout.*;
import com.comp231.mypam.sample.SampleDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class EntryActivity extends AppCompatActivity {

    String function = null;
    private Spinner spinner;
    private Spinner spinnerCategory;

    List<Category> categoryList = SampleDataProvider.categoryItemList;
    DataSource mDataSource;
    private Context mContext;
    View myView;
    TextView mDate;
    Button mButton;
    DatePickerDialog dpd;
    Calendar c;
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    String currentDateTime = sdf.format(new Date());
    String accountIDSelected;
    String categorySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        final Intent intent = getIntent();
        final TextView label;

        mDate = (TextView) findViewById(R.id.date);
        mButton = (Button) findViewById(R.id.pickButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = 01;
                int month = 04;
                int year = 2020;

                dpd = new DatePickerDialog(EntryActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, year, month-1, day);
                dpd.show();
            }
        });

        mDataSource = new DataSource(getApplicationContext());
        mDataSource.open();

        //get the categories from the database to populate the category spinner
        final List<Category> categoryFromDb = mDataSource.getAllItems();

        List<String> itemNames = new ArrayList<>();
        List<String> itemID = new ArrayList<>();

        Collections.sort(categoryFromDb, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getCategoryName().compareTo(o2.getCategoryName());
            }});

        for (Category item: categoryFromDb) {
            itemNames.add(item.getCategoryName());
            itemID.add(item.getCategoryId());
        }

        spinnerCategory = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,itemNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                categorySelected = spinnerCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });


        //get the accounts from the database to populate the spinner
        final List<Account> accountFromDb = mDataSource.getAllAccounts();
        List<String> accountNames = new ArrayList<>();
        final List<String> accountID = new ArrayList<>();

        for (Account item: accountFromDb) {
            accountNames.add(item.getAccountName());
            accountID.add(item.getAccountId());
            Log.i("reading",item.toString());
        }

        spinner = (Spinner)findViewById(R.id.spinnerAccount);
        ArrayAdapter<String> adapterA = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,accountNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterA);

        //event handler for the Account spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

               accountIDSelected = spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });

        TextView tvLabel2 = findViewById(R.id.tvLabel2);

        if (intent.getStringExtra("entry") != null){
            tvLabel2.setText("Manage Entry");
//            label = findViewById(R.id.tvLabel2);
//            Entry entry = mDataSource.getEntry(intent.getStringExtra("entry"));
//            label.setText(entry.getEntryDescription());
            function = "U";

        } else{
            if (intent.getStringExtra("add") != null) {
                tvLabel2.setText("Add Entry");
//                label = findViewById(R.id.tvLabel2);
//                label.setText("");
                function = "C";
                FloatingActionButton fabDelete = findViewById(R.id.fabDelete);
                fabDelete.hide();
            }
        }

        //event handlers for the fab buttons
        FloatingActionButton fabCancel = findViewById(R.id.fabCancel);

        fabCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                setResult(EntryActivity.RESULT_OK);
                finish();
            }
        });

        FloatingActionButton fabConfirm = findViewById(R.id.fabConfirm);
        fabConfirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                EditText amountEntry;
                //EditText editEntry;
                TextView dateEntry;

                //editEntry = findViewById(R.id.test1);
                amountEntry = findViewById((R.id.amount));
                dateEntry = findViewById((R.id.date));

                //String name = editEntry.getText().toString();

                Double amount = Double.parseDouble(amountEntry.getText().toString());

                String date = dateEntry.getText().toString();

                final Context here = getBaseContext();
                DataSource mDataSource = new DataSource(getApplicationContext());
                mDataSource.open();

                if (function == "C") {
                    Entry newEntry = new Entry(null,accountIDSelected,date,categorySelected,"D",amount);
                    mDataSource.createItemEntry(newEntry);
                }

                if (function == "U") {
                    Entry newEntry = new Entry(intent.getStringExtra("entry"),accountIDSelected,date,categorySelected,"D", amount);
                    mDataSource.updateEntry(newEntry);
                }
                Toast.makeText(here,"Changes Saved", Toast.LENGTH_SHORT).show();
                setResult(CategoryActivity.RESULT_OK);
                finish();
            }
        });

        FloatingActionButton fabDelete = findViewById(R.id.fabDelete);
        if (function == "C") {
            fabDelete.hide();
        }


        fabDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                Spinner editEntry;
                editEntry = findViewById(R.id.spinner);
                String name = editEntry.getSelectedItem().toString();

                final Context here = getBaseContext();
                DataSource mDataSource = new DataSource(getApplicationContext());
                mDataSource.open();

                if (function == "U") {
                    mDataSource.deleteEntry(intent.getStringExtra("entry"));
                    Toast.makeText(here,name + " Deleted", Toast.LENGTH_SHORT).show();
                }
                setResult(EntryActivity.RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
