package com.made.calvintd.moviecatalogue.Presenter;

import android.content.Context;
import android.content.res.TypedArray;

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

    public void initMovies(Context context, ArrayList<Movie> movies) {
        String[] titles = context.getResources().getStringArray(R.array.movie_title);
        String[] descriptions = context.getResources().getStringArray(R.array.movie_description);
        TypedArray posters = context.getResources().obtainTypedArray(R.array.movie_poster);
        String[] years = context.getResources().getStringArray(R.array.movie_year);
        String[] directors = context.getResources().getStringArray(R.array.movie_director);
        MovieAdapter adapter = new MovieAdapter(context);

        for(int i = 0; i < titles.length; i++) {
            Movie m = new Movie();
            m.setName(titles[i]);
            m.setDescription(descriptions[i]);
            m.setPoster(posters.getResourceId(i, -1));
            m.setYear(years[i]);
            m.setDirector(directors[i]);
            movies.add(m);
        }

        posters.recycle();
        adapter.setMovies(movies);
        MainModel model = new MainModel(adapter);
        view.showMovies(model);
    }
}
