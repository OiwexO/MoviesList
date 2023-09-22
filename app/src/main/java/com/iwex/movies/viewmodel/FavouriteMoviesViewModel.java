package com.iwex.movies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.iwex.movies.model.movie.Movie;
import com.iwex.movies.repository.FavouriteMoviesDao;
import com.iwex.movies.repository.FavouriteMoviesDatabase;

import java.util.List;

public class FavouriteMoviesViewModel extends AndroidViewModel {

    private final FavouriteMoviesDao favouriteMoviesDao;

    public FavouriteMoviesViewModel(@NonNull Application application) {
        super(application);
        favouriteMoviesDao = FavouriteMoviesDatabase.getInstance(application).favouriteMoviesDao();
    }


    public LiveData<List<Movie>> getAllFavouriteMovies() {
        return favouriteMoviesDao.getAllFavouriteMovies();
    }
}
