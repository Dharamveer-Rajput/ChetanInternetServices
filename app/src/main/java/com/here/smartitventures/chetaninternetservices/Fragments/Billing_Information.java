package com.here.smartitventures.chetaninternetservices.Fragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.here.smartitventures.chetaninternetservices.R;

public class Billing_Information extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_billing__information,
                container, false);

        return view;
    }
}
