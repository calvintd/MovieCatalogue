package com.made.calvintd.moviecatalogue.widgetservice;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.db.CatalogueDatabase;
import com.made.calvintd.moviecatalogue.itemmodel.FavoriteTvShow;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FavoriteTvShowsWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FavoriteTvShowsStackRemoteViewsFactory(this.getApplicationContext());
    }
}

class FavoriteTvShowsStackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<FavoriteTvShow> favoriteTvShows;
    private final Context context;

    FavoriteTvShowsStackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        favoriteTvShows = CatalogueDatabase.getDatabase(context).catalogueDao().getFavoriteTvShows();
    }

    @Override
    public void onDestroy() {
        favoriteTvShows.clear();
    }

    @Override
    public int getCount() {
        return favoriteTvShows.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_stack);
        try {
            rv.setImageViewBitmap(R.id.img_stack_widget_poster, Glide.with(context)
                .asBitmap()
                .load(favoriteTvShows.get(position).getPosterPath())
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