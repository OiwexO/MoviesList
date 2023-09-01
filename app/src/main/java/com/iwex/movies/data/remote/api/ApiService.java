package com.iwex.movies.data.remote.api;

import com.iwex.movies.BuildConfig;
import com.iwex.movies.model.movie.MovieResponse;
import com.iwex.movies.model.review.ReviewResponse;
import com.iwex.movies.model.trailer.TrailerResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers("X-API-KEY:" + BuildConfig.API_KEY)
    @GET("v1.3/movie?field=rating.kp&search=4-8&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @Headers("X-API-KEY:" + BuildConfig.API_KEY)
    @GET("v1.3/movie?selectFields=videos.trailers.name&selectFields=videos.trailers.url")
    Single<TrailerResponse> loadTrailers(@Query("id") int movieId);

    @Headers("X-API-KEY:" + BuildConfig.API_KEY)
    @GET("v1/review?selectFields=author&selectFields=type&selectFields=review")
    Single<ReviewResponse> loadReviews(@Query("movieId") int movieId);
}
