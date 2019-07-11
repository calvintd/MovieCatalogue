package com.made.calvintd.moviecatalogue.presenter;

import android.util.Log;

import com.made.calvintd.moviecatalogue.adapter.MovieAdapter;
import com.made.calvintd.moviecatalogue.itemmodel.Movie;
import com.made.calvintd.moviecatalogue.itemmodel.MovieListResponse;
import com.made.calvintd.moviecatalogue.model.MovieModel;
import com.made.calvintd.moviecatalogue.restapi.ApiInterface;
import com.made.calvintd.moviecatalogue.restapi.RetrofitInstance;
import com.made.calvintd.moviecatalogue.view.MovieView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {
    private MovieView view;

    public MoviePresenter(MovieView view) {
        this.view = view;
    }

    public void getData(ArrayList<Movie> movies) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<MovieListResponse> call = apiInterface.getNowPlayingMoviesList();

        handleCall(call, movies);
    }

    public void getDataByName(ArrayList<Movie> movies, String query) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<MovieListResponse> call = apiInterface.getNowPlayingMoviesListByName(query);

        handleCall(call, movies);
    }

    private void handleCall(Call<MovieListResponse> call, final ArrayList<Movie> movies) {
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if(response.isSuccessful()) {
                    MovieListResponse movieListResponse = response.body();

                    try {
                        if (movieListResponse != null) {
                            List<MovieListResponse.Results> results = movieListResponse.getResults();

                            for (MovieListResponse.Results result : results) {
                                if (result.getPosterPath() != null) {
                                    movies.add(new Movie(result.getId(), result.getOverview(), "https://image.tmdb.org/t/p/w185" +
                                            result.getPosterPath(), result.getReleaseDate(), result.getTitle(), result.getVoteAverage(),
                                            result.getVoteCount()));
                                } else {
                                    movies.add(new Movie(result.getId(), result.getOverview(), null, result.getReleaseDate(),
                                            result.getTitle(), result.getVoteAverage(), result.getVoteCount()));
                                }
                            }

                            MovieAdapter movieAdapter = new MovieAdapter();
                            movieAdapter.setListMovies(movies);
                            MovieModel model = new MovieModel(movieAdapter);
                            view.showMovies(model);
                        }
                    } finally {
                        if (movieListResponse != null) {
                            movieListResponse.toString();
                        }
                    }
                } else {
                    if (response.errorBody() != null) {
                        Log.d("APIFailure", response.errorBody().toString());
                        view.showError();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.d("APIFailure", t.getCause().toString());
                view.showError();
            }
        });
    }
}
