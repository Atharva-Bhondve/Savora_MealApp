package com.example.savora;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savora.database.AppDatabase;
import com.example.savora.database.FavoriteMeal;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView rvFavorites;

    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rvFavorites = findViewById(R.id.rvFavorites);

        rvFavorites.setLayoutManager(new LinearLayoutManager(this));

        database = AppDatabase.getInstance(this);

        List<FavoriteMeal> favoriteMeals =
                database.favoriteDao().getAllFavorites();

        FavoriteAdapter adapter =
                new FavoriteAdapter(favoriteMeals);

        rvFavorites.setAdapter(adapter);
    }
}