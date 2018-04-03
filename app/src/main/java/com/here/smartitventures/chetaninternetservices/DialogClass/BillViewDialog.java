package com.here.smartitventures.chetaninternetservices.DialogClass;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.BillResponse.GetAllBillsModel;
import com.here.smartitventures.chetaninternetservices.Response.Response.BillResponse.GetOneBillDetail;

public class BillViewDialog extends Dialog implements View.OnClickListener {


    Context ctx;
    Button mbtnOk;

    GetOneBillDetail getOneBillDetail;


    TextView tvTotalAmount,tvInvoiceDetails;

    GetAllBillsModel getAllBillsModel;

    public BillViewDialog(Context context,GetOneBillDetail getOneBillDetail2) {
        super(context);
        this.getOneBillDetail = getOneBillDetail2;
        ctx= context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_bill_view_dialog);

        getAllBillsModel = GetAllBillsModel.getInstance();

        tvInvoiceDetails = (TextView) findViewById(R.id.tvInvoiceDetails);
        tvTotalAmount = (TextView) findViewById(R.id.tvTotalAmount);

        tvInvoiceDetails.setText(getAllBillsModel.get474());
        tvTotalAmount.setText(getOneBillDetail.getAmount());
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
