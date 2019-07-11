package com.made.calvintd.moviecatalogue.model;

import com.made.calvintd.moviecatalogue.adapter.MovieAdapter;

public class MovieModel {
    private MovieAdapter adapter;

    public MovieModel(MovieAdapter adapter) {
        this.adapter = adapter;
    }

    public MovieAdapter getMovieAdapter() {
        return adapter;
    }
}