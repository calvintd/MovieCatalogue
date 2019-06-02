package com.made.calvintd.moviecatalogue.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.made.calvintd.moviecatalogue.R;
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

    public void initMovies(final Context context, final ArrayList<Movie> movies) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<MovieListResponse> call = apiInterface.getNowPlayingMoviesList();

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.isSuccessful()) {
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

                            MovieAdapter movieAdapter = new MovieAdapter(context);
                            movieAdapter.setListMovies(movies);
                            movieAdapter.notifyDataSetChanged();
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
                    }
                    Toast.makeText(context, context.getResources().getString(R.string.api_data_failure), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.d("APIFailure", t.getCause().toString());
                Toast.makeText(context, context.getResources().getString(R.string.api_data_failure), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
