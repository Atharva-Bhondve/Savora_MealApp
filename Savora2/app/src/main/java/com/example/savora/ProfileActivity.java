package com.example.savora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    TextView txtUserName, txtUserEmail;

    LinearLayout layoutPersonalInfo;
    LinearLayout layoutSettings;
    LinearLayout layoutFavorites;

    Button btnLogout;

    BottomNavigationView bottomNavigation;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtUserName = findViewById(R.id.txtUserName);
        txtUserEmail = findViewById(R.id.txtUserEmail);

        layoutPersonalInfo = findViewById(R.id.layoutPersonalInfo);
        layoutSettings = findViewById(R.id.layoutSettings);
        layoutFavorites = findViewById(R.id.layoutFavorites);

        btnLogout = findViewById(R.id.btnLogout);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Get saved account information
        sharedPreferences =
                getSharedPreferences("UserData", MODE_PRIVATE);

        String name =
                sharedPreferences.getString("name", "User Name");

        String email =
                sharedPreferences.getString("email", "user@email.com");

        // Show Name and Email
        txtUserName.setText(name);
        txtUserEmail.setText(email);


        // Personal Information
        layoutPersonalInfo.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ProfileActivity.this,
                    PersonalInfoActivity.class
            );

            startActivity(intent);

        });

        // Settings
        layoutSettings.setOnClickListener(v -> {

            android.widget.Toast.makeText(
                    ProfileActivity.this,
                    "Settings",
                    android.widget.Toast.LENGTH_SHORT
            ).show();

        });


        // Favorites
        layoutFavorites.setOnClickListener(v -> {

            startActivity(new Intent(
                    ProfileActivity.this,
                    FavoriteActivity.class
            ));

        });


        // Logout
        btnLogout.setOnClickListener(v -> {

            sharedPreferences.edit()
                    .putBoolean("isLoggedIn", false)
                    .apply();

            Intent intent = new Intent(
                    ProfileActivity.this,
                    LoginActivity.class
            );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);
            finish();

        });


        // Bottom Navigation
        bottomNavigation.setSelectedItemId(R.id.nav_profile);

        bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {

                startActivity(new Intent(
                        ProfileActivity.this,
                        MainActivity.class
                ));
                return true;

            } else if (item.getItemId() == R.id.nav_search) {

                startActivity(new Intent(
                        ProfileActivity.this,
                        SearchActivity.class
                ));
                return true;

            } else if (item.getItemId() == R.id.nav_favorite) {

                startActivity(new Intent(
                        ProfileActivity.this,
                        FavoriteActivity.class
                ));
                return true;

            } else if (item.getItemId() == R.id.nav_profile) {

                return true;
            }

            return false;
        });
    }
}