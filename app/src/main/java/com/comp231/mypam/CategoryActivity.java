package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        final Intent intent = getIntent();

        //final Intent backIntent = new Intent(getApplicationContext(),MainActivity.class);

        String test = intent.getStringExtra("category");
        TextView test1;

        test1 = findViewById(R.id.test1);
        test1.setText(test);

        FloatingActionButton fabCancel = findViewById(R.id.fabCancel);

        fabCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


      /*  fabCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(backIntent);
            }
        });*/

    }
}
