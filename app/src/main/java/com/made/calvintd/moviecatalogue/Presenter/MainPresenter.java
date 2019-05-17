package com.made.calvintd.moviecatalogue.Presenter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.made.calvintd.moviecatalogue.Model.MainModel;
import com.made.calvintd.moviecatalogue.View.MainView;
import com.made.calvintd.moviecatalogue.Model.Movie;
import com.made.calvintd.moviecatalogue.Adapter.MovieAdapter;
import com.made.calvintd.moviecatalogue.R;

import java.util.ArrayList;

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void initMovies(Context context, ArrayList<Movie> movies, MovieAdapter movieAdapter, RecyclerView rv, int catId) {
        String[] titles = context.getResources().getStringArray(R.array.movie_title);
        String[] descriptions = context.getResources().getStringArray(R.array.movie_description);
        TypedArray posters = context.getResources().obtainTypedArray(R.array.movie_poster);
        String[] years = context.getResources().getStringArray(R.array.movie_year);
        String[] figures = context.getResources().getStringArray(R.array.movie_figure);
        Integer[] categories = new Integer[context.getResources().getStringArray(R.array.movie_category).length];
        parseIntCategoryLoop(context.getResources().getStringArray(R.array.movie_category), categories);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));

        for(int i = 0; i < titles.length; i++) {
            if(categories[i] == catId) {
                Movie m = new Movie();
                m.setName(titles[i]);
                m.setDescription(descriptions[i]);
                m.setPoster(posters.getResourceId(i, -1));
                m.setYear(years[i]);
                m.setFigure(figures[i]);
                m.setCategory(categories[i]);
                movies.add(m);
            }
        }

        posters.recycle();
        movieAdapter.setListMovies(movies);
        MainModel model = new MainModel(movies, movieAdapter);
        view.showMovies(model);
    }

    private void parseIntCategoryLoop(String[] catStr, Integer[] catInt) {
        int i = 0;
        for(String cat: catStr) {
            catInt[i++] = Integer.parseInt(cat);
        }
    }
}
