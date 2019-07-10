package com.made.calvintd.favoritemoviecatalogue.fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.made.calvintd.favoritemoviecatalogue.R;
import com.made.calvintd.favoritemoviecatalogue.adapter.FavoriteTvShowAdapter;
import com.made.calvintd.favoritemoviecatalogue.database.CatalogueContract;
import com.made.calvintd.favoritemoviecatalogue.itemmodel.FavoriteTvShow;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.Objects;

import static com.made.calvintd.favoritemoviecatalogue.database.CatalogueContract.FavoriteTvShowItems.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment {
    @BindView(R.id.rv_fav_tv_shows) RecyclerView recyclerView;
    private FavoriteTvShowAdapter adapter = new FavoriteTvShowAdapter();
    private Uri uri = CatalogueContract.FavoriteTvShowItems.CONTENT_URI;
    private final String TAG = "TvShowCursor";

    public FavoriteTvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
        ButterKnife.bind(this, view);

        ArrayList<FavoriteTvShow> favoriteTvShows = new ArrayList<>();
        if (view.getContext() != null) {
            Cursor cursor = view.getContext().getContentResolver().query(uri,
                    null,
                    null,
                    null,
                    null,
                    null);

            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    String firstAirDate = cursor.getString(cursor.getColumnIndex(FIRST_AIR_DATE));
                    int id = cursor.getInt(cursor.getColumnIndex(ID));
                    String name = cursor.getString(cursor.getColumnIndex(NAME));
                    String posterPath = cursor.getString(cursor.getColumnIndex(POSTER_PATH));
                    double voteAverage = cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE));
                    int voteCount = cursor.getInt(cursor.getColumnIndex(VOTE_COUNT));
                    FavoriteTvShow favoriteTvShow = new FavoriteTvShow(firstAirDate, id, name, posterPath, voteAverage, voteCount);
                    favoriteTvShows.add(favoriteTvShow);
                } while (cursor.moveToNext());
                cursor.close();
            } else {
                Log.d(TAG, "No TV shows found!");
            }
        }

        adapter.setListFavoriteTvShows(favoriteTvShows);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }
}
