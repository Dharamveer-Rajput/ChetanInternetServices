package com.here.smartitventures.chetaninternetservices.Fragments;

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

import com.here.smartitventures.chetaninternetservices.Adapter.PaymentAdapter;
import com.here.smartitventures.chetaninternetservices.ApiClient.MyClient;
import com.here.smartitventures.chetaninternetservices.MyListener;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.PaymentDetails.PaymentModel;
import com.here.smartitventures.chetaninternetservices.SimpleDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PaymentFragment extends Fragment {

    private RecyclerView recyclerView;
    private PaymentAdapter paymentAdapter;
    Context mContext;

    public  static  ArrayList<PaymentModel> paymentModelsArrays;
    String bank;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String UserId = "userId";
    String userId;


    ProgressBar progressBarPayment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_linear,
                container, false);

        sharedpreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        progressBarPayment = (ProgressBar) view.findViewById(R.id.progressBarPayment);

        userId = sharedpreferences.getString(UserId,"");

        paymentModelsArrays = new ArrayList<>();

        mContext = container.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_payment);

        MyClient client = new MyClient("https://103.48.187.2:8000/api/v1/get_payment_details/"+userId,getActivity());

        client.myListener  = new MyListener() {
            @Override
            public void onResult(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    JSONArray jsonArray1 = jsonArray.getJSONArray(1);

                    JSONArray jsonArray2 = jsonArray.getJSONArray(3);

                    for (int i = 0;i<jsonArray2.length();i++){

                        JSONArray jsonArray3 = jsonArray2.getJSONArray(i);

                        try {
                            for (int k = 0; k<jsonArray3.length(); k++)
                            {
                                JSONArray jsonArray4 = jsonArray3.getJSONArray(k);

                                try{
                                    for(int j=0;j<jsonArray4.length();j++)
                                    {

                                        JSONObject jsonObject = jsonArray4.getJSONObject(j);

                                        JSONObject jsonObject1 = jsonObject.getJSONObject("PaymentInfoCheque");


                                        PaymentModel paymentModel = new PaymentModel();

                                        bank = jsonObject1.getString("bank");

                                        JSONObject jsonObject2 = jsonArray1.getJSONObject(i);

                                        JSONObject jsonObject3 = jsonObject2.getJSONObject("Payment");

                                        String id_payment = jsonObject3.getString("id");
                                        String date = jsonObject3.getString("create_datetime");
                                        String amount = jsonObject3.getString("amount");

                                        paymentModel.setId_payment(id_payment);
                                        paymentModel.setDate(date);
                                        paymentModel.setAmount(amount);
                                        paymentModel.setMethod_type(bank);

                                        paymentModelsArrays.add(paymentModel);

                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                paymentAdapter = new PaymentAdapter(mContext,paymentModelsArrays);
                                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                                recyclerView.setLayoutManager(mLayoutManager);
                                                recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
                                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                                recyclerView.setAdapter(paymentAdapter);

                                                progressBarPayment.setVisibility(View.INVISIBLE);

                                            }
                                        });

                                    }
                                }catch (Exception e){

                                }
                            }
                        }catch (Exception e){

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };

        try {
            client.Execute(0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }


}
