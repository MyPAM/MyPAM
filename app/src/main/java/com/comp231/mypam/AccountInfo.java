package com.comp231.mypam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountInfo extends AppCompatActivity {

    String function = null;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_accountinfo);

        final Intent intent = getIntent();

        EditText accountName = null;
        EditText accountDescription = null;
        EditText accountType = null;

        DataSource mDataSource = new DataSource(getApplicationContext());
        mDataSource.open();

        TextView tvLabel2 = findViewById(R.id.tvLabel2);

        if (intent.getStringExtra("account") != null){
            tvLabel2.setText("Manage Account");

            Account account = mDataSource.getAccount(intent.getStringExtra("account"));
            accountName = findViewById((R.id.accountName));
            accountName.setText(account.getAccountName());
            accountDescription = findViewById((R.id.accountDescription));
            accountDescription.setText(account.getAccountDescription());
            accountType = findViewById((R.id.accountType));
            accountType.setText(account.getAccountType());
            function = "U";
        } else{
            if (intent.getStringExtra("add") != null) {
                tvLabel2.setText("Add Account");
                accountName = findViewById(R.id.accountName);
                accountName.setText("");
                accountDescription = findViewById((R.id.accountDescription));
                accountDescription.setText("");
                accountType = findViewById((R.id.accountType));
                accountType.setText("");
                function = "C";
            }
        }
        FloatingActionButton fabCancel = findViewById(R.id.fabCancel);

        fabCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                setResult(AccountInfo.RESULT_OK);
                finish();
            }
        });

        FloatingActionButton fabConfirm = findViewById(R.id.fabConfirm);
        fabConfirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                EditText accountName = null;
                EditText accountDescription = null;
                EditText accountType = null;

                accountName = findViewById((R.id.accountName));
                String name = accountName.getText().toString();
                accountDescription = findViewById((R.id.accountDescription));
                String description = accountDescription.getText().toString();
                accountType = findViewById((R.id.accountType));
                String type = accountType.getText().toString();

                final Context here = getBaseContext();
                DataSource mDataSource = new DataSource(getApplicationContext());
                mDataSource.open();

                if (function == "C") {
                    Account newAccount = new Account(null,name,description,type);
                    mDataSource.createItemAccount(newAccount);
                }

                if (function == "U") {
                    Account newAccount = new Account(intent.getStringExtra("account"),name,description,type);
                    mDataSource.updateAccount(newAccount);
                }
                Toast.makeText(here,"Changes Saved", Toast.LENGTH_SHORT).show();
                setResult(AccountInfo.RESULT_OK);
                finish();
            }
        });

        FloatingActionButton fabDelete = findViewById(R.id.fabDelete);
        if (function == "C") {
            fabDelete.hide();
        }

        fabDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                EditText editAccount;
                editAccount = findViewById(R.id.accountName);
                String name = editAccount.getText().toString();

                final Context here = getBaseContext();
                DataSource mDataSource = new DataSource(getApplicationContext());
                mDataSource.open();

                if (function == "U") {
                    mDataSource.deleteAccount(intent.getStringExtra("account"));
                }
                Toast.makeText(here,name + " Deleted", Toast.LENGTH_SHORT).show();
                setResult(AccountInfo.RESULT_OK);
                finish();
            }
        });
    }
}
