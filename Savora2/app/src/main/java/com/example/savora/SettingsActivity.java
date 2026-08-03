package com.example.savora;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch switchDarkMode, switchNotification;
    Button btnLanguage, btnContact, btnAbout, btnRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchDarkMode = findViewById(R.id.switchDarkMode);
        switchNotification = findViewById(R.id.switchNotification);

        btnLanguage = findViewById(R.id.btnLanguage);
        btnContact = findViewById(R.id.btnContact);
        btnAbout = findViewById(R.id.btnAbout);
        btnRate = findViewById(R.id.btnRate);

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked)
                Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Dark Mode Disabled", Toast.LENGTH_SHORT).show();

        });

        switchNotification.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked)
                Toast.makeText(this, "Notifications ON", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Notifications OFF", Toast.LENGTH_SHORT).show();

        });

        btnLanguage.setOnClickListener(v ->
                Toast.makeText(this,
                        "Language feature coming soon",
                        Toast.LENGTH_SHORT).show());

        btnContact.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:support@savora.com"));
            startActivity(intent);

        });

        btnAbout.setOnClickListener(v ->

                Toast.makeText(this,
                        "Savora v1.0\nRecipe App using Java + Room + Retrofit",
                        Toast.LENGTH_LONG).show());

        btnRate.setOnClickListener(v ->

                Toast.makeText(this,
                        "Thanks for rating Savora ❤️",
                        Toast.LENGTH_SHORT).show());

    }
}