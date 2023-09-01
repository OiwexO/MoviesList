package com.iwex.movies.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.iwex.movies.data.remote.api.ApiFactory;
import com.iwex.movies.model.movie.Movie;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "MainViewModel";

    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isMoviesLoading = new MutableLiveData<>(false);

    private int moviesPage = 1;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel(@NonNull Application application) {
        super(application);
        loadMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<Boolean> getIsMoviesLoading() {
        return isMoviesLoading;
    }

    public void loadMovies() {
        Boolean isAlreadyLoading = isMoviesLoading.getValue();
        if (isAlreadyLoading != null && isAlreadyLoading) {
            return;
        }

        Disposable disposable = ApiFactory.apiService.loadMovies(moviesPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((disposable1) -> isMoviesLoading.setValue(true))
                .doAfterTerminate(() -> isMoviesLoading.setValue(false))
                .subscribe(
                        movieResponse -> {
                            List<Movie> currentMovies = movies.getValue();
                            if (currentMovies != null) {
                                currentMovies.addAll(movieResponse.getMovies());
                                movies.setValue(currentMovies);
                            } else {
                                movies.setValue(movieResponse.getMovies());
                            }
                            moviesPage++;
                        },
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
