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
import android.widget.Toast;

import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.activity.TvShowDetailsActivity;
import com.made.calvintd.moviecatalogue.adapter.TvShowAdapter;
import com.made.calvintd.moviecatalogue.itemmodel.TvShow;
import com.made.calvintd.moviecatalogue.model.TvShowModel;
import com.made.calvintd.moviecatalogue.presenter.TvShowPresenter;
import com.made.calvintd.moviecatalogue.recyclerviewsupport.ItemClickSupport;
import com.made.calvintd.moviecatalogue.view.TvShowView;
import com.made.calvintd.moviecatalogue.viewmodel.TvShowViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements TvShowView {
    @BindView(R.id.pb_tv_shows) ProgressBar progressBar;
    @BindView(R.id.rv_tv_shows) RecyclerView recyclerView;
    @BindView(R.id.tv_show_searchview) SearchView searchView;
    TvShowPresenter tvShowPresenter = new TvShowPresenter(this);
    private ArrayList<TvShow> tvShows = new ArrayList<>();
    private TvShowAdapter adapter = new TvShowAdapter();
    private TvShowViewModel tvShowViewModel;
    private ArrayList<TvShow> filteredTvShows = new ArrayList<>();

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setVisibility(View.INVISIBLE);
        searchView.setVisibility(View.GONE);

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvShows().observe(this, getTvShowsObserver);

        if(tvShowViewModel.getTvShows().getValue() == null) {
            tvShowPresenter.getData(tvShows);
        } else {
            tvShows = tvShowViewModel.getTvShows().getValue();
            adapter.notifyDataSetChanged();
        }

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if(filteredTvShows.isEmpty()) {
                    showDetails(tvShows.get(position));
                } else {
                    showDetails(filteredTvShows.get(position));
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filteredTvShows.clear();
                tvShowPresenter.getDataByName(filteredTvShows, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    private Observer<ArrayList<TvShow>> getTvShowsObserver = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShows) {
            adapter.setListTvShows(tvShows);
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.VISIBLE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    };

    private void showDetails(TvShow tvShow) {
        Intent intent = new Intent(getActivity(), TvShowDetailsActivity.class);
        intent.putExtra(TvShowDetailsActivity.EXTRA_TV_SHOW, tvShow);
        startActivity(intent);
    }

    @Override
    public void showTvShows(TvShowModel model) {
        recyclerView.setAdapter(model.getTvShowAdapter());
        adapter = model.getTvShowAdapter();
        adapter.notifyDataSetChanged();
        tvShowViewModel.postMutableLiveData(tvShows);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        searchView.setSubmitButtonEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void showQueriedTvShows(TvShowModel model) {
        recyclerView.setAdapter(model.getTvShowAdapter());
        adapter = model.getTvShowAdapter();
        adapter.notifyDataSetChanged();
        tvShowViewModel.postMutableLiveData(filteredTvShows);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        searchView.setSubmitButtonEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void showError() {
        if(getContext() != null) {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.api_data_failure), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!filteredTvShows.isEmpty()) {
            adapter.setListTvShows(filteredTvShows);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        searchView.clearFocus();
    }
}