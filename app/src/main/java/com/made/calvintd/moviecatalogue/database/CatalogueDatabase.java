package com.made.calvintd.moviecatalogue.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.made.calvintd.moviecatalogue.dao.CatalogueDao;
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteMovie;
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteTvShow;

@Database (entities = {FavoriteMovie.class, FavoriteTvShow.class}, version = 1, exportSchema = false)
public abstract class CatalogueDatabase extends RoomDatabase {
    private static CatalogueDatabase databaseInstance;
    public abstract CatalogueDao catalogueDao();
    private final static String DATABASE_NAME = "movieCatalogue.db";

    public static CatalogueDatabase getDatabase (final Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context, CatalogueDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return databaseInstance;
    }

    public static void destroyInstance() {
        databaseInstance = null;
    }
}
