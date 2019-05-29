package com.made.calvintd.moviecatalogue.restapi;

import com.made.calvintd.moviecatalogue.itemmodel.MovieDetailsResponse;
import com.made.calvintd.moviecatalogue.itemmodel.MovieListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("movie/now_playing")
    Call<List<MovieListResponse>> getNowPlayingMoviesList();

    @GET("tv/on_the_air")
    Call<List<MovieListResponse>> getOnTheAirTvShowsList();

    @GET("movie/{movie_id}")
    Call<List<MovieDetailsResponse>> getMovieDetails(@Path("movie_id") int id);

    @GET("tv/{tv_id}")
    Call<List<MovieDetailsResponse>> getTvShowDetails(@Path("tv_id") int id);
}
