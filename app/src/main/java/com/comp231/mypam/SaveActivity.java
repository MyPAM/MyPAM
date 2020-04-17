package com.comp231.mypam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.lang.Object;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SaveActivity extends AppCompatActivity {

    Button saveButton;
    TextView textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        saveButton = findViewById(R.id.saveButton);
        textview2 = findViewById(R.id.textView2);
        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    try {
                        File f = new File(Environment.getExternalStorageDirectory(), "External.csv");
                        String filePath = Environment.getExternalStorageDirectory().toString();
                        CSVWriter writer;
                        textview2.setText("Saved successfully");
                        // File exist
                        if (f.exists() && !f.isDirectory()) {
                            FileWriter mFileWriter = new FileWriter(filePath, true);
                            writer = new CSVWriter(mFileWriter);
                        } else {
                            writer = new CSVWriter(new FileWriter(filePath));
                        }

                        List<String[]> data = new ArrayList<String[]>();
                        data.add(new String[]{"India", "New Delhi"});

                        writer.writeAll(data);

                        writer.close();
                    } catch (Exception e) {

                    }
                }
            }
        });
    }
}
