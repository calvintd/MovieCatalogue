package com.made.calvintd.favoritemoviecatalogue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.made.calvintd.favoritemoviecatalogue.R;
import com.made.calvintd.favoritemoviecatalogue.adapter.FavoriteMovieAdapter;
import com.made.calvintd.favoritemoviecatalogue.itemmodel.FavoriteMovie;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.List;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        ButterKnife.bind(this, view);

        List<FavoriteMovie> favoriteMovies;

        return view;
    }
}
