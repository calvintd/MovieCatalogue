package com.made.calvintd.moviecatalogue.presenter;

import android.content.Context;
import android.util.Log;

import com.made.calvintd.moviecatalogue.adapter.TvShowAdapter;
import com.made.calvintd.moviecatalogue.itemmodel.TvShow;
import com.made.calvintd.moviecatalogue.itemmodel.TvShowListResponse;
import com.made.calvintd.moviecatalogue.model.TvShowModel;
import com.made.calvintd.moviecatalogue.restapi.ApiInterface;
import com.made.calvintd.moviecatalogue.restapi.RetrofitInstance;
import com.made.calvintd.moviecatalogue.view.TvShowView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowPresenter{
    private TvShowView view;

    public TvShowPresenter(TvShowView view) {
        this.view = view;
    }

    public void getData(final ArrayList<TvShow> tvShows) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<TvShowListResponse> call = apiInterface.getAiringTodayTvShowsList();

        call.enqueue(new Callback<TvShowListResponse>() {
            @Override
            public void onResponse(Call<TvShowListResponse> call, Response<TvShowListResponse> response) {
                if (response.isSuccessful()) {
                    TvShowListResponse tvShowListResponse = response.body();

                    try {
                        if (tvShowListResponse != null) {
                            List<TvShowListResponse.Results> results = tvShowListResponse.getResults();

                            for (TvShowListResponse.Results result : results) {
                                if (result.getPosterPath() != null) {
                                    tvShows.add(new TvShow(result.getFirstAirDate(), result.getId(), result.getName(), result.getOverview(),
                                            "https://image.tmdb.org/t/p/w185" + result.getPosterPath(), result.getVoteAverage(),
                                            result.getVoteCount()));
                                } else {
                                    tvShows.add(new TvShow(result.getFirstAirDate(), result.getId(), result.getName(), result.getOverview(),
                                            null, result.getVoteAverage(), result.getVoteCount()));
                                }
                            }

                            TvShowAdapter tvShowAdapter = new TvShowAdapter();
                            tvShowAdapter.setListTvShows(tvShows);
                            TvShowModel model = new TvShowModel(tvShowAdapter);
                            view.showTvShows(model);
                        }
                    } finally {
                        if (tvShowListResponse != null) {
                            tvShowListResponse.toString();
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
            public void onFailure(Call<TvShowListResponse> call, Throwable t) {
                Log.d("APIFailure", t.getCause().toString());
                view.showError();
            }
        });
    }

    public void getDataByName(final ArrayList<TvShow> tvShows, String query) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<TvShowListResponse> call = apiInterface.getAiringTodayTvShowsListByName(query);

        call.enqueue(new Callback<TvShowListResponse>() {
            @Override
            public void onResponse(Call<TvShowListResponse> call, Response<TvShowListResponse> response) {
                if (response.isSuccessful()) {
                    TvShowListResponse tvShowListResponse = response.body();

                    try {
                        if (tvShowListResponse != null) {
                            List<TvShowListResponse.Results> results = tvShowListResponse.getResults();

                            for (TvShowListResponse.Results result : results) {
                                if (result.getPosterPath() != null) {
                                    tvShows.add(new TvShow(result.getFirstAirDate(), result.getId(), result.getName(), result.getOverview(),
                                            "https://image.tmdb.org/t/p/w185" + result.getPosterPath(), result.getVoteAverage(),
                                            result.getVoteCount()));
                                } else {
                                    tvShows.add(new TvShow(result.getFirstAirDate(), result.getId(), result.getName(), result.getOverview(),
                                            null, result.getVoteAverage(), result.getVoteCount()));
                                }
                            }

                            TvShowAdapter tvShowAdapter = new TvShowAdapter();
                            tvShowAdapter.setListTvShows(tvShows);
                            TvShowModel model = new TvShowModel(tvShowAdapter);
                            view.showQueriedTvShows(model);
                        }
                    } finally {
                        if (tvShowListResponse != null) {
                            tvShowListResponse.toString();
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
            public void onFailure(Call<TvShowListResponse> call, Throwable t) {
                Log.d("APIFailure", t.getCause().toString());
                view.showError();
            }
        });
    }
}
