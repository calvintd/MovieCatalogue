package com.made.calvintd.moviecatalogue.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.made.calvintd.moviecatalogue.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowSearchFragment extends Fragment {

    public TvShowSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_search, container, false);
    }
}
