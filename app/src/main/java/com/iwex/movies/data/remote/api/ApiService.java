package com.iwex.movies.data.remote.api;

import com.iwex.movies.BuildConfig;
import com.iwex.movies.model.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

//    @GET("movie?token=" + BuildConfig.API_KEY + "&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1")
    @GET("movie?token=" + BuildConfig.API_KEY + "&field=rating.kp&search=4-8&sortField=votes.kp&sortType=-1&limit=5")
    Single<MovieResponse> loadMovies();
}
