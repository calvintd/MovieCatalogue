package com.made.calvintd.moviecatalogue.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.calvintd.moviecatalogue.Model.Movie;
import com.made.calvintd.moviecatalogue.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    @BindView(R.id.img_detail_poster) ImageView imgPoster;
    @BindView(R.id.tv_detail_title) TextView tvTitle;
    @BindView(R.id.tv_detail_director) TextView tvDirector;
    @BindView(R.id.tv_detail_year) TextView tvYear;
    @BindView(R.id.tv_detail_description) TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.detail_activity_title));

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        Glide.with(this).load(movie.getPoster()).into(imgPoster);
        tvTitle.setText(movie.getTitle());
        tvDirector.setText(movie.getDirector());
        tvYear.setText(movie.getYear());
        tvDescription.setText(movie.getDescription());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }
}
