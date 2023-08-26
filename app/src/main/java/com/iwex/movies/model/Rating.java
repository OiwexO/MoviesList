package com.iwex.movies.model;

import com.google.gson.annotations.SerializedName;

public class Rating {

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

    @Override
    public String toString() {
        return "Rating{" +
                "kp=" + kp +
                ", imdb=" + imdb +
                '}';
    }
}
