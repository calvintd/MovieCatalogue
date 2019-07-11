package com.made.calvintd.moviecatalogue.activity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.converter.DateConverter;
import com.made.calvintd.moviecatalogue.dao.CatalogueDao;
import com.made.calvintd.moviecatalogue.database.CatalogueDatabase;
import com.made.calvintd.moviecatalogue.fragment.LanguageFragment;
import com.made.calvintd.moviecatalogue.fragment.ReminderFragment;
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteMovie;
import com.made.calvintd.moviecatalogue.itemmodel.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MOVIE = "extra_movie";
    private CatalogueDao catalogueDao;
    private boolean favorited = false;
    private Movie movie;
    @BindView(R.id.img_movie_detail_poster) ImageView imgPoster;
    @BindView(R.id.tv_movie_detail_title) TextView tvTitle;
    @BindView(R.id.tv_movie_detail_release_date) TextView tvReleaseDate;
    @BindView(R.id.tv_movie_detail_score) TextView tvScore;
    @BindView(R.id.tv_movie_detail_overview) TextView tvOverview;
    @BindView(R.id.img_movie_detail_favorite) ImageView imgFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();

        catalogueDao = CatalogueDatabase.getDatabase(this).catalogueDao();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.details_activity_title));
        }

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if(movie.getPosterPath() != null) {
            Glide.with(this)
                    .load(movie.getPosterPath())
                    .centerCrop()
                    .placeholder(R.drawable.ic_photo_black_48dp)
                    .error(R.drawable.ic_error_black_48dp)
                    .into(imgPoster);
        } else {
            Glide.with(this)
                    .load(R.drawable.ic_photo_black_48dp)
                    .into(imgPoster);
        }

        tvTitle.setText(movie.getTitle());

        tvReleaseDate.setText(DateConverter.convertDate(this, movie.getReleaseDate()));

        tvScore.setText(movie.getVoteAverage() + " " + resources.getQuantityString(R.plurals.tv_score, movie.getVoteCount(), movie.getVoteCount()));

        tvOverview.setText(movie.getOverview());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            tvOverview.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }

        if (catalogueDao.checkMovieInTable(movie.getId()) > 0) {
            favorited = true;
            imgFavorite.setImageResource(R.drawable.ic_favorite_black_48dp);
        }
        imgFavorite.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.img_movie_detail_favorite && !favorited) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setMessage(R.string.details_favorite_confirmation_message)
                    .setCancelable(true)
                    .setIcon(R.drawable.ic_favorite_black_48dp)
                    .setPositiveButton(R.string.details_favorite_confirmation_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FavoriteMovie favoriteMovie = new FavoriteMovie(movie.getId(), movie.getPosterPath(), movie.getReleaseDate(),
                                    movie.getTitle(), movie.getVoteAverage(), movie.getVoteCount());
                            catalogueDao.insertFavoriteMovie(favoriteMovie);
                            favorited = true;
                            imgFavorite.setImageResource(R.drawable.ic_favorite_black_48dp);
                            Snackbar.make(v, R.string.details_favorite_added, Snackbar.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton(R.string.details_favorite_confirmation_no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.other_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (item.getItemId()) {
            case R.id.menu_language:
                LanguageFragment languageFragment = new LanguageFragment();
                languageFragment.show(fragmentManager, LanguageFragment.class.getSimpleName());
                break;
            case R.id.menu_reminder:
                ReminderFragment reminderFragment = new ReminderFragment();
                reminderFragment.show(fragmentManager, ReminderFragment.class.getSimpleName());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        Resources res = this.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();

        res.updateConfiguration(config, dm);
    }
}
