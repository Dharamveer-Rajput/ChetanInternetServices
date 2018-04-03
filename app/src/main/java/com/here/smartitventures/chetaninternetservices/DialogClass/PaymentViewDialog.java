package com.here.smartitventures.chetaninternetservices.DialogClass;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.PaymentDetails.PaymentModel;

import java.util.List;

public class PaymentViewDialog extends Dialog implements View.OnClickListener {



    Context ctx;
    Button mbtnOk;

    PaymentModel dataModelPaymentList;
    TextView tvpaymentMethod,tvinvoiceId,tvinvoiceDate,paymentDetails;


    public PaymentViewDialog(Context context, PaymentModel dataModelPayments) {
        super(context);
        dataModelPaymentList= dataModelPayments;
        ctx= context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.activity_payment_view_dialog);

        paymentDetails = (TextView) findViewById(R.id.paymentDetails);


        tvpaymentMethod = (TextView) findViewById(R.id.tvpaymentMethod);
        tvinvoiceId = (TextView) findViewById(R.id.tvinvoiceId);
        tvinvoiceDate = (TextView) findViewById(R.id.tvinvoiceDate);


        paymentDetails.setText(dataModelPaymentList.getId_payment());
        tvpaymentMethod.setText("Cash");
        tvinvoiceId.setText(dataModelPaymentList.getId_payment());
        tvinvoiceDate.setText(dataModelPaymentList.getDate());

        mbtnOk = (Button) findViewById(R.id.btnOk);

        mbtnOk.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view == mbtnOk){
            dismiss();
        }
    }
}


