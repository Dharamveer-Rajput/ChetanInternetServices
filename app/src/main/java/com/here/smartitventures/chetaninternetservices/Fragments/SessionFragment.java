package com.here.smartitventures.chetaninternetservices.Fragments;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.here.smartitventures.chetaninternetservices.Activity.MainActivity;
import com.here.smartitventures.chetaninternetservices.Adapter.DataAdapter;
import com.here.smartitventures.chetaninternetservices.Model.DataModelSession;
import com.here.smartitventures.chetaninternetservices.ApiClient.MyClient;
import com.here.smartitventures.chetaninternetservices.MyListener;
import com.here.smartitventures.chetaninternetservices.PaginationScrollListener;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.SessionDetails.SessionArray;
import com.here.smartitventures.chetaninternetservices.SimpleDividerItemDecoration;
import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemCreator;
import com.paginate.recycler.LoadingListItemSpanLookup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SessionFragment extends Fragment {

    private List<DataModelSession> dataList = new ArrayList<>();


    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    LinearLayout mdatePickerFrom, mdatePickerTo;
    TextView medtDateFrom, medtDateTo;

    ProgressBar progressBarSession;

    public static ArrayList<SessionArray> sessionArrays;

    public static List<SessionArray> newList;

    Button btnExport;

    OnSendArrayListDUActivityListener onSendArrayListDUActivityListener;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String UserId = "userId";
    String userId, fromDate, toDate;


    /*--------------------------------Pagination contents-----------------------------*/
    private TextView title;
    public int TOTAL_LIST_ITEMS;

    public int NUM_ITEMS_PAGE = 100;
    private int noOfBtns;
    private Button[] btns;

    LinearLayout ll;


    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    private int TOTAL_PAGES = +1;

    private int currentPage = PAGE_START;

    LinearLayoutManager linearLayoutManager;


    MyClient client;

    int start_index=50, end_index=99;
    /*--------------------------------Pagination contents-----------------------------*/


    public void runtimePermissions() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

    }

   private void Btnfooter()
    {
        int val = TOTAL_LIST_ITEMS%NUM_ITEMS_PAGE;
        val = val==0?0:1;
        noOfBtns=TOTAL_LIST_ITEMS/NUM_ITEMS_PAGE+val;


        btns	=new Button[noOfBtns];

        for(int i = 0; i<noOfBtns; i++)
        {
            btns[i]	=	new Button(getActivity());
            btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btns[i].setText(""+(i+1));


            final int finalI = i;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    btns[finalI].setWidth(10);
                    btns[finalI].setHeight(5);
                    ll.addView(btns[finalI], lp);

                }
            });


            final int j = i;
            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v)
                {
                    //loadList(j);
                    CheckBtnBackGroud(j);
                }
            });
        }

    }

    /**
     * Method for Checking Button Backgrounds
     */
    private void CheckBtnBackGroud(final int index) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                title.setText("Page " + (index + 1) + " of " + noOfBtns);

            }
        });
        for (int i = 0; i < noOfBtns; i++) {
            if (i == index) {
                btns[index].setBackgroundDrawable(getResources().getDrawable(R.drawable.export_round_btn));
                btns[i].setTextColor(getResources().getColor(android.R.color.white));
            } else {
                btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(android.R.color.black));
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.session_without_date, container, false);
        sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        userId = sharedpreferences.getString(UserId, "");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        progressBarSession = (ProgressBar) view.findViewById(R.id.progressBarSession);

        sessionArrays = new ArrayList<>();

        newList = new ArrayList<>();

        dataAdapter = new DataAdapter(newList,getActivity());

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dataAdapter);


        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });



        loadFirstPage();


        btnExport = (Button) view.findViewById(R.id.btnExport);


     /*   mdatePickerFrom = (LinearLayout )view.findViewById(R.id.datePickerFrom);
        mdatePickerTo = (LinearLayout )view.findViewById(R.id.datePickerTo);

        medtDateFrom = (TextView) view.findViewById(R.id.edtDateFrom);
        medtDateTo = (TextView) view.findViewById(R.id.edtDateTo);*/


      /*  mdatePickerFrom.setOnClickListener(this);
        mdatePickerTo.setOnClickListener(this);
        medtDateFrom.setOnClickListener(this);
        medtDateTo.setOnClickListener(this);*/

        runtimePermissions();

        onSendArrayListDUActivityListener.sendArrayListToActivity((ArrayList<DataModelSession>) dataList);


        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onSendArrayListDUActivityListener = (OnSendArrayListDUActivityListener) context;

        } catch (Exception e) {
        }
    }


    public interface OnSendArrayListDUActivityListener {

        void sendArrayListToActivity(ArrayList<DataModelSession> dataList);
    }

    private void loadFirstPage() {
        // Got data. Send it to adapter
        client = new MyClient("https://103.48.187.2:8000/api/v1/get_usersession_details/" + userId, getActivity());

        try {client.Execute(0);}
        catch (Exception e) {e.printStackTrace();}

        client.myListener = new MyListener() {
            @Override
            public void onResult(String response) {

                try {

                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray jsonArray = jsonObj.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        SessionArray sessionArray = new SessionArray();

                        sessionArray.setStart_time(jsonArray.getJSONObject(i).getString("stop_time"));
                        sessionArray.setDownloadBytes(jsonArray.getJSONObject(i).getString("download_bytes"));
                        sessionArray.setUploadBytes(jsonArray.getJSONObject(i).getString("upload_bytes"));

                        sessionArrays.add(sessionArray);

                    }

                    TOTAL_PAGES = sessionArrays.size()/20;


                    //for (int j = 0; j <= sessionArrays.size(); j++) {

                        newList = sessionArrays.subList(0, 49);

                    //}

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            progressBarSession.setVisibility(View.GONE);
                            dataAdapter.addAll(newList);
                            if (currentPage <= TOTAL_PAGES)
                                dataAdapter.addLoadingFooter();
                            else isLastPage = true;
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };
    }


    private void loadNextPage() {


        client = new MyClient("https://103.48.187.2:8000/api/v1/get_usersession_details/" + userId, getActivity());


        try {
            client.Execute(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        client.myListener = new MyListener() {
            @Override
            public void onResult(String response) {

                try {

                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray jsonArray = jsonObj.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        SessionArray sessionArray = new SessionArray();

                        sessionArray.setStart_time(jsonArray.getJSONObject(i).getString("stop_time"));
                        sessionArray.setDownloadBytes(jsonArray.getJSONObject(i).getString("download_bytes"));
                        sessionArray.setUploadBytes(jsonArray.getJSONObject(i).getString("upload_bytes"));


                        sessionArrays.add(sessionArray);

                    }



                   // for (int j = 0; j <= sessionArrays.size(); j++) {

                    newList = sessionArrays.subList(start_index, end_index);


                    start_index = start_index+50;
                    end_index = start_index+50;


                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dataAdapter.removeLoadingFooter();
                            isLoading = false;

                            dataAdapter.addAll(newList);

                            if (currentPage != TOTAL_PAGES) {
                                dataAdapter.addLoadingFooter();
                            }
                            else {
                                isLastPage = true;
                            }
                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };


    }
}

   /* private void loadList(int number)
    {
        final ArrayList<SessionArray> sort = new ArrayList<SessionArray>();

        int start = number * NUM_ITEMS_PAGE;
        for(int i=start;i<(start)+NUM_ITEMS_PAGE;i++)
        {
            if(i<sessionArrays.size())
            {
                sort.add(sessionArrays.get(i));
            }
            else
            {
                break;
            }

        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                dataAdapter = new DataAdapter(sort,getActivity());

                recyclerView.setAdapter(dataAdapter);


                progressBarSession.setVisibility(View.INVISIBLE);





            }
        });

    }*/








   /* class mDateSetFromListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // getCalender();
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            medtDateFrom.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("/").append(mDay).append("/")
                    .append(mYear).append(" "));
            //System.out.println(v.getText().toString());

            fromDate = String.valueOf(medtDateFrom.getText());

        }
    }*/


   /* class mDateSetToListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // getCalender();
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            medtDateTo.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("/").append(mDay).append("/")
                    .append(mYear).append(" "));
            //System.out.println(v.getText().toString());

            toDate = String.valueOf(medtDateTo.getText());

        }
    }*/

   /* @Override
    public void onClick(View view) {

        if(view == mdatePickerFrom)
        {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            System.out.println("the selected " + mDay);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                    new mDateSetFromListener(), mYear, mMonth, mDay);
            dialog.show();
        }
        if(view == mdatePickerTo){
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            System.out.println("the selected " + mDay);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                    new mDateSetToListener(), mYear, mMonth, mDay);
            dialog.show();
        }

        if(view==medtDateFrom){

            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            System.out.println("the selected " + mDay);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                    new mDateSetFromListener(), mYear, mMonth, mDay);
            dialog.show();

        }
        if (view==medtDateTo){

            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            System.out.println("the selected " + mDay);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                    new mDateSetToListener(), mYear, mMonth, mDay);
            dialog.show();
        }

    }*/





