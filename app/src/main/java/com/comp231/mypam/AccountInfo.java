package com.comp231.mypam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ru05_accountinfo);
        TextView accountName = (TextView) findViewById(R.id.accountName);
        TextView accountDescription = (TextView) findViewById(R.id.accountDescription);
        TextView accountType = (TextView) findViewById(R.id.accountType);
        Intent intent = getIntent();
        accountName.setText(intent.getStringExtra("accountName").toString());
        accountDescription.setText(intent.getStringExtra("accountDescription").toString());
        accountType.setText(intent.getStringExtra("accountType").toString());

    }
}
