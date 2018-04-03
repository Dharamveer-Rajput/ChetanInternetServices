package com.here.smartitventures.chetaninternetservices.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.here.smartitventures.chetaninternetservices.R;



public class Concurrent_Sessions extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.concurrent_session_layout,
                container, false);

        return view;
    }
}
