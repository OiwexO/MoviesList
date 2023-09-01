package com.iwex.movies.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.iwex.movies.R;
import com.iwex.movies.model.review.Review;
import com.iwex.movies.model.review.Type;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        Review review = reviews.get(position);
        String type = review.getType();

        @ColorRes int backgroundColorRes = android.R.color.holo_blue_dark;
        if (type != null) {
            switch (type) {
                case Type.POSITIVE:
                    backgroundColorRes = android.R.color.holo_green_light;
                    break;
                case Type.NEUTRAL:
                    backgroundColorRes = android.R.color.holo_orange_light;
                    break;
                case Type.NEGATIVE:
                    backgroundColorRes = android.R.color.holo_red_light;
                    break;
            }
        }

        @ColorInt int backgroundColor = ContextCompat.getColor(
                holder.itemView.getContext(),
                backgroundColorRes
        );
        holder.cardViewReviewBackground.setCardBackgroundColor(backgroundColor);
        holder.textViewReviewAuthor.setText(review.getAuthor());
        holder.textViewReviewText.setText(review.getText());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewsViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardViewReviewBackground;

        private final TextView textViewReviewAuthor;

        private final TextView textViewReviewText;

        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewReviewBackground = itemView.findViewById(R.id.cardViewReviewBackground);
            textViewReviewAuthor = itemView.findViewById(R.id.textViewReviewAuthor);
            textViewReviewText = itemView.findViewById(R.id.textViewReviewText);
        }
    }
}
