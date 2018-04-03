package com.here.smartitventures.chetaninternetservices.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.here.smartitventures.chetaninternetservices.DialogClass.BillViewDialog;
import com.here.smartitventures.chetaninternetservices.Fragments.BillFragment;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.BillResponse.GetOneBillDetail;

import java.text.DecimalFormat;
import java.util.List;


public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyViewHolder> {

    private List<GetOneBillDetail> getOneBillDetails;
    Context mContext;
    LayoutInflater mInflater;



    public BillAdapter(Context context,List<GetOneBillDetail> getOneBillDetails) {
        this.getOneBillDetails = getOneBillDetails;
        this.mContext=context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BillAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_row_linear1, parent, false);
        return new MyViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(BillAdapter.MyViewHolder holder, final int position) {
        final GetOneBillDetail getOneBillDetail = getOneBillDetails.get(position);


        /*split the amount coming from server in two format one is 52,325 and  2nd is "CISPL/2017/686"*/
        String strInvoiceNo = getOneBillDetail.getInvoiceNumber();

        if(strInvoiceNo.matches(".*[a-z].*")) {
            String[] stringsArr = strInvoiceNo.split("/");
            holder.txtInvoice.setText(stringsArr[2]);
        }
        else {
            holder.txtInvoice.setText(strInvoiceNo);

        }

        String str  = getOneBillDetail.getBillGeneratedDate();
        String[] arr = str.split(" ");
        //if (arr=){}
        holder.txtdate.setText(arr[0]);

        /*set the amount*/
        String billAmt = getOneBillDetail.getAmount();

        if(billAmt.equalsIgnoreCase("null")){
            holder.txtAmt.setText("0.00");

        }
        else {
            double number = Double.parseDouble(billAmt);
            DecimalFormat numberFormat = new DecimalFormat("#.0");
            holder.txtAmt.setText(numberFormat.format(number));
        }


        holder.txtBal.setText("0.00");
        holder.txtDetailsView.setText("View");

        holder.btnStatus.setText(getOneBillDetail.getStatus());
        holder.txtDetailsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BillViewDialog billViewDialog = new BillViewDialog(mContext,getOneBillDetails.get(position));
                billViewDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return getOneBillDetails.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtInvoice,txtdate,txtAmt,txtBal,txtDetailsView;
        Button btnStatus;


        public MyViewHolder(View itemView) {
            super(itemView);

            txtInvoice = (TextView) itemView.findViewById(R.id.txtInvoice);
            txtdate = (TextView) itemView.findViewById(R.id.txtdate);
            txtAmt = (TextView) itemView.findViewById(R.id.txtAmt);
            txtBal = (TextView) itemView.findViewById(R.id.txtBal);
            txtDetailsView = (TextView) itemView.findViewById(R.id.txtDetailsView);

            btnStatus  = (Button) itemView.findViewById(R.id.btnStatusBill2);


        }


    }
}
