package com.example.savora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvSignup;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tvSignup);

        sharedPreferences =
                getSharedPreferences("UserData", MODE_PRIVATE);


        // Login
        btnLogin.setOnClickListener(v -> {

            String email =
                    etEmail.getText().toString().trim();

            String password =
                    etPassword.getText().toString().trim();

            String savedEmail =
                    sharedPreferences.getString("email", "");

            String savedPassword =
                    sharedPreferences.getString("password", "");


            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                        LoginActivity.this,
                        "Please enter email and password",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            if (email.equals(savedEmail)
                    && password.equals(savedPassword)) {

                // Login successful
                sharedPreferences.edit()
                        .putBoolean("isLoggedIn", true)
                        .apply();

                Toast.makeText(
                        LoginActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent =
                        new Intent(
                                LoginActivity.this,
                                MainActivity.class
                        );

                startActivity(intent);
                finish();

            } else {

                Toast.makeText(
                        LoginActivity.this,
                        "Invalid Email or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        // Create Account
        tvSignup.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            LoginActivity.this,
                            RegisterActivity.class
                    );

            startActivity(intent);
        });
    }
}