package com.here.smartitventures.chetaninternetservices.Activity;

import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.here.smartitventures.chetaninternetservices.Adapter.DataAdapter;
import com.here.smartitventures.chetaninternetservices.Model.DataModelSession;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.SessionDetails.SessionArray;
import com.here.smartitventures.chetaninternetservices.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class SessionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;

    ArrayList<SessionArray> sessionArrays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        dataAdapter = new DataAdapter(sessionArrays, SessionActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setAdapter(dataAdapter);


    }
}

