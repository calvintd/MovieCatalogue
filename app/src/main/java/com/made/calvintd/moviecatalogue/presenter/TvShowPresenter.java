package com.made.calvintd.moviecatalogue.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.made.calvintd.moviecatalogue.adapter.TvShowAdapter;
import com.made.calvintd.moviecatalogue.itemmodel.TvShow;
import com.made.calvintd.moviecatalogue.model.TvShowModel;
import com.made.calvintd.moviecatalogue.view.TvShowView;

import java.util.ArrayList;

public class TvShowPresenter {
    private TvShowView view;

    public TvShowPresenter(TvShowView view) {
        this.view = view;
    }

    public void initMovies(Context context, ArrayList<TvShow> tvShows, RecyclerView rv) {
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));

        TvShowAdapter tvShowAdapter = new TvShowAdapter(context);
        tvShowAdapter.setListTvShow(tvShows);
        TvShowModel model = new TvShowModel(tvShowAdapter);
        view.showTvShows(model);
    }
}
