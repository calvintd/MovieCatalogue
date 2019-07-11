package com.made.calvintd.moviecatalogue.view;

import com.made.calvintd.moviecatalogue.model.MovieModel;

public interface MovieView {
    void showMovies(MovieModel model);
    void showError();
}
