package com.made.calvintd.moviecatalogue.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.made.calvintd.moviecatalogue.Adapter.MovieAdapter;
import com.made.calvintd.moviecatalogue.Model.MainModel;
import com.made.calvintd.moviecatalogue.Model.Movie;
import com.made.calvintd.moviecatalogue.Presenter.MainPresenter;
import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.RecyclerViewSupport.ItemClickSupport;
import com.made.calvintd.moviecatalogue.View.DetailActivity;
import com.made.calvintd.moviecatalogue.View.MainView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment implements MainView {
    @BindView(R.id.rv_movies) RecyclerView rvMovies;
    private ArrayList<Movie> movies = new ArrayList<>();
    private final MainPresenter presenter = new MainPresenter(this);

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);

        ButterKnife.bind(this, view);

        MovieAdapter movieAdapter = new MovieAdapter(getActivity());
        presenter.initMovies(getActivity(), movies, movieAdapter, rvMovies, 2); //2 = TV shows

        ItemClickSupport.addTo(rvMovies).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showDetails(movies.get(position));
            }
        });

        return view;
    }

    private void showDetails(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void assignMovies(MainModel model) {
        movies = model.getListMovies();
    }

    @Override
    public void showMovies(MainModel model) {
        rvMovies.setAdapter(model.getAdapter());
    }
}