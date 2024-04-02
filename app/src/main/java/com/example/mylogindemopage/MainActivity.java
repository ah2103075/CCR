package com.example.mylogindemopage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper databaseHelper;
    private Button signInButton, signUpButton;
    private EditText usernameEditText, passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.signInButtonID);
        signUpButton = findViewById(R.id.signUpHereButtonID);
        usernameEditText = findViewById(R.id.signInusernameEditTextID);
        passwordEditText = findViewById(R.id.signInpasswordEditTextID);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter username ", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter password ", Toast.LENGTH_SHORT).show();
        } else {
            if (v.getId() == R.id.signInButtonID) {
                Boolean result = databaseHelper.findPassword(username, password);
                if (result == true) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "username or password didn't match", Toast.LENGTH_SHORT).show();
                }
            }

            try {
                if (v.getId() == R.id.signUpHereButtonID) {
                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(intent);
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please enter all data", Toast.LENGTH_LONG).show();
            }
        }
    }
}