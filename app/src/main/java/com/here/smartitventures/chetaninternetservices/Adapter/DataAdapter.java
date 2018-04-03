package com.here.smartitventures.chetaninternetservices.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.here.smartitventures.chetaninternetservices.Model.DataModelSession;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.SessionDetails.SessionArray;
import com.here.smartitventures.chetaninternetservices.Utils.Util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<SessionArray> recyclerViewItems;

    float gb;

    float mb;
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public static int sum = 0;

    public static boolean add= true;


    private Context context;

    private boolean isLoadingAdded = false;

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new SessionArray());
    }


    public void add(SessionArray r) {
        recyclerViewItems.add(r);
        notifyItemInserted(recyclerViewItems.size() - 1);
    }

    public void addAll(List<SessionArray> moveResults) {

        for(SessionArray sessionArray:moveResults){
            add(sessionArray);
        }
    }


    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = recyclerViewItems.size() - 1;

        SessionArray result = getItem(position);

        if (result != null) {
            recyclerViewItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    final public SessionArray getItem(int position) {
        return recyclerViewItems.get(position);
    }


    public DataAdapter(List<SessionArray> recyclerViewItems, Context context) {
        this.recyclerViewItems = recyclerViewItems;
        this.context = context;
    }


    public void add(List<SessionArray> items) {
        int previousDataSize = this.recyclerViewItems.size();
        this.recyclerViewItems.addAll(items);
        notifyItemRangeInserted(previousDataSize, items.size());
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView date, downloadBytes, uploadBytes;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            downloadBytes = (TextView) view.findViewById(R.id.downloadBytes);
            uploadBytes = (TextView) view.findViewById(R.id.uploadBytes);
        }
    }

    protected class LoadingVH extends MyViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder myViewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                myViewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                myViewHolder = new LoadingVH(v2);
                break;
        }

        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {


        final SessionArray item = recyclerViewItems.get(i);

        switch (getItemViewType(i)) {
            case ITEM:


                final MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

                String str = item.getStart_time();
                String[] arr = str.split(" ");

                //---------------------For downloadBytes
                long downloadBytes = Long.parseLong(item.getDownloadBytes());

                int lengthDownloadBytes = (int) (Math.log10(downloadBytes) + 1);


                if (lengthDownloadBytes > 8) {
                    gb = (1073741824 / downloadBytes);

                    String formatgb = String.format("%.02f", gb);

                    myViewHolder.downloadBytes.setText(String.valueOf(formatgb) + " GB");


                } else {
                    mb = (downloadBytes / 1073741824);

                    String formatmb = String.format("%.02f", gb);

                    myViewHolder.downloadBytes.setText(String.valueOf(formatmb) + " MB");

                }

                //-----------------------For upload bytes
                long uploadbytes = Long.parseLong(item.getUploadBytes());

                int lengthUploadbytes = (int) (Math.log10(uploadbytes) + 1);


                if (lengthUploadbytes > 8) {
                    gb = (1073741824 / uploadbytes);

                    String formatgb = String.format("%.02f", gb);
                    myViewHolder.uploadBytes.setText(String.valueOf(formatgb) + " GB");
                } else {

                    mb = (uploadbytes / 1073741824);
                    String formatmb = String.format("%.02f", gb);
                    myViewHolder.uploadBytes.setText(String.valueOf(formatmb) + " MB");
                }


                myViewHolder.date.setText(arr[0]);


                if (add) {
                    sum = Integer.parseInt(sum + item.getDownloadBytes());

                    add = false;
                }


                break;
            case LOADING:
                // DO nothing
                break;
        }

    }

    private MyViewHolder getViewHolder(ViewGroup parent,LayoutInflater inflater){
        MyViewHolder myViewHolder;

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_row, parent, false);

        myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

   /* @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final SessionArray item = recyclerViewItems.get(position);



        switch (getItemViewType(position)) {
            case ITEM:

                String str = item.getStart_time();
                String[] arr = str.split(" ");

                //---------------------For downloadBytes
                long downloadBytes = Long.parseLong(item.getDownloadBytes());

                int lengthDownloadBytes = (int) (Math.log10(downloadBytes) + 1);


                if (lengthDownloadBytes > 8) {
                    gb = (1073741824 / downloadBytes);

                    String formatgb = String.format("%.02f", gb);

                    holder.downloadBytes.setText(String.valueOf(formatgb) + " GB");


                } else {
                    mb = (downloadBytes / 1073741824);

                    String formatmb = String.format("%.02f", gb);

                    holder.downloadBytes.setText(String.valueOf(formatmb) + " MB");

                }

                //-----------------------For upload bytes
                long uploadbytes = Long.parseLong(item.getUploadBytes());

                int lengthUploadbytes = (int) (Math.log10(uploadbytes) + 1);


                if (lengthUploadbytes > 8) {
                    gb = (1073741824 / uploadbytes);

                    String formatgb = String.format("%.02f", gb);
                    holder.uploadBytes.setText(String.valueOf(formatgb) + " GB");
                } else {

                    mb = (uploadbytes / 1073741824);
                    String formatmb = String.format("%.02f", gb);
                    holder.uploadBytes.setText(String.valueOf(formatmb) + " MB");
                }


                holder.date.setText(arr[0]);


                if (add) {
                    sum = Integer.parseInt(sum + item.getDownloadBytes());

                    add = false;
                }


                break;
            case LOADING:
                // DO nothing
                break;
        }
    }*/


    @Override
    public int getItemViewType(int position) {
        return (position == recyclerViewItems.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return recyclerViewItems == null ? 0 : recyclerViewItems.size();
    }
}

