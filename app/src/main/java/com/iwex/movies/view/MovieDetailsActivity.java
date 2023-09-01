package com.iwex.movies.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iwex.movies.R;
import com.iwex.movies.model.movie.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "movie";

    private ImageView imageViewDetailsPoster;

    private TextView textViewDetailsName;

    private TextView textViewDetailsYear;

    private TextView textViewDetailsDescription;

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
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewDetailsPoster);
        textViewDetailsName.setText(movie.getName());
        textViewDetailsYear.setText(String.valueOf(movie.getYear()));
        textViewDetailsDescription.setText(movie.getDescription());

    }

    private void initViews() {
        imageViewDetailsPoster = findViewById(R.id.imageViewDetailsPoster);
        textViewDetailsName = findViewById(R.id.textViewDetailsName);
        textViewDetailsYear = findViewById(R.id.textViewDetailsYear);
        textViewDetailsDescription = findViewById(R.id.textViewDetailsDescription);
    }
}