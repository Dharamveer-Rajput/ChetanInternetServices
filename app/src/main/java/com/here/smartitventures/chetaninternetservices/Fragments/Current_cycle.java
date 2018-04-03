package com.here.smartitventures.chetaninternetservices.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.here.smartitventures.chetaninternetservices.DialogClass.BillViewDialog;
import com.here.smartitventures.chetaninternetservices.DialogClass.Previous_Cycle_Dialog;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails.CurrentBillingCycleUsage;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails.User;

import java.text.DecimalFormat;


public class Current_cycle extends Fragment {

    Button mbtnViewPreviousCycle;

    double totalDataUsage;
    long uploadData;


    TextView mtvUploadDataUsage,mtvDownloadDataUsage,mtvTotalDataUsage,tvCurrentBandwidth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_cycle_layout,
                container, false);

       // mbtnViewPreviousCycle = (Button) view.findViewById(R.id.btnViewPreviousCycle);
       // mbtnViewPreviousCycle.setOnClickListener(this);

        mtvUploadDataUsage = (TextView) view.findViewById(R.id.tvUploadDataUsage);
        mtvDownloadDataUsage = (TextView) view.findViewById(R.id.tvDownloadDataUsage);
        mtvTotalDataUsage = (TextView) view.findViewById(R.id.tvTotalDataUsage);
        tvCurrentBandwidth = (TextView) view.findViewById(R.id.tvCurrentBandwidth);


        CurrentBillingCycleUsage currentBillingCycleUsage = CurrentBillingCycleUsage.getInstance();


        /*-------------------------------upload data usage------------------------------------------*/


        uploadData = Long.parseLong(currentBillingCycleUsage.getUploadDataUsage());

        double uploaddata = uploadData;

        if(uploaddata>=1024 && uploaddata<1048576){

            double lengthdownloadbytes1 =  uploaddata/1024;

            String formatmb = String.format("%.02f", lengthdownloadbytes1);

            mtvUploadDataUsage.setText(formatmb+" MB");

        }

        else if(uploaddata>=1048576 && uploaddata<1073741824){

            double lengthdownloadbytes1 =  uploaddata/1048576;

            String formatmb = String.format("%.02f", lengthdownloadbytes1);

            mtvUploadDataUsage.setText(formatmb+" MB");

        }

        else if(uploaddata>=1073741824){

            double uploaddata1 =  uploaddata/1073741824;

            String formatmb = String.format("%.02f", uploaddata1);

            mtvUploadDataUsage.setText(formatmb+" GB");


        }



        /*-------------------------------download data usage------------------------------------------*/

        double downloadDataUsage = Double.parseDouble(currentBillingCycleUsage.getDownloadDataUsage());

        if(downloadDataUsage>=1024 && downloadDataUsage<1048576){

            double lengthdownloadbytes =  downloadDataUsage/1024;

            String formatmb = String.format("%.02f", lengthdownloadbytes);

            mtvDownloadDataUsage.setText(formatmb+" KB");


        }

        else if(downloadDataUsage>=1048576 && downloadDataUsage<1073741824){

            double lengthdownloadbytes =  downloadDataUsage/1048576;

            String formatmb = String.format("%.02f", lengthdownloadbytes);

            mtvDownloadDataUsage.setText(formatmb+" MB");

        }

        else if(downloadDataUsage>=1073741824){

            double lengthdownloadbytes =  downloadDataUsage/1073741824;

            String formatmb = String.format("%.02f", lengthdownloadbytes);

            mtvDownloadDataUsage.setText(formatmb+" GB");


        }

        /*-------------------------------download data usage------------------------------------------*/


        /*-------------------------------total data usage------------------------------------------*/

        totalDataUsage = currentBillingCycleUsage.getTotalDataUsage();

        //int lengthTotalBytes = (int)(Math.log10(TotalData)+1);

        if(totalDataUsage>=1024 && totalDataUsage<1048576){

            double lengthTotalbytes1 = totalDataUsage/1024;

            String formatgb = String.format("%.02f", lengthTotalbytes1);

            mtvTotalDataUsage.setText(formatgb + " KB");
        }

        else if(totalDataUsage>=1048576 && totalDataUsage<1073741824){

            double lengthTotalbytes1 = totalDataUsage/1048576;

            String formatgb = String.format("%.02f", lengthTotalbytes1);

            mtvTotalDataUsage.setText(formatgb + " MB");
        }

        else if(totalDataUsage>=1073741824){

            double lengthTotalbytes1 = totalDataUsage/1073741824;

            String formatgb = String.format("%.02f", lengthTotalbytes1);

            mtvTotalDataUsage.setText(formatgb + " GB");
        }
        /*-------------------------------total data usage------------------------------------------*/



        tvCurrentBandwidth.setText(currentBillingCycleUsage.getBandwidthTemplateName());


        return view;
    }

    /*@Override
    public void onClick(View view) {

        if(view==mbtnViewPreviousCycle){

            Previous_Cycle_Dialog previous_cycle_dialog = new Previous_Cycle_Dialog(getActivity());
            previous_cycle_dialog.show();

        }
    }*/
}
