package org.alexcode.crazysnakes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Login";
    private Button loginBtn, registerLink;
    private EditText userName, userPass;
    private TextView userNameErr, userPassErr;
    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new Database(this);
        initLoginView();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initLogin();
            }
        });
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayerRegister.class);
                startActivity(intent);
            }
        });
    }

    private void initLogin(){
        Log.d(TAG, "initLogin : Started");

        if (validateData() && validateLogin()) {
            loginUser();
        }
    }

    private void loginUser(){
        Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, GameMenu.class);
        startActivity(intent);
        userName.setText("");
        userPass.setText("");

    }

    @SuppressLint("SetTextI18n")
    private boolean validateData() {
        Log.d(TAG, "validateData : Started");
        boolean isValid = true;
        if (userName.getText().toString().equals("")) {
            userNameErr.setVisibility(View.VISIBLE);
            userNameErr.setText("Enter your name");
            isValid = false;
        } else {
            userNameErr.setVisibility(View.GONE);
        }
        if (userPass.getText().toString().equals("")) {
            userPassErr.setVisibility(View.VISIBLE);
            userPassErr.setText("Enter your password");
            isValid = false;
        } else {
            userPassErr.setVisibility(View.GONE);
        }
        return isValid;
    }

    @SuppressLint("SetTextI18n")
    private boolean validateLogin() {
        Cursor res = myDb.checkLogin(userName.getText().toString());
        if(res.getCount() == 0) {
            userNameErr.setVisibility(View.VISIBLE);
            userNameErr.setText("The username is not correct");
            return false;
        } else {
            userNameErr.setVisibility(View.GONE);
        }
        res.moveToNext();
        if(!userPass.getText().toString().equals(res.getString(1))) {
            userPassErr.setVisibility(View.VISIBLE);
            userPassErr.setText("The password is not correct");
            return false;
        } else {
            userPassErr.setVisibility(View.GONE);
        }
        return true;
    }

    private void initLoginView() {
        Log.d(TAG, "initLoginView:Started");
        loginBtn = findViewById(R.id.loginBtn);
        registerLink = findViewById(R.id.registerLink);
        userName = findViewById(R.id.userName);
        userPass = findViewById(R.id.userPassword);
        userNameErr = findViewById(R.id.userNameErr);
        userPassErr = findViewById(R.id.userPassErr);
    }
}