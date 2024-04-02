package com.example.mylogindemopage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, emailEditText, usernameEditText, passwordEditText;
    private Button signUpButton;
    UserDetails userDetails;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = findViewById(R.id.editNameID);
        emailEditText = findViewById(R.id.editEmailID);
        usernameEditText = findViewById(R.id.editUserNameID);
        passwordEditText = findViewById(R.id.editPasswordID);
        signUpButton = findViewById(R.id.signUpButtonID);

        databaseHelper = new DatabaseHelper(this);
        userDetails = new UserDetails();
        signUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(name.equals("")){
            Toast.makeText(SignUpActivity.this, "Please enter name ", Toast.LENGTH_SHORT).show();
        }
        else if(email.equals("")){
            Toast.makeText(SignUpActivity.this, "Please enter email ", Toast.LENGTH_SHORT).show();
        }
        else if(username.equals("")){
            Toast.makeText(SignUpActivity.this, "Please enter username ", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(SignUpActivity.this, "Please enter password ", Toast.LENGTH_SHORT).show();
        }

        else{
            userDetails.setName(name);
            userDetails.setEmail(email);
            userDetails.setUsername(username);
            userDetails.setPassword(password);
            if(v.getId()==R.id.signUpButtonID){
                long rowID = databaseHelper.insertData(userDetails);

                if(rowID>0){
                    Toast.makeText(getApplicationContext(), "Registration successful ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registration is not successful", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}