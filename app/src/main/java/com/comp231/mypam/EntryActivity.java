package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static androidx.constraintlayout.widget.ConstraintSet.VISIBLE;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
    }

    public void addIncomeButton00Click(View v) {
        Button addButton = (Button) findViewById(R.id.addIncomeButton00);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView01);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void addIncomeButton01Click(View v) {
        Button addButton = (Button) findViewById(R.id.addIncomeButton01);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView02);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void clearIncomeButton01Click(View v) {
        Button addButton00 = (Button) findViewById(R.id.addIncomeButton00);
        addButton00.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView01);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryIncomeEditText01);
        EditText dateEdit = (EditText) findViewById(R.id.dateIncomeEditText01);
        EditText amountEdit = (EditText) findViewById(R.id.amountIncomeEditText01);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }

    public void addIncomeButton02Click(View v) {
        Button addButton = (Button) findViewById(R.id.addIncomeButton02);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView03);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void clearIncomeButton02Click(View v) {
        Button addButton01 = (Button) findViewById(R.id.addIncomeButton01);
        addButton01.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView02);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryIncomeEditText02);
        EditText dateEdit = (EditText) findViewById(R.id.dateIncomeEditText02);
        EditText amountEdit = (EditText) findViewById(R.id.amountIncomeEditText02);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }

    public void addIncomeButton03Click(View v) {
        Button addButton = (Button) findViewById(R.id.addIncomeButton03);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView04);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void clearIncomeButton03Click(View v) {
        Button addButton01 = (Button) findViewById(R.id.addIncomeButton02);
        addButton01.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView03);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryIncomeEditText03);
        EditText dateEdit = (EditText) findViewById(R.id.dateIncomeEditText03);
        EditText amountEdit = (EditText) findViewById(R.id.amountIncomeEditText03);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }

    public void addIncomeButton04Click(View v) {
        Button addButton = (Button) findViewById(R.id.addIncomeButton04);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView05);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void clearIncomeButton04Click(View v) {
        Button addButton01 = (Button) findViewById(R.id.addIncomeButton03);
        addButton01.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView04);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryIncomeEditText04);
        EditText dateEdit = (EditText) findViewById(R.id.dateIncomeEditText04);
        EditText amountEdit = (EditText) findViewById(R.id.amountIncomeEditText04);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }

    public void clearIncomeButton05Click(View v) {
        Button addButton01 = (Button) findViewById(R.id.addIncomeButton04);
        addButton01.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.incomeCardView05);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryIncomeEditText05);
        EditText dateEdit = (EditText) findViewById(R.id.dateIncomeEditText05);
        EditText amountEdit = (EditText) findViewById(R.id.amountIncomeEditText05);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }

    public void addExpenseButton00Click(View v) {
        Button addButton = (Button) findViewById(R.id.addExpenseButton00);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView01);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void addExpenseButton01Click(View v) {
        Button addButton = (Button) findViewById(R.id.addExpenseButton01);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView02);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void clearExpenseButton01Click(View v) {
        Button addButton00 = (Button) findViewById(R.id.addExpenseButton00);
        addButton00.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView01);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryExpenseEditText01);
        EditText dateEdit = (EditText) findViewById(R.id.dateExpenseEditText01);
        EditText amountEdit = (EditText) findViewById(R.id.amountExpenseEditText01);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }

    public void addExpenseButton02Click(View v) {
        Button addButton = (Button) findViewById(R.id.addExpenseButton02);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView03);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void clearExpenseButton02Click(View v) {
        Button addButton01 = (Button) findViewById(R.id.addExpenseButton01);
        addButton01.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView02);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryExpenseEditText02);
        EditText dateEdit = (EditText) findViewById(R.id.dateExpenseEditText02);
        EditText amountEdit = (EditText) findViewById(R.id.amountExpenseEditText02);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }

    public void addExpenseButton03Click(View v) {
        Button addButton = (Button) findViewById(R.id.addExpenseButton03);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView04);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void clearExpenseButton03Click(View v) {
        Button addButton01 = (Button) findViewById(R.id.addExpenseButton02);
        addButton01.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView03);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryExpenseEditText03);
        EditText dateEdit = (EditText) findViewById(R.id.dateExpenseEditText03);
        EditText amountEdit = (EditText) findViewById(R.id.amountExpenseEditText03);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }

    public void addExpenseButton04Click(View v) {
        Button addButton = (Button) findViewById(R.id.addExpenseButton04);
        addButton.setVisibility(View.INVISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView05);
        incomeCardView.setVisibility(View.VISIBLE);
    }

    public void clearExpenseButton04Click(View v) {
        Button addButton01 = (Button) findViewById(R.id.addExpenseButton03);
        addButton01.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView04);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryExpenseEditText04);
        EditText dateEdit = (EditText) findViewById(R.id.dateExpenseEditText04);
        EditText amountEdit = (EditText) findViewById(R.id.amountExpenseEditText04);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }

    public void clearExpenseButton05Click(View v) {
        Button addButton01 = (Button) findViewById(R.id.addExpenseButton04);
        addButton01.setVisibility(View.VISIBLE);
        View incomeCardView = (View) findViewById(R.id.expenseCardView05);
        incomeCardView.setVisibility(View.GONE);
        EditText categoryEdit = (EditText) findViewById(R.id.categoryExpenseEditText05);
        EditText dateEdit = (EditText) findViewById(R.id.dateExpenseEditText05);
        EditText amountEdit = (EditText) findViewById(R.id.amountExpenseEditText05);
        categoryEdit.setText("");
        dateEdit.setText("");
        amountEdit.setText("");
    }
}
