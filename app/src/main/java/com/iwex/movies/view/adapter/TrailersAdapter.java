package com.iwex.movies.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iwex.movies.R;
import com.iwex.movies.model.trailer.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {

    private List<Trailer> trailers = new ArrayList<>();

    private OnTrailerClickListener onTrailerClickListener;

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    public void setOnTrailerClickListener(OnTrailerClickListener onTrailerClickListener) {
        this.onTrailerClickListener = onTrailerClickListener;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.trailer_button_item,
                parent,
                false
        );
        return new TrailersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.textViewTrailerName.setText(trailer.getName());
        holder.imageViewPlayTrailer.setOnClickListener(v -> {
            if (onTrailerClickListener != null) {
                onTrailerClickListener.onTrailerClick(trailer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public interface OnTrailerClickListener {
        void onTrailerClick(Trailer trailer);
    }

    static class TrailersViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewTrailerName;

        private final ImageView imageViewPlayTrailer;

        public TrailersViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTrailerName = itemView.findViewById(R.id.textViewTrailerName);
            imageViewPlayTrailer = itemView.findViewById(R.id.imageViewPlayTrailer);
        }
    }
}
