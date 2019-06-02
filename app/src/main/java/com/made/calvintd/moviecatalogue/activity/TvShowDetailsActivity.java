package com.made.calvintd.moviecatalogue.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.calvintd.moviecatalogue.fragment.LanguageFragment;
import com.made.calvintd.moviecatalogue.itemmodel.TvShow;
import com.made.calvintd.moviecatalogue.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    @BindView(R.id.img_tvshow_detail_poster) ImageView imgPoster;
    @BindView(R.id.tv_tvshow_detail_name) TextView tvName;
    @BindView(R.id.tv_tvshow_detail_first_air_date) TextView tvFirstAirDate;
    @BindView(R.id.tv_tvshow_detail_score) TextView tvScore;
    @BindView(R.id.tv_tvshow_detail_overview) TextView tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        ButterKnife.bind(this);

        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.details_activity_title));
        }

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOW);

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

        Locale locale = configuration.locale;
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputDateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
        String inputDate = tvShow.getFirstAirDate();
        try {
            Date parsedDate = inputDateFormat.parse(inputDate);
            tvFirstAirDate.setText(outputDateFormat.format(parsedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvScore.setText(tvShow.getVoteAverage() + " " + resources.getQuantityString(R.plurals.tv_score, tvShow.getVoteCount(), tvShow.getVoteCount()));

        tvOverview.setText(tvShow.getOverview());
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            tvOverview.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
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
        if (item.getItemId() == R.id.menu_language) {
            LanguageFragment mLanguageFragment = new LanguageFragment();
            FragmentManager mFragmentManager = getSupportFragmentManager();
            mLanguageFragment.show(mFragmentManager, LanguageFragment.class.getSimpleName());
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