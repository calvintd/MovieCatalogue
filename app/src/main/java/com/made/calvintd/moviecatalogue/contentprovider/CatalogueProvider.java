package com.made.calvintd.moviecatalogue.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.made.calvintd.moviecatalogue.database.CatalogueContract;

import java.util.Objects;

public class CatalogueProvider extends ContentProvider {
    private static final int FAVORITE_MOVIE = 100;
    private static final int FAVORITE_TV_SHOW = 101;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private CatalogueHelper catalogueHelper;

    static {
        uriMatcher.addURI(CatalogueContract.AUTHORITY, CatalogueContract.FavoriteMovieItems.TABLE_NAME, FAVORITE_MOVIE);
        uriMatcher.addURI(CatalogueContract.AUTHORITY, CatalogueContract.FavoriteTvShowItems.TABLE_NAME, FAVORITE_TV_SHOW);
    }

    @Override
    public boolean onCreate() {
        catalogueHelper = new CatalogueHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs,
        @Nullable String sortOrder) {
        SQLiteDatabase db = catalogueHelper.getWritableDatabase();
        Cursor cursor = null;

        int match = uriMatcher.match(uri);

        switch (match) {
            case FAVORITE_MOVIE:
                cursor = db.query(CatalogueContract.FavoriteMovieItems.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case FAVORITE_TV_SHOW:
                cursor = db.query(CatalogueContract.FavoriteTvShowItems.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "+uri);
        }

        if (cursor != null) {
            cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = uriMatcher.match(uri);
        String contentType = "";

        switch (match) {
            case FAVORITE_MOVIE:
                contentType = CatalogueContract.FavoriteMovieItems.CONTENT_TYPE;
                break;
            case FAVORITE_TV_SHOW:
                contentType = CatalogueContract.FavoriteTvShowItems.CONTENT_TYPE;
                break;
        }

        return contentType;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

class CatalogueHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movieCatalogue.db";
    private static final int DATABASE_VERSION = 1;

    public CatalogueHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createFavoriteMovieTable(db);
        createFavoriteTvShowTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createFavoriteMovieTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+CatalogueContract.FavoriteMovieItems.TABLE_NAME+" ("+
            CatalogueContract.FavoriteMovieItems.ID+" INTEGER PRIMARY KEY, "+
            CatalogueContract.FavoriteMovieItems.POSTER_PATH+" TEXT, "+
            CatalogueContract.FavoriteMovieItems.RELEASE_DATE+" TEXT, "+
            CatalogueContract.FavoriteMovieItems.TITLE+" TEXT, "+
            CatalogueContract.FavoriteMovieItems.VOTE_AVERAGE+" REAL, "+
            CatalogueContract.FavoriteMovieItems.VOTE_COUNT+" REAL);");
    }

    private void createFavoriteTvShowTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+CatalogueContract.FavoriteMovieItems.TABLE_NAME+" ("+
                CatalogueContract.FavoriteTvShowItems.FIRST_AIR_DATE+" TEXT, "+
                CatalogueContract.FavoriteTvShowItems.ID+" INTEGER PRIMARY KEY, "+
                CatalogueContract.FavoriteTvShowItems.NAME+" TEXT, "+
                CatalogueContract.FavoriteTvShowItems.POSTER_PATH+" TEXT, "+
                CatalogueContract.FavoriteTvShowItems.VOTE_AVERAGE+" REAL, "+
                CatalogueContract.FavoriteTvShowItems.VOTE_COUNT+" REAL);");
    }
}
