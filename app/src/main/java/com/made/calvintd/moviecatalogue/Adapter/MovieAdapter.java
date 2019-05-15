package com.made.calvintd.moviecatalogue.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.made.calvintd.moviecatalogue.Model.Movie;
import com.made.calvintd.moviecatalogue.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Movie> listMovies;

    public ArrayList<Movie> getListMovies() { return listMovies; }

    public void setListMovies(ArrayList<Movie> listMovies) { this.listMovies = listMovies; }

    public MovieAdapter (Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Glide.with(context)
                .load(getListMovies().get(i).getPoster())
                .apply(new RequestOptions().override(60, 90))
                .into(movieViewHolder.imgPoster);
        movieViewHolder.tvTitle.setText(getListMovies().get(i).getTitle());
        movieViewHolder.tvYear.setText(getListMovies().get(i).getTitle());
        movieViewHolder.tvDirector.setText(getListMovies().get(i).getDirector());
    }

    @Override
    public int getItemCount() {
        return getListMovies().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_movie_poster) ImageView imgPoster;
        @BindView(R.id.tv_movie_title) TextView tvTitle;
        @BindView(R.id.tv_movie_year) TextView tvYear;
        @BindView(R.id.tv_movie_director) TextView tvDirector;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
