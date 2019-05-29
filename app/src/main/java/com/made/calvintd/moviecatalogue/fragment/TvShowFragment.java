package com.made.calvintd.moviecatalogue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.made.calvintd.moviecatalogue.activity.TvShowDetailActivity;
import com.made.calvintd.moviecatalogue.itemmodel.TvShow;
import com.made.calvintd.moviecatalogue.model.TvShowModel;
import com.made.calvintd.moviecatalogue.presenter.TvShowPresenter;
import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.recyclerviewsupport.ItemClickSupport;
import com.made.calvintd.moviecatalogue.view.TvShowView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements TvShowView {
    @BindView(R.id.rv_tvshows) RecyclerView rvTvShows;
    private ArrayList<TvShow> tvShows = new ArrayList<>();
    private final TvShowPresenter presenter = new TvShowPresenter(this);

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);

        ButterKnife.bind(this, view);

        presenter.initMovies(getActivity(), tvShows, rvTvShows); //2 = TV shows

        ItemClickSupport.addTo(rvTvShows).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showDetails(tvShows.get(position));
            }
        });

        return view;
    }

    private void showDetails(TvShow tvShow) {
        Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, tvShow);
        startActivity(intent);
    }

    @Override
    public void showTvShows(TvShowModel model) {
        rvTvShows.setAdapter(model.getTvShowAdapter());
    }
}