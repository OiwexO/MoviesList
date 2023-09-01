package com.iwex.movies.model.trailer;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Videos {

    @SerializedName("trailers")
    private final List<Trailer> trailers;

    public Videos(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    @NonNull
    @Override
    public String toString() {
        return "Videos{" +
                "trailers=" + trailers +
                '}';
    }

}
