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
import com.made.calvintd.favoritemoviecatalogue.adapter.FavoriteMovieAdapter;
import com.made.calvintd.favoritemoviecatalogue.database.CatalogueContract;
import com.made.calvintd.favoritemoviecatalogue.itemmodel.FavoriteMovie;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.Objects;

import static com.made.calvintd.favoritemoviecatalogue.database.CatalogueContract.FavoriteMovieItems.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {
    @BindView(R.id.rv_fav_movies) RecyclerView recyclerView;
    private FavoriteMovieAdapter adapter = new FavoriteMovieAdapter();
    private Uri uri = CatalogueContract.FavoriteMovieItems.CONTENT_URI;
    private final String TAG = "MovieCursor";

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        ButterKnife.bind(this, view);

        ArrayList<FavoriteMovie> favoriteMovies = new ArrayList<>();
        if (view.getContext() != null) {
            Cursor cursor = view.getContext().getContentResolver().query(uri,
                    null,
                    null,
                    null,
                    null,
                    null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(ID));
                    String posterPath = cursor.getString(cursor.getColumnIndex(POSTER_PATH));
                    String releaseDate = cursor.getString(cursor.getColumnIndex(RELEASE_DATE));
                    String title = cursor.getString(cursor.getColumnIndex(TITLE));
                    double voteAverage = cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE));
                    int voteCount = cursor.getInt(cursor.getColumnIndex(VOTE_COUNT));
                    FavoriteMovie favoriteMovie = new FavoriteMovie(id, posterPath, releaseDate, title, voteAverage, voteCount);
                    favoriteMovies.add(favoriteMovie);
                } while (cursor.moveToNext());
                cursor.close();
            } else {
                Log.d(TAG, "No movies found!");
            }
        }

        adapter.setListFavoriteMovies(favoriteMovies);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }
}
