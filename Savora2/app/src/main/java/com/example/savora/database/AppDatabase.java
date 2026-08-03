package com.example.savora.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteMeal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){

        if(instance == null){

            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "favorite_database"
                    )
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}