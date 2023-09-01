package com.iwex.movies.model.review;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("author")
    private final String author;

    @SerializedName("review")
    private final String text;

    @SerializedName("type")
    private final String type;

    public Review(String author, String text, String type) {
        this.author = author;
        this.text = text;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    @NonNull
    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                //", text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
