package com.example.savora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone, etPassword;
    Button btnRegister;
    TextView tvLogin;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);

        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        sharedPreferences =
                getSharedPreferences("UserData", MODE_PRIVATE);

        // Create Account
        btnRegister.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty() ||
                    email.isEmpty() ||
                    phone.isEmpty() ||
                    password.isEmpty()) {

                Toast.makeText(
                        RegisterActivity.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // Save account details
            SharedPreferences.Editor editor =
                    sharedPreferences.edit();

            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("phone", phone);
            editor.putString("password", password);

            // User is already logged in after registration
            editor.putBoolean("isLoggedIn", true);

            editor.apply();

            Toast.makeText(
                    RegisterActivity.this,
                    "Account Created Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            // Directly open Home
            Intent intent =
                    new Intent(
                            RegisterActivity.this,
                            MainActivity.class
                    );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);
            finish();
        });
    }}