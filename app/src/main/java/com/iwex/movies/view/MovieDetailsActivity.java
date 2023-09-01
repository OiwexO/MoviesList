package com.iwex.movies.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iwex.movies.R;
import com.iwex.movies.model.movie.Movie;
import com.iwex.movies.view.adapter.ReviewsAdapter;
import com.iwex.movies.view.adapter.TrailersAdapter;
import com.iwex.movies.viewmodel.MovieDetailsViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "movie";

    private static final String TAG = "MovieDetailsActivity";

    private MovieDetailsViewModel viewModel;

    private ImageView imageViewDetailsPoster;

    private TextView textViewDetailsName;

    private TextView textViewDetailsYear;

    private TextView textViewDetailsDescription;

    private RecyclerView recyclerViewTrailers;

    private RecyclerView recyclerViewReviews;

    private TrailersAdapter trailersAdapter;

    private ReviewsAdapter reviewsAdapter;

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        initViews();
        viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewDetailsPoster);
        textViewDetailsName.setText(movie.getName());
        textViewDetailsYear.setText(String.valueOf(movie.getYear()));
        textViewDetailsDescription.setText(movie.getDescription());

        viewModel.loadTrailers(movie.getId());
        viewModel.getTrailers().observe(this, trailers -> trailersAdapter.setTrailers(trailers));
        viewModel.loadReviews(movie.getId());
        viewModel.getReviews().observe(this, reviews -> {
            Log.d(TAG, reviews.toString());
            reviewsAdapter.setReviews(reviews);
        });
    }

    private void initViews() {
        imageViewDetailsPoster = findViewById(R.id.imageViewDetailsPoster);
        textViewDetailsName = findViewById(R.id.textViewDetailsName);
        textViewDetailsYear = findViewById(R.id.textViewDetailsYear);
        textViewDetailsDescription = findViewById(R.id.textViewDetailsDescription);

        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        trailersAdapter = new TrailersAdapter();
        trailersAdapter.setOnTrailerClickListener(trailer -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(trailer.getUrl()));
            startActivity(intent);
        });
        recyclerViewTrailers.setAdapter(trailersAdapter);

        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        reviewsAdapter = new ReviewsAdapter();
        recyclerViewReviews.setAdapter(reviewsAdapter);
    }

}