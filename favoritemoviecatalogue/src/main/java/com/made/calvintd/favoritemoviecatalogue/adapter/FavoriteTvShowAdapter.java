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
import com.made.calvintd.favoritemoviecatalogue.itemmodel.FavoriteTvShow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteTvShowAdapter extends RecyclerView.Adapter<FavoriteTvShowAdapter.FavoriteTvShowViewHolder> {
    private List<FavoriteTvShow> listFavoriteTvShows;

    public List<FavoriteTvShow> getListFavoriteTvShows() {
        return listFavoriteTvShows;
    }

    public void setListFavoriteTvShows (List<FavoriteTvShow> listFavoriteTvShows) {
        this.listFavoriteTvShows = listFavoriteTvShows;
    }

    @NonNull
    @Override
    public FavoriteTvShowViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int i) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_tv_show, parent, false);
        return new FavoriteTvShowViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteTvShowViewHolder favoriteTvShowViewHolder, int i) {
        FavoriteTvShow favoriteTvShow = getListFavoriteTvShows().get(i);

        if (favoriteTvShow.getPosterPath() !=  null) {
            Glide.with(favoriteTvShowViewHolder.imgPoster.getContext())
                    .load(favoriteTvShow.getPosterPath())
                    .centerCrop()
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.ic_photo_black_48dp)
                    .error(R.drawable.ic_error_black_48dp)
                    .into(favoriteTvShowViewHolder.imgPoster);
        } else {
            Glide.with(favoriteTvShowViewHolder.imgPoster.getContext())
                    .load(R.drawable.ic_photo_black_48dp)
                    .into(favoriteTvShowViewHolder.imgPoster);
        }

        favoriteTvShowViewHolder.tvName.setText(favoriteTvShow.getName());

        favoriteTvShowViewHolder.tvFirstAirDate.setText(DateConverter.convertDate(favoriteTvShowViewHolder.tvFirstAirDate.getContext(),
                favoriteTvShow.getFirstAirDate()));

        favoriteTvShowViewHolder.tvScore.setText(favoriteTvShow.getVoteAverage() + " " + favoriteTvShowViewHolder.tvScore.getResources()
                .getQuantityString(R.plurals.tv_score, favoriteTvShow.getVoteCount(), favoriteTvShow.getVoteCount()));
    }

    @Override
    public int getItemCount() {
        return getListFavoriteTvShows().size();
    }

    class FavoriteTvShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_fav_tv_show_item_poster) ImageView imgPoster;
        @BindView(R.id.tv_fav_tv_show_item_name) TextView tvName;
        @BindView(R.id.tv_fav_tv_show_item_first_air_date) TextView tvFirstAirDate;
        @BindView(R.id.tv_fav_tv_show_item_score) TextView tvScore;

        FavoriteTvShowViewHolder (@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
