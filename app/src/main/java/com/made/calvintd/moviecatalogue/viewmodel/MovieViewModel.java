package com.made.calvintd.moviecatalogue.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.made.calvintd.moviecatalogue.itemmodel.Movie;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> mutableLiveData = new MutableLiveData<>();

    public void postMutableLiveData(ArrayList<Movie> movies) {
        this.mutableLiveData.postValue(movies);
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return mutableLiveData;
    }
}
