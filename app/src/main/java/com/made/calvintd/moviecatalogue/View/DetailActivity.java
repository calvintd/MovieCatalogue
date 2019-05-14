package com.made.calvintd.moviecatalogue.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.calvintd.moviecatalogue.Model.Movie;
import com.made.calvintd.moviecatalogue.R;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private ImageView imgPoster;
    private TextView tvTitle;
    private TextView tvDirector;
    private TextView tvYear;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle(getResources().getString(R.string.detail_activity_title));
        imgPoster = findViewById(R.id.img_detail_poster);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvDirector = findViewById(R.id.tv_detail_director);
        tvYear = findViewById(R.id.tv_detail_year);
        tvDescription = findViewById(R.id.tv_detail_description);

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
