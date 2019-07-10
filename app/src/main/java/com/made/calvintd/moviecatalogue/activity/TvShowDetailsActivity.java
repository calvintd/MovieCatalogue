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
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteTvShow;
import com.made.calvintd.moviecatalogue.itemmodel.TvShow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    private CatalogueDao catalogueDao;
    private boolean favorited = false;
    private TvShow tvShow;
    @BindView(R.id.img_tv_show_detail_poster) ImageView imgPoster;
    @BindView(R.id.tv_tv_show_detail_name) TextView tvName;
    @BindView(R.id.tv_tv_show_detail_first_air_date) TextView tvFirstAirDate;
    @BindView(R.id.tv_tv_show_detail_score) TextView tvScore;
    @BindView(R.id.tv_tv_show_detail_overview) TextView tvOverview;
    @BindView(R.id.img_tv_show_detail_favorite) ImageView imgFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        ButterKnife.bind(this);

        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();

        catalogueDao = CatalogueDatabase.getDatabase(this).catalogueDao();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.details_activity_title));
        }

        tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

        if(tvShow.getPosterPath() != null) {
            Glide.with(this)
                    .load(tvShow.getPosterPath())
                    .centerCrop()
                    .placeholder(R.drawable.ic_photo_black_48dp)
                    .error(R.drawable.ic_error_black_48dp)
                    .into(imgPoster);
        } else {
            Glide.with(this)
                    .load(R.drawable.ic_photo_black_48dp)
                    .into(imgPoster);
        }

        tvName.setText(tvShow.getName());

        tvFirstAirDate.setText(DateConverter.convertDate(this, tvShow.getFirstAirDate()));

        tvScore.setText(tvShow.getVoteAverage() + " " + resources.getQuantityString(R.plurals.tv_score, tvShow.getVoteCount(), tvShow.getVoteCount()));

        tvOverview.setText(tvShow.getOverview());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            tvOverview.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }

        if (catalogueDao.checkTvShowInTable(tvShow.getId()) > 0) {
            favorited = true;
            imgFavorite.setImageResource(R.drawable.ic_favorite_black_48dp);
        }
        imgFavorite.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.img_tv_show_detail_favorite && !favorited) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setMessage(R.string.details_favorite_confirmation_message)
                    .setCancelable(true)
                    .setIcon(R.drawable.ic_favorite_black_48dp)
                    .setPositiveButton(R.string.details_favorite_confirmation_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FavoriteTvShow favoriteTvShow = new FavoriteTvShow(tvShow.getFirstAirDate(), tvShow.getId(), tvShow.getName(),
                                    tvShow.getPosterPath(), tvShow.getVoteAverage(), tvShow.getVoteCount());
                            catalogueDao.insertFavoriteTvShow(favoriteTvShow);
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
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
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
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        Resources res = this.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();

        res.updateConfiguration(config, dm);
    }
}