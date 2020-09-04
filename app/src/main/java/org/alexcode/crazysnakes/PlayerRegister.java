package org.alexcode.crazysnakes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

//TODO: need to remake for remote database

public class PlayerRegister extends AppCompatActivity {
    private static final String TAG = "PlayerRegistration";
    private TextView nameErr, emailErr, passErr;
    private Button registerBtn;
    private EditText playerName, playerPass, playerEmail;
    private String gender;
    private RadioButton male, female, other;
    private CheckBox agreement;
    Database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_register);
        initRegisterView();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();

            }
        });
        myDb = new Database(this);
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");
        if(validateData2()) {
            boolean registeredSuccesfully = myDb.insertNewUser(playerName.getText().toString(),
                    playerEmail.getText().toString(),
                    playerPass.getText().toString(), gender);
            if(registeredSuccesfully) {
                register();
            } else  {
                Toast.makeText(PlayerRegister.this, "Not registered, please try again", Toast.LENGTH_SHORT).show();
            }
       }
    }

    private void register() {
        Toast.makeText(PlayerRegister.this, "Registered successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PlayerRegister.this, GameMenu.class);
        startActivity(intent);
        playerName.setText("");
        playerPass.setText("");
        playerEmail.setText("");
    }

    @SuppressLint("SetTextI18n")
    private boolean validateData2() {
        Log.d(TAG, "validateData: Started");
        boolean isValid = true;
        if(playerName.getText().toString().equals("")) {
            nameErr.setVisibility(View.VISIBLE);
            nameErr.setText("Enter your name");
            isValid = false;
        } else {
            nameErr.setVisibility(View.GONE);
        }
        if(playerPass.getText().toString().equals("")) {
            passErr.setVisibility(View.VISIBLE);
            passErr.setText("Enter a password");
            isValid = false;
        } else {
            passErr.setVisibility(View.GONE);
        }
        if(playerEmail.getText().toString().equals("")) {
            emailErr.setVisibility(View.VISIBLE);
            emailErr.setText("Enter you email");
            isValid = false;
        } else {
            emailErr.setVisibility(View.GONE);
        }
        if(!agreement.isChecked()) {
            Toast.makeText(this, "You need to agree our terms and conditions", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (isValid) {
            if (male.isChecked()) {
                gender = male.getText().toString();
            } else if (female.isChecked()) {
                gender = female.getText().toString();
            } else {
                gender = other.getText().toString();
            }
        }
        return isValid;
    }

    private void  initRegisterView() {
        registerBtn = findViewById(R.id.registerBtn);
        playerName = findViewById(R.id.playerName);
        playerPass = findViewById(R.id.playerPass);
        playerEmail =  findViewById(R.id.playerEmail);
        nameErr = findViewById(R.id.nameErr);
        emailErr = findViewById(R.id.emailErr);
        passErr = findViewById(R.id.passErr);
        agreement = findViewById(R.id.agreement);
        male = findViewById(R.id.playerMale);
        female = findViewById(R.id.playerFemale);
        other = findViewById(R.id.playerOther);
    }
}