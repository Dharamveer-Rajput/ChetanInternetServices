package com.here.smartitventures.chetaninternetservices.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.here.smartitventures.chetaninternetservices.Adapter.BillAdapter;
import com.here.smartitventures.chetaninternetservices.ApiClient.MyClient;
import com.here.smartitventures.chetaninternetservices.MyListener;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.BillResponse.GetAllBillsModel;
import com.here.smartitventures.chetaninternetservices.Response.Response.BillResponse.GetOneBillDetail;
import com.here.smartitventures.chetaninternetservices.SimpleDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BillFragment extends Fragment {

    private RecyclerView recyclerView;
    private BillAdapter billAdapter;

    Context mContext;

    public  static  ArrayList<GetOneBillDetail> getOneBillDetailArrayList;

    MyClient client;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String UserId = "userId";
    String userId;

    ProgressBar progressBarSession;

    GetOneBillDetail getOneBillDetail;

    JSONArray keysArrays;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bill_linerar, container, false);


        getOneBillDetailArrayList = new ArrayList<>();

        sharedpreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        userId = sharedpreferences.getString(UserId,"");

        progressBarSession =  view.findViewById(R.id.progressBarBill);

        mContext = container.getContext();

        recyclerView =  view.findViewById(R.id.recycler_view);

 /*------------------------------------------------------------------------------------------------------------------------------------------*/

        client = new MyClient("https://103.48.187.2:8000/api/v1/get_all_invoices_from_userid/"+userId,getActivity());


        try {
            client.Execute(0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        client.myListener = new MyListener() {
            @Override
            public void onResult(String response) {

                //1st try block
                try {

                    JSONArray jsonArray = new JSONArray(response);
//------------------------------1st for loop-------------------------------------------------------------------------------------------------
                    for(int i = 0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        keysArrays = jsonObject.names();

                    }



//------------------------------end of 1st for loop-------------------------------------------------------------------------------------------------


//--------------------------------2nd for loop-----------------------------------------------------------------------------------------------
                    for(int k=0;k<keysArrays.length();k++)
                    {

                        client = new MyClient("https://103.48.187.2:8000/api/v1/get_invoice_details/"+keysArrays.get(k),getActivity());

                        client.myListener = new MyListener() {
                            @Override
                            public void onResult(String response) {

                                JSONArray jsonArray1 = null;
                                try {
                                    jsonArray1 = new JSONArray(response);
                                } catch (JSONException e)
                                {e.printStackTrace();}

                                for (int j = 0; j<jsonArray1.length(); j++) {

                                    try {
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);

                                        getOneBillDetail = new GetOneBillDetail();

                                        getOneBillDetail.setInvoiceNumber(jsonObject1.getString("invoiceNumber"));
                                        getOneBillDetail.setBillGeneratedDate(jsonObject1.getString("billGeneratedDate"));
                                        getOneBillDetail.setStatus(jsonObject1.getString("status"));
                                        getOneBillDetail.setAmount(jsonObject1.getString("amount"));

                                        getOneBillDetailArrayList.add(getOneBillDetail);


                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                billAdapter = new BillAdapter(mContext, getOneBillDetailArrayList);
                                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                                recyclerView.setLayoutManager(mLayoutManager);
                                                recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
                                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                                recyclerView.setAdapter(billAdapter);

                                                progressBarSession.setVisibility(View.INVISIBLE);


                                            }
                                        });

                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }


                            }
                        };
                        //2nd try block
                        try {client.Execute(0);}

                        catch (Exception e) {e.printStackTrace();}//catch of 2nd block



                    }
/*---------------------------end of 2nd for loop----------------------------------------------------------------------*/

                }//end of 1st try block
                catch (Exception e) {e.printStackTrace();}//catch of 1st try block

            }
        };


        return view;
    }



}







