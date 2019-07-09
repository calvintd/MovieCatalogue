package com.made.calvintd.moviecatalogue.fragment;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.made.calvintd.moviecatalogue.R;
import com.made.calvintd.moviecatalogue.alarmreceiver.DailyReminderReceiver;
import com.made.calvintd.moviecatalogue.alarmreceiver.NewestReminderReceiver;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends DialogFragment implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    @BindView(R.id.switch_reminder_daily) Switch switchDaily;
    @BindView(R.id.switch_reminder_new_releases) Switch switchNewReleases;
    @BindView(R.id.btn_reminder_save) Button btnSave;
    @BindView(R.id.btn_reminder_close) Button btnClose;
    private SharedPreferences sharedPreferences;
    private Context context;
    private Resources resources;
    private String DAILY_REMINDER_PREF, NEWEST_REMINDER_PREF;

    public ReminderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        btnSave.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        context = view.getContext();
        resources = context.getResources();
        String REMINDER_PREF = resources.getString(R.string.shared_preferences_reminder);
        DAILY_REMINDER_PREF = resources.getString(R.string.shared_preferences_reminder_daily);
        NEWEST_REMINDER_PREF = resources.getString(R.string.shared_preferences_reminder_newest);

        sharedPreferences = context.getSharedPreferences(REMINDER_PREF, Context.MODE_PRIVATE);
        switchDaily.setChecked(sharedPreferences.getBoolean(DAILY_REMINDER_PREF, false));
        switchNewReleases.setChecked(sharedPreferences.getBoolean(NEWEST_REMINDER_PREF, false));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_reminder_save:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(DAILY_REMINDER_PREF, switchDaily.isChecked());
                editor.putBoolean(NEWEST_REMINDER_PREF, switchNewReleases.isChecked());
                editor.apply();
                onSharedPreferenceChanged(sharedPreferences, DAILY_REMINDER_PREF);
                onSharedPreferenceChanged(sharedPreferences, NEWEST_REMINDER_PREF);
                showToastSavedReminderPreferences();
                this.dismiss();
                break;
            case R.id.btn_reminder_close:
                this.dismiss();
                break;
        }
    }

    private void showToastSavedReminderPreferences() {
        Toast.makeText(context, getResources().getString(R.string.reminder_message_saved), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(DAILY_REMINDER_PREF)) {
            AlarmManager alarmManager  = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();

            final int DAILY_REQUEST_CODE = Integer.valueOf(resources.getString(R.string.reminder_daily_request_code));
            Intent intent = new Intent(context, DailyReminderReceiver.class);
            final int DAILY_HOUR = Integer.valueOf(resources.getString(R.string.reminder_daily_hour_of_day));
            final int DAILY_MINUTE = Integer.valueOf(resources.getString(R.string.reminder_daily_minute));
            final int DAILY_SECOND = Integer.valueOf(resources.getString(R.string.reminder_daily_second));

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REQUEST_CODE, intent, 0);

            if(sharedPreferences.getBoolean(DAILY_REMINDER_PREF, false)) {
                calendar.set(Calendar.HOUR_OF_DAY, DAILY_HOUR);
                calendar.set(Calendar.MINUTE, DAILY_MINUTE);
                calendar.set(Calendar.SECOND, DAILY_SECOND);
                if(calendar.getTimeInMillis() < System.currentTimeMillis()) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            } else {
                alarmManager.cancel(pendingIntent);
            }
        } else if(key.equals(NEWEST_REMINDER_PREF)) {
            AlarmManager alarmManager  = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();

            final int NEWEST_REQUEST_CODE = Integer.valueOf(resources.getString(R.string.reminder_newest_request_code));
            Intent intent = new Intent(context, NewestReminderReceiver.class);
            final int NEWEST_HOUR = Integer.valueOf(resources.getString(R.string.reminder_newest_hour_of_day));
            final int NEWEST_MINUTE = Integer.valueOf(resources.getString(R.string.reminder_newest_minute));
            final int NEWEST_SECOND = Integer.valueOf(resources.getString(R.string.reminder_newest_second));

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NEWEST_REQUEST_CODE, intent, 0);

            if(sharedPreferences.getBoolean(NEWEST_REMINDER_PREF, false)) {
                calendar.set(Calendar.HOUR_OF_DAY, NEWEST_HOUR);
                calendar.set(Calendar.MINUTE, NEWEST_MINUTE);
                calendar.set(Calendar.SECOND, NEWEST_SECOND);
                if(calendar.getTimeInMillis() < System.currentTimeMillis()) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            } else {
                alarmManager.cancel(pendingIntent);
            }
        }
    }
}
