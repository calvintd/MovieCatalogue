package com.made.calvintd.moviecatalogue.View;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.made.calvintd.moviecatalogue.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguageActivity extends AppCompatActivity {
    @BindView(R.id.rg_language) RadioGroup rgLanguage;
    @BindView(R.id.rb_language_en) RadioButton rbLanguageEN;
    @BindView(R.id.rb_language_in) RadioButton rbLanguageIN;
    @BindView(R.id.btn_language_choose) Button btnLanguageChoose;
    private Configuration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        ButterKnife.bind(this);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.language_activity_title));
        }

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
            finish();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        Resources res = this.getResources();

        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
