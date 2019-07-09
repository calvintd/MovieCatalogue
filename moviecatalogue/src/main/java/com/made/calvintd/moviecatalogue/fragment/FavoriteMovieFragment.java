package com.made.calvintd.moviecatalogue.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.adapter.FavoriteMovieAdapter;
import com.made.calvintd.moviecatalogue.dao.CatalogueDao;
import com.made.calvintd.moviecatalogue.database.CatalogueDatabase;
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {
    @BindView(R.id.rv_fav_movies) RecyclerView recyclerView;
    private FavoriteMovieAdapter adapter = new FavoriteMovieAdapter();

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        ButterKnife.bind(this, view);

        final CatalogueDao catalogueDao = CatalogueDatabase.getDatabase(this.getContext()).catalogueDao();

        List<FavoriteMovie> favoriteMovies = catalogueDao.getFavoriteMovies();
        adapter.setListFavoriteMovies(favoriteMovies);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }
}
