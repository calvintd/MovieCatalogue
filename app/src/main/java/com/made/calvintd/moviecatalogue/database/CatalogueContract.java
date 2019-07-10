package com.made.calvintd.moviecatalogue.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class CatalogueContract {
    public static final String AUTHORITY = "com.made.calvintd.moviecatalogue";
    private static final String SCHEME = "content";

    public static final class FavoriteMovieItems implements BaseColumns {
        public static final String TABLE_NAME = "favoriteMovie";
        public static final String ID = "id";
        public static final String POSTER_PATH = "posterPath";
        public static final String RELEASE_DATE = "releaseDate";
        public static final String TITLE = "title";
        public static final String VOTE_AVERAGE = "voteAverage";
        public static final String VOTE_COUNT = "voteCount";

        public static final Uri CONTENT_URI = new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_URI + TABLE_NAME;
    }

    public static final class FavoriteTvShowItems implements BaseColumns {
        public static final String TABLE_NAME = "favoriteTvShow";
        public static final String FIRST_AIR_DATE = "firstAirDate";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String POSTER_PATH = "posterPath";
        public static final String VOTE_AVERAGE = "voteAverage";
        public static final String VOTE_COUNT = "voteCount";

        public static final Uri CONTENT_URI = new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_URI + TABLE_NAME;
    }
}
