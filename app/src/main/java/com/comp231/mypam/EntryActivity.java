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

import java.text.DecimalFormat;
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
    Entry mEntry;
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
        //Opens the database
        mDataSource = new DataSource(getApplicationContext());
        mDataSource.open();

        TextView tvLabel2 = findViewById(R.id.tvLabel2);
        EditText edAmount = findViewById(R.id.amount);
        TextView tvDate   = findViewById(R.id.date);

        //loads the data with the selected entry
        if (intent.getStringExtra("entry") != null){
            tvLabel2.setText("Manage Entry");
            mEntry = mDataSource.getEntry(intent.getStringExtra("entry"));
            tvDate.setText(mEntry.getEntryDate());
            edAmount.setText(String.format("%.2f",mEntry.getAmount()));
            function = "U";
        //Loads an empty screen to add a new entry
        } else{
            if (intent.getStringExtra("add") != null) {
                tvLabel2.setText("Add Entry");
                function = "C";
                FloatingActionButton fabDelete = findViewById(R.id.fabDelete);
                fabDelete.hide();
            }
        }

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
        //prepare the spinner for existing Categories
        spinnerCategory = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,itemNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
        //for the update function, sets the spinner on the original category
        if (function == "U") {
            spinnerCategory.setSelection(itemNames.indexOf(mEntry.getEntryDescription()));
        }

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
        }

        //sets the spinner for existing accounts
        spinner = (Spinner)findViewById(R.id.spinnerAccount);
        ArrayAdapter<String> adapterA = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,accountNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterA);

        //for the update function, sets the spinner on the original account
        if (function == "U") {
            spinner.setSelection(accountNames.indexOf(mEntry.getAccountId()));
        }

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

        //event handler for the pick date button (opens a popup with a calendar)
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uses the current date to initialize the date picker for function create
                int day = 0, month = 0, year = 0;

                if (function == "C"){

                    day =  Integer.parseInt(currentDateTime.substring(3,5));
                    month = Integer.parseInt(currentDateTime.substring(0,2));
                    year = Integer.parseInt(currentDateTime.substring(6,10));


                } else {
                    day = Integer.parseInt(mEntry.getEntryDate().substring(0,2));
                    month = Integer.parseInt(mEntry.getEntryDate().substring(3,5));
                    year = Integer.parseInt(mEntry.getEntryDate().substring(6,10));
                }
                dpd = new DatePickerDialog(EntryActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String dateStr = String.format("%02d/%02d/%4d",  dayOfMonth, month+1,year);
                        mDate.setText(dateStr);
                    }
                }, year, month-1, day);
                Log.i("test2",String.valueOf(month));
                dpd.show();
            }
        });

        //instatiation and event handlers for the fab buttons
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
                TextView dateEntry;
                amountEntry = findViewById((R.id.amount));
                dateEntry = findViewById((R.id.date));

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

        //hides the delete button for the create option
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
