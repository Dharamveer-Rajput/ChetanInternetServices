package com.here.smartitventures.chetaninternetservices.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.here.smartitventures.chetaninternetservices.Adapter.BillAdapter;
import com.here.smartitventures.chetaninternetservices.Adapter.DataAdapter;
import com.here.smartitventures.chetaninternetservices.Model.DataModelSession;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.BillResponse.GetOneBillDetail;
import com.here.smartitventures.chetaninternetservices.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;



public class BillActivity extends AppCompatActivity {


    private List<GetOneBillDetail> dataModelBills = new ArrayList<>();
    private RecyclerView recyclerView;
    private BillAdapter billAdapter;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        billAdapter = new BillAdapter(mContext,dataModelBills);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(billAdapter);

    }

}




