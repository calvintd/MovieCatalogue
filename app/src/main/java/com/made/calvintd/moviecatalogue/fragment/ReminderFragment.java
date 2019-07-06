package com.made.calvintd.moviecatalogue.fragment;


import android.content.Context;
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
    private String REMINDER_PREF;
    private String DAILY_REMINDER_PREF;
    private String NEWEST_REMINDER_PREF;

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
        Resources resources = context.getResources();
        REMINDER_PREF = resources.getString(R.string.shared_preferences_reminder);
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
                showToastSavedReminderPreferences();
                onSharedPreferenceChanged(sharedPreferences, REMINDER_PREF);
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
        if(key.equals(REMINDER_PREF)) {
            if (sharedPreferences.getBoolean(DAILY_REMINDER_PREF, false)) {

            } else {

            }

            if (sharedPreferences.getBoolean(NEWEST_REMINDER_PREF, false)) {

            } else {

            }
        }
    }
}
