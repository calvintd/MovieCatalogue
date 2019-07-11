package com.made.calvintd.moviecatalogue.alarmreceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.itemmodel.MovieListResponse;
import com.made.calvintd.moviecatalogue.restapi.ApiInterface;
import com.made.calvintd.moviecatalogue.restapi.RetrofitInstance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewestReminderReceiver extends BroadcastReceiver {
    private final long[] vibrationPattern = new long[]{1000, 1000, 1000, 1000, 1000};
    private ArrayList<String> movieTitles = new ArrayList<>();
    private Random random = new Random();

    @Override
    public void onReceive(final Context context, Intent intent) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<MovieListResponse> call = apiInterface.getNowPlayingMoviesList();

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.isSuccessful()) {
                    MovieListResponse movieListResponse = response.body();

                    try {
                        if (movieListResponse != null) {
                            List<MovieListResponse.Results> results = movieListResponse.getResults();
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                            for(MovieListResponse.Results result: results) {
                                if(result.getReleaseDate().equals(simpleDateFormat.format(calendar.getTime()))) {
                                    movieTitles.add(result.getTitle());
                                }
                            }
                        }
                    } finally {
                        if (movieListResponse != null) {
                            movieListResponse.toString();
                        }
                    }
                } else {
                    if (response.errorBody() != null) {
                        Log.d("APIFailure", response.errorBody().toString());
                    }
                    Toast.makeText(context, context.getResources().getString(R.string.api_data_failure), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.d("APIFailure", t.getCause().toString());
                Toast.makeText(context, context.getResources().getString(R.string.api_data_failure), Toast.LENGTH_SHORT).show();
            }
        });

        final int NEWEST_NOTIFICATION_ID = Integer.valueOf(context.getResources().getString(R.string.reminder_notification_newest_id));
        final String NEWEST_NOTIFICATION_CHANNEL_ID = context.getResources().getString(R.string.reminder_notification_newest_channel_id);
        final String NEWEST_NOTIFICATION_CHANNEL_NAME = context.getResources().getString(R.string.reminder_notification_newest_channel_name);
        final String NEWEST_NOTIFICATION_TITLE = context.getResources().getString(R.string.reminder_notification_newest_title);
        final String NEWEST_NOTIFICATION_MESSAGE;
        if(movieTitles.size() == 1) {
            NEWEST_NOTIFICATION_MESSAGE = String.format(context.getResources().getString(R.string.reminder_notification_newest_message_single),
                    movieTitles.get(0));
        } else if (movieTitles.size() > 1){
            NEWEST_NOTIFICATION_MESSAGE = String.format(context.getResources().getString(R.string.reminder_notification_newest_message_multiple),
                    movieTitles.size(), movieTitles.get(random.nextInt(movieTitles.size() - 1)));
        } else {
            NEWEST_NOTIFICATION_MESSAGE = context.getResources().getString(R.string.reminder_notification_newest_message_zero);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri reminderSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NEWEST_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_movie_black_36dp)
                .setContentTitle(NEWEST_NOTIFICATION_TITLE)
                .setContentText(NEWEST_NOTIFICATION_MESSAGE)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(vibrationPattern)
                .setSound(reminderSound);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NEWEST_NOTIFICATION_CHANNEL_ID, NEWEST_NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(vibrationPattern);
            builder.setChannelId(NEWEST_NOTIFICATION_CHANNEL_ID);
            if(notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        Notification notification = builder.build();
        if(notificationManager != null) {
            notificationManager.notify(NEWEST_NOTIFICATION_ID, notification);
        }
    }
}
