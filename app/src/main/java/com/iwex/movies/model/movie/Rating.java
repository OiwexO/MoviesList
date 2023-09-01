package com.iwex.movies.model.movie;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {

    @SerializedName("kp")
    private final double kp;

    @SerializedName("imdb")
    private final double imdb;

    public Rating(double kp, double imdb) {
        this.kp = kp;
        this.imdb = imdb;
    }

    public double getKp() {
        return kp;
    }

    public double getImdb() {
        return imdb;
    }

    @NonNull
    @Override
    public String toString() {
        return "Rating{" +
                "kp=" + kp +
                ", imdb=" + imdb +
                '}';
    }
}
