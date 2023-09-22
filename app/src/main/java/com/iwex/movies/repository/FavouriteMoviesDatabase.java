package com.iwex.movies.repository;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.iwex.movies.model.movie.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class FavouriteMoviesDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "favourite_movies.db";
    private static FavouriteMoviesDatabase instance = null;

    public static FavouriteMoviesDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    FavouriteMoviesDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract FavouriteMoviesDao favouriteMoviesDao();
}
