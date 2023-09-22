package com.iwex.movies.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iwex.movies.R;
import com.iwex.movies.view.adapter.MoviesAdapter;
import com.iwex.movies.viewmodel.FavouriteMoviesViewModel;

public class FavouriteMoviesActivity extends AppCompatActivity {

    private static final int RECYCLER_SPAN_COUNT = 2;

    private RecyclerView recyclerViewMovies;

    private MoviesAdapter moviesAdapter;

    private FavouriteMoviesViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, FavouriteMoviesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);
        initViews();
        viewModel = new ViewModelProvider(this).get(FavouriteMoviesViewModel.class);
        viewModel.getAllFavouriteMovies().observe(
                this,
                movies -> moviesAdapter.setMovies(movies)
        );
    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setOnMovieClickListener((movie) -> {
            Intent intent = MovieDetailsActivity.newIntent(FavouriteMoviesActivity.this, movie);
            startActivity(intent);
        });
        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, RECYCLER_SPAN_COUNT));
    }
}