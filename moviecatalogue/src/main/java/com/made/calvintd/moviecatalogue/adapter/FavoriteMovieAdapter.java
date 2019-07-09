package com.made.calvintd.moviecatalogue.adapter;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.converter.DateConverter;
import com.made.calvintd.moviecatalogue.database.CatalogueDatabase;
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder> {
    private List<FavoriteMovie> listFavoriteMovies;

    public List<FavoriteMovie> getListFavoriteMovies() {
        return listFavoriteMovies;
    }

    public void setListFavoriteMovies(List<FavoriteMovie> listFavoriteMovies) {
        this.listFavoriteMovies = listFavoriteMovies;
    }

    public FavoriteMovieAdapter() {}

    @NonNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int i) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_movie, parent, false);
        return new FavoriteMovieViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder (@NonNull final FavoriteMovieViewHolder favoriteMovieViewHolder, int i) {
        FavoriteMovie favoriteMovie = getListFavoriteMovies().get(i);

        if (favoriteMovie.getPosterPath() !=  null) {
            Glide.with(favoriteMovieViewHolder.imgPoster.getContext())
                .load(favoriteMovie.getPosterPath())
                .centerCrop()
                .thumbnail(0.5f)
                .placeholder(R.drawable.ic_photo_black_48dp)
                .error(R.drawable.ic_error_black_48dp)
                .into(favoriteMovieViewHolder.imgPoster);
        } else {
            Glide.with(favoriteMovieViewHolder.imgPoster.getContext())
                    .load(R.drawable.ic_photo_black_48dp)
                    .into(favoriteMovieViewHolder.imgPoster);
        }

        favoriteMovieViewHolder.tvTitle.setText(favoriteMovie.getTitle());

        favoriteMovieViewHolder.tvReleaseDate.setText(DateConverter.convertDate(favoriteMovieViewHolder.tvReleaseDate.getContext(),
                favoriteMovie.getReleaseDate()));

        favoriteMovieViewHolder.tvScore.setText(favoriteMovie.getVoteAverage() + " " + favoriteMovieViewHolder.tvScore.getResources()
                .getQuantityString(R.plurals.tv_score, favoriteMovie.getVoteCount(), favoriteMovie.getVoteCount()));

        favoriteMovieViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext())
                        .setMessage(R.string.favorites_delete_confirmation_message)
                        .setCancelable(true)
                        .setIcon(R.drawable.ic_delete_black_48dp)
                        .setPositiveButton(R.string.favorites_delete_confirmation_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CatalogueDatabase.getDatabase(v.getContext()).catalogueDao().deleteFavoriteMovie(getListFavoriteMovies()
                                        .get(favoriteMovieViewHolder.getAdapterPosition()).getId());
                                listFavoriteMovies.remove(favoriteMovieViewHolder.getAdapterPosition());
                                notifyItemRemoved(favoriteMovieViewHolder.getAdapterPosition());
                                Snackbar.make(v.getRootView(), R.string.favorites_delete_deleted, Snackbar.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(R.string.favorites_delete_confirmation_no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListFavoriteMovies().size();
    }

    class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_fav_movie_item_poster) ImageView imgPoster;
        @BindView(R.id.tv_fav_movie_item_title) TextView tvTitle;
        @BindView(R.id.tv_fav_movie_item_release_date) TextView tvReleaseDate;
        @BindView(R.id.tv_fav_movie_item_score) TextView tvScore;
        @BindView(R.id.img_fav_movie_item_delete) ImageView imgDelete;

        FavoriteMovieViewHolder (@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
