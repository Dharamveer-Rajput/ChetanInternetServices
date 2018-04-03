package com.here.smartitventures.chetaninternetservices.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.here.smartitventures.chetaninternetservices.CustomGridViewActivity;
import com.here.smartitventures.chetaninternetservices.ApiClient.MyClient;
import com.here.smartitventures.chetaninternetservices.MyListener;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetBalance.GetBalance;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails.CurrentBillingCycleUsage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ProfileFragment extends Fragment {


    GridView androidGridView;
    String[] gridViewString = {
            "Data Used", "Data Remaining", "Total Data",
            "Balance"
    };
    int[] gridViewImageId = {
            R.drawable.data, R.drawable.remaning, R.drawable.used,
            R.drawable.total

    };

    double balance;

    String uploadDataUsage,downloadDataUsage;

    double totalDataUsage;
    double dataRemaining;

    long totalBandwidthQuota;

    CurrentBillingCycleUsage currentBillingCycleUsage;

    Bundle bundle;

    MyClient client;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String UserId = "userId";
    String userId;

    //ProgressBar progressBarProfile;

    PieChart pieChart1,pieChart2,pieChart3,pieChart4;

    CardView onlybalCard;
    TextView tv_one_balance;

    double num,number;
    DecimalFormat decimalFormat,numberFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_layout, container, false);

