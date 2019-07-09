package com.made.calvintd.moviecatalogue.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.made.calvintd.moviecatalogue.itemmodel.FavoriteMovie;
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteTvShow;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CatalogueDao {
    @Insert(onConflict = REPLACE)
    void insertFavoriteMovie(FavoriteMovie favoriteMovie);

    @Insert(onConflict = REPLACE)
    void insertFavoriteTvShow(FavoriteTvShow favoriteTvShow);

    @Query("SELECT * FROM FavoriteMovie")
    List<FavoriteMovie> getFavoriteMovies();

    @Query("SELECT * FROM FavoriteTvShow")
    List<FavoriteTvShow> getFavoriteTvShows();

    @Query("SELECT COUNT(id) FROM FavoriteMovie WHERE id = :id")
    int checkMovieInTable (int id);

    @Query("SELECT COUNT(id) FROM FavoriteTvShow WHERE id = :id")
    int checkTvShowInTable (int id);

    @Query("DELETE FROM FavoriteMovie WHERE id = :id")
    void deleteFavoriteMovie(int id);

    @Query("DELETE FROM FavoriteTvShow WHERE id = :id")
    void deleteFavoriteTvShow(int id);
}
