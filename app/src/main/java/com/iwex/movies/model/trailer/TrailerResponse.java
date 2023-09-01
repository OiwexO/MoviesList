package com.iwex.movies.model.trailer;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerResponse {

    @SerializedName("docs")
    private final List<TrailerDoc> trailerDocs;

    public TrailerResponse(List<TrailerDoc> trailerDocs) {
        this.trailerDocs = trailerDocs;
    }

    public List<TrailerDoc> getTrailerDocs() {
        return trailerDocs;
    }

    @NonNull
    @Override
    public String toString() {
        return "TrailerResponse{" +
                "trailerDocs=" + trailerDocs +
                '}';
    }
}
