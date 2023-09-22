package com.iwex.movies.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.iwex.movies.data.remote.api.ApiFactory;
import com.iwex.movies.model.movie.Movie;
import com.iwex.movies.model.review.Review;
import com.iwex.movies.model.review.ReviewResponse;
import com.iwex.movies.model.trailer.Trailer;
import com.iwex.movies.repository.FavouriteMoviesDao;
import com.iwex.movies.repository.FavouriteMoviesDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsViewModel extends AndroidViewModel {

    private static final String TAG = "MovieDetailsViewModel";

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();

    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();

    private final FavouriteMoviesDao favouriteMoviesDao;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        favouriteMoviesDao = FavouriteMoviesDatabase.getInstance(application).favouriteMoviesDao();
    }

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public LiveData<Movie> getFavouriteMovie(int movieId) {
        return favouriteMoviesDao.getFavouriteMovieById(movieId);
    }

    public void insertMovie(Movie movie) {
        Disposable disposable = favouriteMoviesDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void deleteMovie(int movieId) {
        Disposable disposable = favouriteMoviesDao.deleteMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void loadTrailers(int movieId) {
        Disposable disposable = ApiFactory.apiService.loadTrailers(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(trailerResponse ->
                        trailerResponse.getTrailerDocs().get(0).getVideos().getTrailers()
                )
                .subscribe(
                        trailers::setValue,
                        throwable -> Log.e(TAG, throwable.toString())
                );
        compositeDisposable.add(disposable);
    }

    public void loadReviews(int movieId) {
        Disposable disposable = ApiFactory.apiService.loadReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ReviewResponse::getReviews)
                .subscribe(
                        reviews::setValue,
                        throwable -> Log.e(TAG, throwable.toString())
                );
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
