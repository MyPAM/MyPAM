package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        Button addEntryButton = (Button) findViewById(R.id.addFloatingActionButton);

        RecyclerView incomeView = (RecyclerView) findViewById(R.id.income_entry_recyclerview);
        RecyclerView.LayoutManager incomeLayoutManager = new LinearLayoutManager(this);
        RecyclerViewAdapter incomeRecyclerViewAdapter = new RecyclerViewAdapter();
        incomeView.setLayoutManager(incomeLayoutManager);
        incomeView.setAdapter(incomeRecyclerViewAdapter);

        RecyclerView expenseView = (RecyclerView) findViewById(R.id.expense_entry_recyclerview);
        RecyclerView.LayoutManager expenseLayoutManager = new LinearLayoutManager(this);
        RecyclerViewAdapter expenseRecyclerViewAdapter = new RecyclerViewAdapter();
        expenseView.setLayoutManager(expenseLayoutManager);
        expenseView.setAdapter(expenseRecyclerViewAdapter);
    }
}
