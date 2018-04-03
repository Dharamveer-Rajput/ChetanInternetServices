package com.here.smartitventures.chetaninternetservices.Fragments;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails.CurrentBillingCycleUsage;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails.User;


public class General_Information extends Fragment {


    TextView mtvusername,mtvName,mtvPlanName,mtvMobileNo,mtvemailId,mtvAddress,mtvActivationDate,mtvExpirationDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.general_information_layout,
                container, false);

        mtvusername = (TextView) view.findViewById(R.id.tvusername);
        mtvName = (TextView) view.findViewById(R.id.tvName);
        mtvPlanName = (TextView) view.findViewById(R.id.tvPlanName);
        mtvMobileNo = (TextView) view.findViewById(R.id.tvMobileNo);
        mtvemailId = (TextView) view.findViewById(R.id.tvemailId);
        mtvAddress = (TextView) view.findViewById(R.id.tvAddress);
        mtvActivationDate = (TextView) view.findViewById(R.id.tvActivationDate);
        mtvExpirationDate = (TextView) view.findViewById(R.id.tvExpirationDate);

        CurrentBillingCycleUsage currentBillingCycleUsage = CurrentBillingCycleUsage.getInstance();


        double totolData = currentBillingCycleUsage.getTotalBandwidthQuota();

        totolData = totolData/1073741824;

        String totalStringData = String.valueOf(totolData);



        User user = User.getInstance();
        String username = user.getUsername();
        String name = user.getName();
        String mobile = user.getPhone();
        String email = user.getEmail();
        String address = user.getAddressLine1();
        String activationDate = user.getActivationTime();
        String expirationDate = user.getExpirationTime();


        mtvusername.setText(username);
        mtvName.setText(name);
        mtvPlanName.setText(totalStringData+" GB");
        mtvMobileNo.setText(mobile);
        mtvemailId.setText(email);
        mtvAddress.setText(address);
        mtvActivationDate.setText(activationDate);
        mtvExpirationDate.setText(expirationDate);


        return view;
    }
}
