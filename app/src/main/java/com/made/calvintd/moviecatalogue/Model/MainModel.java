package com.made.calvintd.moviecatalogue.Model;

import com.made.calvintd.moviecatalogue.Adapter.MovieAdapter;

public class MainModel {
    private MovieAdapter adapter;

    public MainModel(MovieAdapter adapter) {
        this.adapter = adapter;
    }

    public MovieAdapter getAdapter() {
        return adapter;
    }
}
