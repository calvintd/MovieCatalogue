package com.made.calvintd.moviecatalogue.fragment;


import android.content.Context;
import android.content.SharedPreferences;
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
public class ReminderFragment extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.switch_reminder_daily) Switch switchDaily;
    @BindView(R.id.switch_reminder_new_releases) Switch switchNewReleases;
    @BindView(R.id.btn_reminder_save) Button btnSave;
    @BindView(R.id.btn_reminder_close) Button btnClose;
    private SharedPreferences sharedPreferences;
    private Context context;
    private static final String REMINDER_PREF = "reminder_pref";

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
        sharedPreferences = context.getSharedPreferences(REMINDER_PREF, Context.MODE_PRIVATE);

        switchDaily.setChecked(sharedPreferences.getBoolean("daily", false));
        switchNewReleases.setChecked(sharedPreferences.getBoolean("new_releases", false));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_reminder_save:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("daily", switchDaily.isChecked());
                editor.putBoolean("new_releases", switchNewReleases.isChecked());
                editor.apply();
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
}
