package com.made.calvintd.favoritemoviecatalogue.fragment;


import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.made.calvintd.favoritemoviecatalogue.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageFragment extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.rg_language)
    RadioGroup rgLanguage;
    @BindView(R.id.rb_language_en)
    RadioButton rbLanguageEN;
    @BindView(R.id.rb_language_in) RadioButton rbLanguageIN;
    @BindView(R.id.btn_language_choose)
    Button btnChoose;
    @BindView(R.id.btn_language_close) Button btnClose;
    private Configuration config;
    final Locale inLocale = new Locale("in");
    final Locale enLocale = Locale.ENGLISH;

    public LanguageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        btnChoose.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        config = this.getResources().getConfiguration();

        if(config.locale != null) {
            if(config.locale.equals(inLocale)) {
                rbLanguageIN.toggle();
            } else if(!config.locale.equals(enLocale)) {
                rbLanguageEN.toggle();
            } else {
                Locale defaultLocale = Locale.getDefault();
                if (defaultLocale.equals(inLocale)) {
                    rbLanguageIN.toggle();
                } else {
                    rbLanguageEN.toggle();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_language_choose:
                switch(rgLanguage.getCheckedRadioButtonId()) {
                    case R.id.rb_language_en:
                        if (config.locale.equals(enLocale)) {
                            showToastCurrentLanguage();
                        } else {
                            config.setLocale(enLocale);
                            config.setLayoutDirection(enLocale);
                            onConfigurationChanged(config);
                            showToastChanged();
                        }
                        break;
                    case R.id.rb_language_in:
                        if (config.locale.equals(inLocale)) {
                            showToastCurrentLanguage();
                        } else {
                            config.setLocale(inLocale);
                            config.setLayoutDirection(inLocale);
                            onConfigurationChanged(config);
                            showToastChanged();
                        }
                        break;
                }
            case R.id.btn_language_close:
                this.dismiss();
                break;
        }
    }

    private void showToastCurrentLanguage() {
        Toast.makeText(this.getContext(), getResources().getString(R.string.language_message_currently_used), Toast.LENGTH_SHORT).show();
    }

    private void showToastChanged() {
        Toast.makeText(this.getContext(), getResources().getString(R.string.language_message_chosen), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        Resources res = this.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();

        res.updateConfiguration(config, dm);
        if (getActivity() != null) {
            getActivity().recreate();
        }
    }
}
