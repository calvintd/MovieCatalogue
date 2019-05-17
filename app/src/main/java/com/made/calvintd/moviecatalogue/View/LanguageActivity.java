package com.made.calvintd.moviecatalogue.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.made.calvintd.moviecatalogue.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguageActivity extends AppCompatActivity {
    @BindView(R.id.rg_language) RadioGroup rgLanguage;
    @BindView(R.id.btn_language_choose) Button btnLanguageChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        ButterKnife.bind(this);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.language_activity_title));
        }

        btnLanguageChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(rgLanguage.getCheckedRadioButtonId()) {
                    case R.id.rb_language_en:
                        break;
                    case R.id.rb_language_in:
                        break;
                }
                Toast.makeText(v.getContext(), getResources().getString(R.string.language_toast_chosen), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
