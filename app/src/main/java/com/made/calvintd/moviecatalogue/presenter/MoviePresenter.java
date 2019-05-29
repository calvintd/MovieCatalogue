package com.made.calvintd.moviecatalogue.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.made.calvintd.moviecatalogue.adapter.MovieAdapter;
import com.made.calvintd.moviecatalogue.itemmodel.Movie;
import com.made.calvintd.moviecatalogue.model.MovieModel;
import com.made.calvintd.moviecatalogue.view.MovieView;

import java.util.ArrayList;

public class MoviePresenter {
    private MovieView view;

    public MoviePresenter(MovieView view) {
        this.view = view;
    }

    public void initMovies(Context context, ArrayList<Movie> movies, RecyclerView rv) {
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));

        MovieAdapter movieAdapter = new MovieAdapter(context);
        movieAdapter.setListMovies(movies);
        MovieModel model = new MovieModel(movieAdapter);
        view.showMovies(model);
    }
}
