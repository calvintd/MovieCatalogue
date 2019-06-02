package com.made.calvintd.moviecatalogue.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.made.calvintd.moviecatalogue.itemmodel.TvShow;

import java.util.ArrayList;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TvShow>> mutableLiveData = new MutableLiveData<>();

    public void postMutableLiveData(ArrayList<TvShow> tvShows) {
        this.mutableLiveData.postValue(tvShows);
    }

    public LiveData<ArrayList<TvShow>> getTvShows() {
        return mutableLiveData;
    }
}
