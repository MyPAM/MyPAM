package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private final int INCOME_VIEW_ID = 0x8000;
    private ScrollView incomeScrollView;
    int numIncomeCard = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incomeScrollView = (ScrollView)findViewById(R.id.incomeScrollView);
    }

    public void incomeAddButtonClick(View v) {
        numIncomeCard++;
        CardView newIncomeCardView = (CardView)findViewById(R.id.incomeCardView);
        newIncomeCardView.setId(INCOME_VIEW_ID + numIncomeCard);
        incomeScrollView.addView(newIncomeCardView);
    }
}
