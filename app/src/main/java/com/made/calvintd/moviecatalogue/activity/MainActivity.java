package com.made.calvintd.moviecatalogue.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.made.calvintd.moviecatalogue.adapter.PagerAdapter;
import com.made.calvintd.moviecatalogue.fragment.LanguageFragment;
import com.made.calvintd.moviecatalogue.fragment.MovieFragment;
import com.made.calvintd.moviecatalogue.fragment.TvShowFragment;
import com.made.calvintd.moviecatalogue.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_toolbar) Toolbar toolbar;
    @BindView(R.id.main_tab_layout) TabLayout tabLayout;
    @BindView(R.id.main_pager) ViewPager viewPager;
    private Configuration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.main_activity_title));
        }

        config = this.getResources().getConfiguration();

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new MovieFragment(), getResources().getString(R.string.tab_layout_movie));
        pagerAdapter.addFragment(new TvShowFragment(), getResources().getString(R.string.tab_layout_tv_show));

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
