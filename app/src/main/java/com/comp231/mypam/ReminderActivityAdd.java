package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReminderActivityAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_add);

        TextView textView = findViewById(R.id.text_view);

        String message = getIntent().getStringExtra("message");
        textView.setText(message);
    }
}
