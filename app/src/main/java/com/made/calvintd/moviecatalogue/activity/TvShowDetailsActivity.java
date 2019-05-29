package com.made.calvintd.moviecatalogue.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.fragment.LanguageFragment;
import com.made.calvintd.moviecatalogue.itemmodel.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    @BindView(R.id.img_detail_poster) ImageView imgPoster;
    @BindView(R.id.tv_detail_title) TextView tvTitle;
    @BindView(R.id.tv_detail_category) TextView tvCategory;
    @BindView(R.id.tv_detail_figure_id) TextView tvFigureId;
    @BindView(R.id.tv_detail_figure) TextView tvFigure;
    @BindView(R.id.tv_detail_year) TextView tvYear;
    @BindView(R.id.tv_detail_description) TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        ButterKnife.bind(this);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.detail_activity_title));
        }

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        Glide.with(this).load(movie.getPoster()).into(imgPoster);
        tvTitle.setText(movie.getTitle());
        switch (movie.getCategory()) {
            case 1:
                tvCategory.setText(getResources().getString(R.string.detail_category_movie));
                tvFigureId.setText(getResources().getString(R.string.detail_figure_director));
                break;
            case 2:
                tvCategory.setText(getResources().getString(R.string.detail_category_tv_show));
                tvFigureId.setText(getResources().getString(R.string.detail_figure_creator));
                break;
        }
        tvFigure.setText(movie.getFigure());
        tvYear.setText(movie.getYear());
        tvDescription.setText(movie.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_language:
                LanguageFragment languageFragment = new LanguageFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                languageFragment.show(fragmentManager, LanguageFragment.class.getSimpleName());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        Resources res = this.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();

        res.updateConfiguration(config, dm);
    }
}