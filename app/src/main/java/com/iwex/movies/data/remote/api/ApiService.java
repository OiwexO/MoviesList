package com.iwex.movies.data.remote.api;

import com.iwex.movies.BuildConfig;
import com.iwex.movies.model.movie.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers("X-API-KEY:" + BuildConfig.API_KEY)
    @GET("movie?field=rating.kp&search=4-8&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);
}
