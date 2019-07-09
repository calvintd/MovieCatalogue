package com.made.calvintd.moviecatalogue.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.adapter.PagerAdapter;
import com.made.calvintd.moviecatalogue.fragment.FavoriteMovieFragment;
import com.made.calvintd.moviecatalogue.fragment.FavoriteTvShowFragment;
import com.made.calvintd.moviecatalogue.fragment.LanguageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends AppCompatActivity {
    @BindView(R.id.favorite_toolbar) Toolbar toolbar;
    @BindView(R.id.favorite_tab_layout) TabLayout tabLayout;
    @BindView(R.id.favorite_pager) ViewPager viewPager;
    private Configuration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.favorites_activity_title));
        }

        config = this.getResources().getConfiguration();

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new FavoriteMovieFragment(), getResources().getString(R.string.tab_layout_fav_movie));
        pagerAdapter.addFragment(new FavoriteTvShowFragment(), getResources().getString(R.string.tab_layout_fav_tv_show));

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.other_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.other_menu_language) {
            LanguageFragment mLanguageFragment = new LanguageFragment();
            FragmentManager mFragmentManager = getSupportFragmentManager();
            mLanguageFragment.show(mFragmentManager, LanguageFragment.class.getSimpleName());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        onConfigurationChanged(config);
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        Resources res = this.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();

        res.updateConfiguration(config, dm);
        this.recreate();
    }
}
