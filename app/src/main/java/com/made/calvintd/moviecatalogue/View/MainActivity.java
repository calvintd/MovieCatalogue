package com.made.calvintd.moviecatalogue.View;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.made.calvintd.moviecatalogue.Adapter.MovieAdapter;
import com.made.calvintd.moviecatalogue.Model.MainModel;
import com.made.calvintd.moviecatalogue.Model.Movie;
import com.made.calvintd.moviecatalogue.Presenter.MainPresenter;
import com.made.calvintd.moviecatalogue.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {
    private ListView lvMovies;
    private ArrayList<Movie> movies;
    private final MainPresenter presenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getResources().getString(R.string.main_activity_title));
        lvMovies = findViewById(R.id.lv_movies);
        movies = new ArrayList<>();
        presenter.initMovies(this, movies);

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Movie movie = movies.get(position);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMovies(MainModel model) {
        lvMovies.setAdapter(model.getAdapter());
    }
}
