package com.iwex.movies;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        viewModel.loadMovies();
    }

    private void initViews() {
        progressBarLoading = findViewById(R.id.progressBarLoading);
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        moviesAdapter = new MoviesAdapter();
        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, RECYCLER_SPAN_COUNT));
    }
}