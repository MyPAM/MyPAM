package com.comp231.mypam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_ru05_account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ru05_account);

        final EditText accountName = (EditText) findViewById(R.id.accountName);
        final EditText accountDescription = (EditText) findViewById(R.id.accountDescription);
        final EditText accountType = (EditText) findViewById(R.id.accountType);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = accountName.getText().toString();
                String description = accountDescription.getText().toString();
                String type = accountType.getText().toString();

                Intent intent = new Intent(getApplicationContext(), AccountInfo.class);
                intent.putExtra("accountName", name);
                intent.putExtra("accountDescription", description);
                intent.putExtra("accountType", type);
                startActivity(intent);
            }
        });

        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_ru05_account.this);
                builder.setMessage("Are you sure");
                builder.setTitle("Cancel")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Cancel");
                alert.show();
            }

        });


    }
}
