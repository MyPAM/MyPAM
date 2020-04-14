package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
    }

//Login button invoke login method
    public void login(View v){
        EditText edUserid = (EditText) findViewById(R.id.userid);
        EditText edPasswd = (EditText) findViewById(R.id.passwd);
        String uid = edUserid.getText().toString();
        String pw = edPasswd.getText().toString();
        if (uid.equals("mypam") && pw.equals("1234")){ //successfully login
            Toast.makeText(this, "successfully login", Toast.LENGTH_LONG).show();
            finish();
        }else{ //failed
            new AlertDialog.Builder(this)
                    .setTitle("Login")
                    .setMessage("failed")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    public void cancel(View v){

    }
}
