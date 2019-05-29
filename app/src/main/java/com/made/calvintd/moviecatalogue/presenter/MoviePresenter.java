package com.made.calvintd.moviecatalogue.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.made.calvintd.moviecatalogue.adapter.MovieAdapter;
import com.made.calvintd.moviecatalogue.itemmodel.Movie;
import com.made.calvintd.moviecatalogue.itemmodel.MovieDetailsResponse;
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
    private ApiInterface apiInterface;

    public MoviePresenter(MovieView view) {
        this.view = view;
    }

    public void initMovies(final Context context, final ArrayList<Movie> movies, RecyclerView rv) {
        apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<MovieListResponse> call = apiInterface.getNowPlayingMoviesList();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                MovieListResponse movieListResponses = response.body();
                final ArrayList<MovieDetailsResponse> movieDetailsResponses = new ArrayList<>();

                assert movieListResponses != null;
                List<MovieListResponse.Results> results = movieListResponses.getResults();

                ArrayList<Integer> resultsId = new ArrayList<>();
                for(MovieListResponse.Results result: results) {
                    resultsId.add(result.getId());
                }

                for(Integer id: resultsId) {
                    Call<MovieDetailsResponse> callDetails = apiInterface.getMovieDetails(id);

                    callDetails.enqueue(new Callback<MovieDetailsResponse>() {
                        @Override
                        public void onResponse(Call<MovieDetailsResponse> call, Response<MovieDetailsResponse> response) {
                            movieDetailsResponses.add(response.body());
                        }

                        @Override
                        public void onFailure(Call<MovieDetailsResponse> call, Throwable t) {

                        }
                    });
                }

                for(MovieDetailsResponse mdr: movieDetailsResponses) {
                    movies.add(new Movie(mdr.getId(), mdr.getOverview(), mdr.getPosterPath(), mdr.getReleaseDate(), mdr.getRuntime(),
                            mdr.getTitle(), mdr.getVoteAverage(), mdr.getVoteCount()));
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

            }
        });

        MovieAdapter movieAdapter = new MovieAdapter(context);
        movieAdapter.setListMovies(movies);
        MovieModel model = new MovieModel(movieAdapter);
        view.showMovies(model);
    }
}
