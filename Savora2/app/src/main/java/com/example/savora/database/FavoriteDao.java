package com.example.savora.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(FavoriteMeal meal);

    @Delete
    void delete(FavoriteMeal meal);

    @Query("SELECT * FROM favorite_meals")
    List<FavoriteMeal> getAllFavorites();
}