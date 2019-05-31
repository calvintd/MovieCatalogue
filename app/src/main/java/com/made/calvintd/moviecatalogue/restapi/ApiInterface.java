package com.made.calvintd.moviecatalogue.restapi;

import com.made.calvintd.moviecatalogue.itemmodel.MovieListResponse;
import com.made.calvintd.moviecatalogue.itemmodel.TvShowListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/3/movie/now_playing")
    Call<MovieListResponse> getNowPlayingMoviesList();

    @GET("/3/tv/airing_today")
    Call<TvShowListResponse> getAiringTodayTvShowsList();
}
