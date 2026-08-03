package com.example.savora.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_meals")
public class FavoriteMeal {

    @PrimaryKey
    @NonNull
    public String idMeal;

    public String mealName;
    public String category;
    public String area;
    public String image;
}