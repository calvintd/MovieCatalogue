package com.made.calvintd.moviecatalogue.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.activity.MovieDetailsActivity;
import com.made.calvintd.moviecatalogue.adapter.MovieAdapter;
import com.made.calvintd.moviecatalogue.itemmodel.Movie;
import com.made.calvintd.moviecatalogue.model.MovieModel;
import com.made.calvintd.moviecatalogue.presenter.MoviePresenter;
import com.made.calvintd.moviecatalogue.recyclerviewsupport.ItemClickSupport;
import com.made.calvintd.moviecatalogue.view.MovieView;
import com.made.calvintd.moviecatalogue.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieView {
    @BindView(R.id.pb_movies) ProgressBar progressBar;
    @BindView(R.id.rv_movies) RecyclerView recyclerView;
    MoviePresenter moviePresenter = new MoviePresenter(this);
    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieAdapter adapter = new MovieAdapter();
    private MovieViewModel movieViewModel;
    SearchView searchView;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);
        searchView = Objects.requireNonNull(getActivity()).findViewById(R.id.main_searchview);

        recyclerView.setVisibility(View.INVISIBLE);

        movieViewModel = ViewModelProviders.of( this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, getMoviesObserver);

        if (movieViewModel.getMovies().getValue() == null) {
            moviePresenter.getData(getActivity(), movies);
        } else {
            movies = movieViewModel.getMovies().getValue();
            adapter.notifyDataSetChanged();
        }

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showDetails(movies.get(position));
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    private Observer<ArrayList<Movie>> getMoviesObserver = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                adapter.setListMovies(movies);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }
    };

    private void showDetails(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void showMovies(MovieModel model) {
        recyclerView.setAdapter(model.getMovieAdapter());
        adapter = model.getMovieAdapter();
        adapter.notifyDataSetChanged();
        movieViewModel.postMutableLiveData(movies);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}
