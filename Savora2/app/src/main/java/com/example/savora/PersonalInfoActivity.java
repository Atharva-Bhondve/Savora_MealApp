package com.example.savora;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalInfoActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone, etAddress;
    Button btnSave;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnSave = findViewById(R.id.btnSave);

        preferences = getSharedPreferences("User", MODE_PRIVATE);

        // Load saved data
        etName.setText(preferences.getString("name", ""));
        etEmail.setText(preferences.getString("email", ""));
        etPhone.setText(preferences.getString("phone", ""));
        etAddress.setText(preferences.getString("address", ""));

        btnSave.setOnClickListener(v -> {

            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("name", etName.getText().toString());
            editor.putString("email", etEmail.getText().toString());
            editor.putString("phone", etPhone.getText().toString());
            editor.putString("address", etAddress.getText().toString());

            editor.apply();

            Toast.makeText(this,
                    "Profile Updated Successfully",
                    Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}