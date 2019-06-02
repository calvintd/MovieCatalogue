package com.made.calvintd.moviecatalogue.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.made.calvintd.moviecatalogue.itemmodel.TvShow;
import com.made.calvintd.moviecatalogue.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private Context context;
    private ArrayList<TvShow> listTvShow;

    private ArrayList<TvShow> getListTvShow() {
        return listTvShow;
    }

    public void setListTvShow(ArrayList<TvShow> listTvShow) {
        this.listTvShow = listTvShow;
        notifyDataSetChanged();
    }

    public TvShowAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshow, parent, false);
        return new TvShowViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder tvShowViewHolder, int i) {
        TvShow tvShow = getListTvShow().get(i);
        final Resources resources = context.getResources();

        if (tvShow.getPosterPath() != null) {
            Glide.with(context)
                    .load(tvShow.getPosterPath())
                    .centerCrop()
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.ic_photo_black_48dp)
                    .error(R.drawable.ic_error_black_48dp)
                    .into(tvShowViewHolder.imgPoster);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_photo_black_48dp)
                    .into(tvShowViewHolder.imgPoster);
        }
        tvShowViewHolder.tvName.setText(tvShow.getName());
        tvShowViewHolder.tvFirstAirDate.setText(tvShow.getFirstAirDate());
        tvShowViewHolder.tvScore.setText(tvShow.getVoteAverage() +  " " + resources.getQuantityString(R.plurals.tv_score, tvShow.getVoteCount(),
                tvShow.getVoteCount()));
    }

    @Override
    public int getItemCount() {
        return getListTvShow().size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_tvshow_item_poster) ImageView imgPoster;
        @BindView(R.id.tv_tvshow_item_name) TextView tvName;
        @BindView(R.id.tv_tvshow_item_first_air_date) TextView tvFirstAirDate;
        @BindView(R.id.tv_tvshow_item_score) TextView tvScore;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
