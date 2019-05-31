package com.made.calvintd.moviecatalogue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.made.calvintd.moviecatalogue.activity.MovieDetailsActivity;
import com.made.calvintd.moviecatalogue.model.MovieModel;
import com.made.calvintd.moviecatalogue.itemmodel.Movie;
import com.made.calvintd.moviecatalogue.presenter.MoviePresenter;
import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.recyclerviewsupport.ItemClickSupport;
import com.made.calvintd.moviecatalogue.view.MovieView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieView {
    @BindView(R.id.pb_movies) ProgressBar progressBar;
    @BindView(R.id.rv_movies) RecyclerView recyclerView;
    private ArrayList<Movie> movies = new ArrayList<>();
    private final MoviePresenter presenter = new MoviePresenter(this);

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        ButterKnife.bind(this, view);

        recyclerView.setVisibility(View.INVISIBLE);

        presenter.initMovies(this.getContext(), movies, recyclerView);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showDetails(movies.get(position));
            }
        });

        return view;
    }

    private void showDetails(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void showMovies(MovieModel model) {
        recyclerView.setAdapter(model.getMovieAdapter());
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
