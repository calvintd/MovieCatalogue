package com.made.calvintd.moviecatalogue.Fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.made.calvintd.moviecatalogue.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageFragment extends DialogFragment {
    @BindView(R.id.rb_language_en) RadioButton rbLanguageEN;
    @BindView(R.id.rb_language_in) RadioButton rbLanguageIN;
    private Configuration config;
    OnOptionDialogListener optionDialogListener;

    public LanguageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language, container, false);

        ButterKnife.bind(this, view);

        config = this.getResources().getConfiguration();
        final Locale inLocale = new Locale("in");
        final Locale enLocale = Locale.ENGLISH;

        if(config.locale != null) {
            if(config.locale.equals(inLocale)) {
                rbLanguageIN.toggle();
            } else if(!config.locale.equals(inLocale)) {
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

        /*
        btnLanguageChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(rgLanguage.getCheckedRadioButtonId()) {
                    case R.id.rb_language_en:
                        config.setLocale(enLocale);
                        config.setLayoutDirection(enLocale);
                        break;
                    case R.id.rb_language_in:
                        config.setLocale(inLocale);
                        config.setLayoutDirection(inLocale);
                        break;
                }
                Toast.makeText(v.getContext(), getResources().getString(R.string.language_toast_chosen), Toast.LENGTH_SHORT).show();
                onConfigurationChanged(config);
            }
        });
        */

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();

        if (fragment instanceof LanguageFragment) {
            LanguageFragment detailCategoryFragment = (LanguageFragment) fragment;
            this.optionDialogListener = detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        Resources res = this.getResources();

        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public interface OnOptionDialogListener {
        void onOptionChosen(String text);
    }

}
