package com.made.calvintd.moviecatalogue.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.made.calvintd.moviecatalogue.itemmodel.Movie;
import com.made.calvintd.moviecatalogue.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> listMovies;

    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        this.listMovies = listMovies;
    }

    public MovieAdapter () {}

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Movie movie = getListMovies().get(i);

        if (movie.getPosterPath() != null) {
            Glide.with(movieViewHolder.imgPoster.getContext())
                    .load(movie.getPosterPath())
                    .centerCrop()
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.ic_photo_black_48dp)
                    .error(R.drawable.ic_error_black_48dp)
                    .into(movieViewHolder.imgPoster);
        } else {
            Glide.with(movieViewHolder.imgPoster.getContext())
                    .load(R.drawable.ic_photo_black_48dp)
                    .into(movieViewHolder.imgPoster);
        }

        movieViewHolder.tvTitle.setText(movie.getTitle());

        Locale locale = movieViewHolder.tvReleaseDate.getResources().getConfiguration().locale;
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputDateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
        String inputDateText = movie.getReleaseDate();
        try {
            Date parsedDate = inputDateFormat.parse(inputDateText);
            movieViewHolder.tvReleaseDate.setText(outputDateFormat.format(parsedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        movieViewHolder.tvScore.setText(movie.getVoteAverage() + " " + movieViewHolder.tvScore.getResources()
                .getQuantityString(R.plurals.tv_score, movie.getVoteCount(), movie.getVoteCount()));
    }

    @Override
    public int getItemCount() {
        return getListMovies().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_movie_item_poster) ImageView imgPoster;
        @BindView(R.id.tv_movie_item_title) TextView tvTitle;
        @BindView(R.id.tv_movie_item_release_date) TextView tvReleaseDate;
        @BindView(R.id.tv_movie_item_score) TextView tvScore;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
