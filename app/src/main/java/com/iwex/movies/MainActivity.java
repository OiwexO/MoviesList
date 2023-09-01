package com.iwex.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iwex.movies.view.MovieDetailsActivity;
import com.iwex.movies.view.adapter.MoviesAdapter;
import com.iwex.movies.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int RECYCLER_SPAN_COUNT = 2;

    private MainViewModel viewModel;

    private ProgressBar progressBarLoading;

    private RecyclerView recyclerViewMovies;

    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getMovies().observe(this, movies -> moviesAdapter.setMovies(movies));
        viewModel.getIsMoviesLoading().observe(
                this,
                isLoading -> progressBarLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE)
        );
    }

    private void initViews() {
        progressBarLoading = findViewById(R.id.progressBarLoading);
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setOnReachListEndListener(() -> viewModel.loadMovies());
        moviesAdapter.setOnMovieClickListener((movie) -> {
            Intent intent = MovieDetailsActivity.newIntent(MainActivity.this, movie);
            startActivity(intent);
        });
        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, RECYCLER_SPAN_COUNT));
    }
}