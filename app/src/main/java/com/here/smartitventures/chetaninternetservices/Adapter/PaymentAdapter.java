package com.here.smartitventures.chetaninternetservices.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.here.smartitventures.chetaninternetservices.DialogClass.PaymentViewDialog;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.PaymentDetails.PaymentModel;

import java.text.DecimalFormat;
import java.util.List;


public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {

    private List<PaymentModel> dataModelPaymentList;

    Context mContext;
    LayoutInflater mInflater;


    public PaymentAdapter(Context context,List<PaymentModel> dataModelPayments) {
        this.dataModelPaymentList = dataModelPayments;
        this.mContext=context;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public PaymentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_linear_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PaymentAdapter.MyViewHolder holder, final int position) {
        PaymentModel paymentModel = dataModelPaymentList.get(position);

        String str  = paymentModel.getDate();

        String[] arr = str.split(" ");

        double number = Double.parseDouble(paymentModel.getAmount());


        DecimalFormat numberFormat = new DecimalFormat("#.00");


        holder.txtPayment.setText(paymentModel.getId_payment());
        holder.txtPaymentdate.setText(arr[0]);


        holder.txtPaymentMethod.setText(paymentModel.getMethod_type());

        holder.txtPaymentAmt.setText(numberFormat.format(number));


        holder.txtPaymentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PaymentViewDialog paymentViewDialog = new PaymentViewDialog(mContext,dataModelPaymentList.get(position));

                paymentViewDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModelPaymentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtPayment,txtPaymentdate,txtPaymentMethod,txtPaymentAmt,txtPaymentDetails;
        Button btnPaymentType;


        public MyViewHolder(View itemView) {
            super(itemView);

            txtPayment = (TextView) itemView.findViewById(R.id.txtPayment);
            txtPaymentdate = (TextView) itemView.findViewById(R.id.txtPaymentdate);
            txtPaymentMethod = (TextView) itemView.findViewById(R.id.txtPaymentMethod);
            txtPaymentAmt = (TextView) itemView.findViewById(R.id.txtPaymentAmt);
            txtPaymentDetails = (TextView) itemView.findViewById(R.id.txtPaymentDetails);

            btnPaymentType  = (Button) itemView.findViewById(R.id.btnPaymentType);



        }
    }
}
