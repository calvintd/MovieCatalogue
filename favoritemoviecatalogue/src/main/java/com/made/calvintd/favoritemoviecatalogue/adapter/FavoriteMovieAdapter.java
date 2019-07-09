package com.made.calvintd.favoritemoviecatalogue.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.calvintd.favoritemoviecatalogue.R;
import com.made.calvintd.favoritemoviecatalogue.converter.DateConverter;
import com.made.calvintd.favoritemoviecatalogue.itemmodel.FavoriteMovie;

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

        FavoriteMovieViewHolder (@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
