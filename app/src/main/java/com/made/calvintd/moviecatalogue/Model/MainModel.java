package com.made.calvintd.moviecatalogue.Model;

import com.made.calvintd.moviecatalogue.Adapter.MovieAdapter;
import java.util.ArrayList;

public class MainModel {
    private ArrayList<Movie> listMovies;
    private MovieAdapter adapter;

    public MainModel(ArrayList<Movie> listMovies, MovieAdapter adapter) {
        this.listMovies = listMovies;
        this.adapter = adapter;
    }

    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public MovieAdapter getAdapter() {
        return adapter;
    }
}
