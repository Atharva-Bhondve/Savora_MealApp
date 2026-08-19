package com.example.savora;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalInfoActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtEmail;
    TextView txtPhone;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);

        sharedPreferences =
                getSharedPreferences("UserData", MODE_PRIVATE);

        String name =
                sharedPreferences.getString("name", "");

        String email =
                sharedPreferences.getString("email", "");

        String phone =
                sharedPreferences.getString("phone", "");

        txtName.setText(name);
        txtEmail.setText(email);
        txtPhone.setText(phone);
    }
}