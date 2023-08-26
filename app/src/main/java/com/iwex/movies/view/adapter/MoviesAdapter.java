package com.iwex.movies.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iwex.movies.R;
import com.iwex.movies.model.Movie;
import com.iwex.movies.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();

    private OnReachListEndListener onReachListEndListener;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new MovieViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);
        double rating = MathUtils.round(movie.getRating().getKp(), 1);
        holder.textViewRating.setText(String.valueOf(rating));
        int ratingBackgroundId;
        if (rating > 7) {
            ratingBackgroundId = R.drawable.circle_movie_rating_high;
        } else if (rating > 5) {
            ratingBackgroundId = R.drawable.circle_movie_rating_medium;
        } else {
            ratingBackgroundId = R.drawable.circle_movie_rating_low;
        }
        holder.textViewRating.setBackgroundResource(ratingBackgroundId);

        if (position >= movies.size() - 10 && onReachListEndListener != null) {
            onReachListEndListener.onReachListEnd();
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setOnReachListEndListener(OnReachListEndListener onReachListEndListener) {
        this.onReachListEndListener = onReachListEndListener;
    }

    public interface OnReachListEndListener {
        void onReachListEnd();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewPoster;

        private final TextView textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
