package com.made.calvintd.moviecatalogue.View;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;

import com.made.calvintd.moviecatalogue.Adapter.PagerAdapter;
import com.made.calvintd.moviecatalogue.Fragment.MovieFragment;
import com.made.calvintd.moviecatalogue.Fragment.TVShowFragment;
import com.made.calvintd.moviecatalogue.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_toolbar) Toolbar toolbar;
    @BindView(R.id.main_tab_layout) TabLayout tabLayout;
    @BindView(R.id.main_pager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.main_activity_title));
        }

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new MovieFragment(), getResources().getString(R.string.tab_layout_movie));
        pagerAdapter.addFragment(new TVShowFragment(), getResources().getString(R.string.tab_layout_tv_show));

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
