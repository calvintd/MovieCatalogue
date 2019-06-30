package com.made.calvintd.moviecatalogue.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.adapter.FavoriteTvShowAdapter;
import com.made.calvintd.moviecatalogue.dao.CatalogueDao;
import com.made.calvintd.moviecatalogue.db.CatalogueDatabase;
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteTvShow;
import com.made.calvintd.moviecatalogue.recyclerviewsupport.ItemClickSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment {
    @BindView(R.id.rv_fav_tv_shows) RecyclerView recyclerView;
    private FavoriteTvShowAdapter adapter = new FavoriteTvShowAdapter();

    public FavoriteTvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
        ButterKnife.bind(this, view);

        final CatalogueDao catalogueDao = CatalogueDatabase.getDatabase(this.getContext()).catalogueDao();

        List<FavoriteTvShow> favoriteTvShows = catalogueDao.getFavoriteTvShows();
        adapter.setListFavoriteTvShows(favoriteTvShows);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

}
