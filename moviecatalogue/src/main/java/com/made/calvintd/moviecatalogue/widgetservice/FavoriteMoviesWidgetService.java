package com.made.calvintd.moviecatalogue.widgetservice;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.database.CatalogueDatabase;
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteMovie;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FavoriteMoviesWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FavoriteMoviesStackRemoteViewsFactory(this.getApplicationContext());
    }
}

class FavoriteMoviesStackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<FavoriteMovie> favoriteMovies;
    private final Context context;

    FavoriteMoviesStackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        favoriteMovies = CatalogueDatabase.getDatabase(context).catalogueDao().getFavoriteMovies();
    }

    @Override
    public void onDestroy() {
        favoriteMovies.clear();
    }

    @Override
    public int getCount() {
        return favoriteMovies.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_stack);
        try {
            rv.setImageViewBitmap(R.id.img_stack_widget_poster, Glide.with(context)
                .asBitmap()
                .load(favoriteMovies.get(position).getPosterPath())
                .submit()
                .get());
        } catch (ExecutionException|InterruptedException e) {
            e.printStackTrace();
        }
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}