//        androidGridView = (GridView) view.findViewById(R.id.grid_view_image_text);
//        onlybalCard = (CardView) view.findViewById(R.id.onlybalCard);
//        tv_one_balance = (TextView) view.findViewById(R.id.tv_one_balance);

        pieChart1 = (PieChart) view.findViewById(R.id.piechart1);
        pieChart2 = (PieChart) view.findViewById(R.id.piechart2);
        pieChart3 = (PieChart) view.findViewById(R.id.piechart3);
        pieChart4 = (PieChart) view.findViewById(R.id.piechart4);

        pieChart1.setUsePercentValues(true);
        pieChart2.setUsePercentValues(true);
        pieChart3.setUsePercentValues(true);
        pieChart4.setUsePercentValues(true);


        GetBalance getBalance1 = GetBalance.getInstance();

        balance = getBalance1.getBalance();

        bundle = this.getArguments();

        sharedpreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        //progressBarProfile = (ProgressBar) view.findViewById(R.id.progressBarProfile);

        userId = sharedpreferences.getString(UserId,"");

        //String userId1 = SharedPrefrenceUtils.getString(getActivity(),"userId");







        client = new MyClient("https://103.48.187.2:8000//api/v1/get_balance/"+userId,getActivity());


        client.myListener  = new MyListener() {
            @Override
            public void onResult(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    balance = (jsonObject.getDouble("balance"));

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



        client = new MyClient("https://103.48.187.2:8000/api/v1/get_details/"+userId,getActivity());


        client.myListener  = new MyListener() {
            @Override
            public void onResult(String response) {

                try {

                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray jsonArray = jsonObj.getJSONArray("data");

                    for(int i = 1;i<jsonArray.length();i++){

                        /* -----------------------------Current Billing Cycle Object------------------------------------*/

                        JSONObject jsonObject2 = jsonArray.getJSONObject(1);
                        JSONObject jsonObjectBillingCycle = jsonObject2.getJSONObject("currentBillingCycleUsage");

                        totalDataUsage = jsonObjectBillingCycle.getDouble("totalDataUsage");
                        uploadDataUsage = jsonObjectBillingCycle.getString("uploadDataUsage");
                        downloadDataUsage = jsonObjectBillingCycle.getString("downloadDataUsage");

                        totalBandwidthQuota = jsonObjectBillingCycle.getLong("totalBandwidthQuota");

                        currentBillingCycleUsage = CurrentBillingCycleUsage.getInstance();

                        currentBillingCycleUsage.setTotalDataUsage(totalDataUsage);
                        currentBillingCycleUsage.setUploadDataUsage(uploadDataUsage);
                        currentBillingCycleUsage.setDownloadDataUsage(downloadDataUsage);

                        currentBillingCycleUsage.setTotalBandwidthQuota(totalBandwidthQuota);

                        totalDataUsage  = currentBillingCycleUsage.getTotalDataUsage();
                        downloadDataUsage = currentBillingCycleUsage.getDownloadDataUsage();


                        totalBandwidthQuota = currentBillingCycleUsage.getTotalBandwidthQuota();

                        totalDataUsage = totalDataUsage/1073741824;

                        totalBandwidthQuota = totalBandwidthQuota/1073741824;

                        dataRemaining = totalBandwidthQuota-totalDataUsage;

                        number = totalDataUsage;

                        numberFormat = new DecimalFormat("#.00");

                        num = dataRemaining;

                        decimalFormat = new DecimalFormat("#.00");

                        /*----------------------------------------------PieChart1------------------------------------------------------------------------*/


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //For pieChart1
                                ArrayList<Entry> yvalues = new ArrayList<Entry>();
                                yvalues.add(new Entry(5f, 0));
                                yvalues.add(new Entry(25f, 1));


                                //For pieChart2
                                ArrayList<Entry> yvalues2 = new ArrayList<Entry>();
                                yvalues2.add(new Entry(10f, 0));
                                yvalues2.add(new Entry(15f, 1));

                                //For pieChart3
                                ArrayList<Entry> yvalues3 = new ArrayList<Entry>();
                                yvalues3.add(new Entry(35f, 1));

                                //For pieChart4
                                ArrayList<Entry> yvalues4 = new ArrayList<Entry>();
                                yvalues4.add(new Entry(55f, 1));



                                //for pieChart1
                                final PieDataSet dataSet1 = new PieDataSet(yvalues, "");
                                final ArrayList<String> xVals = new ArrayList<String>();
                                xVals.add("");
                                xVals.add("Data used");


                                //for pieChart2
                                final PieDataSet dataSet2 = new PieDataSet(yvalues2, "");
                                final ArrayList<String> xVal2 = new ArrayList<String>();
                                xVal2.add("");
                                xVal2.add("");

                                //for pieChart3
                                final PieDataSet dataSet3 = new PieDataSet(yvalues3, "");
                                final ArrayList<String> xVal3 = new ArrayList<String>();
                                xVal3.add("Data");
                                xVal3.add("");


                                //for pieChart4
                                final PieDataSet dataSet4 = new PieDataSet(yvalues4, "");
                                final ArrayList<String> xVal4 = new ArrayList<String>();
                                xVal4.add("Balance");
                                xVal4.add("");



                                //add colors to dataset
                                final ArrayList<Integer> colors = new ArrayList<>();
                                colors.add(Color.LTGRAY);
                                colors.add(Color.BLUE);

                                final ArrayList<Integer> colors4 = new ArrayList<>();
                                colors4.add(Color.BLUE);
                                colors4.add(Color.LTGRAY);


                                final ArrayList<Integer> colors3 = new ArrayList<>();
                                colors3.add(Color.rgb(52,161,12));
                                colors3.add(Color.LTGRAY);


                                //for pieChart1
                                PieData data = new PieData(xVals, dataSet1);
                                data.setValueFormatter(new PercentFormatter());
                                data.setDrawValues(false);
                                pieChart1.setData(data);
                                pieChart1.setDrawSliceText(false);
                                pieChart1.setDescription("");
                                pieChart1.setDrawHoleEnabled(true);
                                pieChart1.setTransparentCircleRadius(25f);
                                pieChart1.setHoleRadius(80f);
                                pieChart1.setTransparentCircleRadius(10);
                                pieChart1.setCenterText(numberFormat.format(number));

                                pieChart1.setCenterTextSize(20);
                                pieChart1.setTouchEnabled(true);


                                dataSet1.setColors(colors);
                                data.setValueTextSize(13f);
                                data.setValueTextColor(Color.WHITE);
                                pieChart1.animateXY(1400, 1400);
                                pieChart1.invalidate();


                                   /*----------------------------------------------PieChart2------------------------------------------------------------------------*/


                                //for pieChart2
                                PieData data2 = new PieData(xVal2, dataSet2);
                                data2.setValueFormatter(new PercentFormatter());
                                data2.setDrawValues(false);
                                pieChart2.setData(data2);
                                pieChart2.setDrawSliceText(false);

                                pieChart2.setDrawHoleEnabled(true);
                                pieChart2.setTransparentCircleRadius(25f);
                                pieChart2.setDescription("");
                                pieChart2.setHoleRadius(80f);
                                pieChart2.setCenterText(decimalFormat.format(num));
                                //pieChart2.setCenterText("25.1 GB");
                                pieChart2.setCenterTextSize(20);
                                pieChart2.setTouchEnabled(true);


                                dataSet2.setColors(colors);
                                data2.setValueTextSize(13f);
                                data2.setValueTextColor(Color.WHITE);
                                //pieChart.setOnChartValueSelectedListener(this);
                                pieChart2.animateXY(1400, 1400);
                                pieChart2.invalidate();

        /*----------------------------------------------PieChart3------------------------------------------------------------------------*/


                                //for pieChart3
                                PieData data3 = new PieData(xVal3, dataSet3);
                                data3.setValueFormatter(new PercentFormatter());
                                data3.setDrawValues(false);
                                pieChart3.setData(data3);
                                pieChart3.setDrawSliceText(false);

                                pieChart3.setDrawHoleEnabled(true);
                                pieChart3.setDescription("");
                                pieChart3.setTransparentCircleRadius(25f);
                                pieChart3.setHoleRadius(80f);
                                pieChart3.setCenterText(String.valueOf(totalBandwidthQuota)+" GB");
                                pieChart3.setCenterTextSize(20);
                                pieChart3.setTouchEnabled(true);


                                dataSet3.setColors(colors3);
                                data3.setValueTextSize(13f);
                                data3.setValueTextColor(Color.WHITE);
                                pieChart3.animateXY(1400, 1400);
                                pieChart3.invalidate();

/*----------------------------------------------PieChart4------------------------------------------------------------------------*/
                                //for pieChart4
                                PieData data4 = new PieData(xVal4, dataSet4);
                                data4.setValueFormatter(new PercentFormatter());
                                data4.setDrawValues(false);
                                pieChart4.setData(data4);
                                pieChart4.setDescription("");
                                pieChart4.setDrawSliceText(false);

                                pieChart4.setDrawHoleEnabled(true);
                                pieChart4.setTransparentCircleRadius(25f);
                                pieChart4.setHoleRadius(80f);
                                pieChart4.setCenterText(String.valueOf(balance));
                                pieChart4.setCenterTextSize(20);
                                pieChart4.setTouchEnabled(true);

                                dataSet4.setColors(colors4);
                                data4.setValueTextSize(13f);
                                data4.setValueTextColor(Color.WHITE);
                                pieChart4.animateXY(1400, 1400);
                                pieChart4.invalidate();



                            }
                        });


                       /* final Double[] gridViewStringData =
                                {
                                        Double.valueOf(numberFormat.format(number)),
                                        Double.valueOf(decimalFormat.format(num)),
                                        Double.valueOf(totalBandwidthQuota),
                                        balance
                                };

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                               if(gridViewStringData[1]<0)
                                {
                                    onlybalCard.setVisibility(View.VISIBLE);
                                    String stringdouble= Double.toString(balance);
                                    tv_one_balance.setText("Rs. "+ stringdouble);

                                }
                                else
                                {

                                    onlybalCard.setVisibility(View.INVISIBLE);
                                    CustomGridViewActivity adapterViewAndroid =
                                            new CustomGridViewActivity(getActivity(), gridViewString, gridViewImageId, gridViewStringData);
                                    androidGridView.setAdapter(adapterViewAndroid);

                               }


                                progressBarProfile.setVisibility(View.INVISIBLE);

                            }
                        });
*/

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
