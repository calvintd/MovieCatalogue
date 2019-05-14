package com.made.calvintd.moviecatalogue.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.calvintd.moviecatalogue.Model.Movie;
import com.made.calvintd.moviecatalogue.R;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies;

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<Movie>();
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        Movie movie = (Movie) getItem(i);
        viewHolder.bind(movie);
        return view;
    }

    private class ViewHolder {
        private ImageView imgPoster;
        private TextView tvTitle;
        private TextView tvYear;
        private TextView tvDirector;

        ViewHolder(View view) {
            imgPoster = view.findViewById(R.id.img_movie_poster);
            tvTitle = view.findViewById(R.id.tv_movie_title);
            tvYear = view.findViewById(R.id.tv_movie_year);
            tvDirector = view.findViewById(R.id.tv_movie_director);
        }

        void bind(Movie movie) {
            Glide.with(context).load(movie.getPoster()).into(imgPoster);
            tvTitle.setText(movie.getTitle());
            tvYear.setText(movie.getYear());
            tvDirector.setText(movie.getDirector());
        }
    }
}
