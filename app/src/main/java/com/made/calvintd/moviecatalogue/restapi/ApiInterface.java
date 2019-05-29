package com.made.calvintd.moviecatalogue.restapi;

import com.made.calvintd.moviecatalogue.itemmodel.MovieDetailsResponse;
import com.made.calvintd.moviecatalogue.itemmodel.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("movie/now_playing")
    Call<MovieListResponse> getNowPlayingMoviesList();

    @GET("tv/on_the_air")
    Call<MovieListResponse> getOnTheAirTvShowsList();

    @GET("movie/{movie_id}")
    Call<MovieDetailsResponse> getMovieDetails(@Path("movie_id") int id);

    @GET("tv/{tv_id}")
    Call<MovieDetailsResponse> getTvShowDetails(@Path("tv_id") int id);
}
