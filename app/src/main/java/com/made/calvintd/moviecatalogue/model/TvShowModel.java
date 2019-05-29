package com.made.calvintd.moviecatalogue.model;

import com.made.calvintd.moviecatalogue.adapter.TvShowAdapter;

public class TvShowModel {
    private TvShowAdapter adapter;

    public TvShowModel(TvShowAdapter adapter) {
        this.adapter = adapter;
    }

    public TvShowAdapter getTvShowAdapter() {
        return adapter;
    }
}
