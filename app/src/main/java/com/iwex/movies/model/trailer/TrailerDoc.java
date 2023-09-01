package com.iwex.movies.model.trailer;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TrailerDoc {

    @SerializedName("videos")
    private final Videos videos;

    public TrailerDoc(Videos videos) {
        this.videos = videos;
    }

    public Videos getVideos() {
        return videos;
    }

    @NonNull
    @Override
    public String toString() {
        return "TrailerDoc{" +
                "videos=" + videos +
                '}';
    }

}
