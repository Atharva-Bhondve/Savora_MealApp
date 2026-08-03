package com.example.savora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.savora.database.AppDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    TextView txtName, txtEmail, txtFavorites;

    Button btnLogout;

    LinearLayout layoutPersonal, layoutSettings, layoutFavorite;

    BottomNavigationView bottomNavigation;

    SharedPreferences preferences;

    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtFavorites = findViewById(R.id.txtFavorites);

        btnLogout = findViewById(R.id.btnLogout);

        layoutPersonal = findViewById(R.id.layoutPersonal);
        layoutSettings = findViewById(R.id.layoutSettings);
        layoutFavorite = findViewById(R.id.layoutFavorite);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        preferences = getSharedPreferences("User", MODE_PRIVATE);

        database = AppDatabase.getInstance(this);

        String name = preferences.getString("name", "Guest User");
        String email = preferences.getString("email", "guest@gmail.com");

        txtName.setText(name);
        txtEmail.setText(email);

        int count = database.favoriteDao().getAllFavorites().size();
        txtFavorites.setText("❤️ Favorite Meals : " + count);

        layoutFavorite.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this,
                    FavoriteActivity.class));
        });

        layoutSettings.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this,
                    SettingsActivity.class));
        });

        layoutPersonal.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this,
                    PersonalInfoActivity.class));
        });

        btnLogout.setOnClickListener(v -> {

            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(ProfileActivity.this,
                    LoginActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();

        });

        bottomNavigation.setSelectedItemId(R.id.nav_profile);

        bottomNavigation.setOnItemSelectedListener(item -> {

            if(item.getItemId()==R.id.nav_home){

                startActivity(new Intent(ProfileActivity.this,
                        MainActivity.class));

                finish();

                return true;
            }

            if(item.getItemId()==R.id.nav_favorite){

                startActivity(new Intent(ProfileActivity.this,
                        FavoriteActivity.class));

                finish();

                return true;
            }

            return true;

        });
        layoutPersonal.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this,
                    PersonalInfoActivity.class));
        });

    }
}