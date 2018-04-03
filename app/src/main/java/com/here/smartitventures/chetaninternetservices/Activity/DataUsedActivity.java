package com.here.smartitventures.chetaninternetservices.Activity;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.here.smartitventures.chetaninternetservices.ApiClient.MyClient;
import com.here.smartitventures.chetaninternetservices.Fragments.BillFragment;
import com.here.smartitventures.chetaninternetservices.Fragments.Current_cycle;
import com.here.smartitventures.chetaninternetservices.Fragments.General_Information;
import com.here.smartitventures.chetaninternetservices.Fragments.PaymentFragment;
import com.here.smartitventures.chetaninternetservices.Fragments.ProfileFragment;
import com.here.smartitventures.chetaninternetservices.Fragments.SessionFragment;
import com.here.smartitventures.chetaninternetservices.Model.DataModelSession;
import com.here.smartitventures.chetaninternetservices.MyListener;
import com.here.smartitventures.chetaninternetservices.NavigationDrawerCode.CustomDrawerAdapter;
import com.here.smartitventures.chetaninternetservices.NavigationDrawerCode.DrawerItem;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetBalance.GetBalance;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails.CurrentBillingCycleUsage;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails.User;
import com.here.smartitventures.chetaninternetservices.UserSession.UserSessionManager;
import com.here.smartitventures.chetaninternetservices.Utils.Util;
import com.paginate.Paginate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.here.smartitventures.chetaninternetservices.Fragments.SessionFragment.sessionArrays;


public class DataUsedActivity extends AppCompatActivity implements View.OnClickListener,SessionFragment.OnSendArrayListDUActivityListener{

    Button btnExportData;

    private Handler handler;
    private boolean loading = false;
    protected long networkDelay = 2000;

    Toolbar toolbar;

    String uploadDataUsage;
    String downloadDataUsage;
    long totalDataUsage;
    String bandwidthTemplateName;

    private DrawerLayout drawerLayout;
    private DrawerLayout mDrawerLayout;
    CustomDrawerAdapter adapter;

    private ListView listView;
    List<DrawerItem> dataList;
    private ArrayList<DataModelSession> listSessionData;

    public ImageView head;
    private ImageView imageViewRound1;

    TextView textViewUserEmail,toolbar_title,textViewUserName;

    String username, email;

    Bundle bundle;
    double balance;

    User user =  User.getInstance();

    private File dataFile;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String UserId = "userId";
    String userId;

    @Override
    protected void onResume() {
        super.onResume();

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationlist);
        Util.setLogin(true,getApplicationContext());

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        userId = sharedpreferences.getString(UserId,"");

        toolbar= (Toolbar) findViewById(R.id.toolbar);

        btnExportData = (Button) findViewById(R.id.btnExport);

        btnExportData.setOnClickListener(this);
        
        setSupportActionBar(toolbar);


        if(ContextCompat.checkSelfPermission(DataUsedActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(DataUsedActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {


            } else {
                runtimePermissions();

            }
        }
        profileFragmentApi();

        //Text view declarations
        textViewUserName = (TextView) findViewById(R.id.textViewUserName);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);

        //imageViewRound1 = (ImageView) findViewById(R.id.imageView_round1);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.profilephoto);

        head = (ImageView) findViewById(R.id.drawerOpen);

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                    head.setVisibility(View.VISIBLE);

                }
            }
        });


        // Initializing
        dataList = new ArrayList<DrawerItem>();

        listView = (ListView) findViewById(R.id.listview1);

        dataList.add(new DrawerItem("My Profile", R.drawable.user));
        dataList.add(new DrawerItem("Session Detail", R.drawable.session));
        dataList.add(new DrawerItem("Bill", R.drawable.bil));
        dataList.add(new DrawerItem("Payments", R.drawable.payment));
        dataList.add(new DrawerItem("Logout", R.drawable.logout));

        adapter = new CustomDrawerAdapter(this, R.layout.navigation_drawer_linear, dataList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();

                if (position == 0) {

                    sharedpreferences = getSharedPreferences(mypreference,
                            Context.MODE_PRIVATE);

                    userId = sharedpreferences.getString(UserId,"");


                    bundle = new Bundle();
                    bundle.putLong("total", totalDataUsage);
                    bundle.putDouble("bal",balance);
                    bundle.putString("userId",userId);



                    toolbar_title.setText("View Profile");
                    Fragment fragment = new ProfileFragment();
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.mainFragmentFrame, fragment,"profile").commit();

                    btnExportData.setVisibility(View.INVISIBLE);
                }

                if (position == 1) {

                    toolbar_title.setText("Session Details");

                    btnExportData.setVisibility(View.VISIBLE);

                    Fragment fragment = new SessionFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.mainFragmentFrame, fragment,"session").commit();
                }

                if (position == 2) {

                    toolbar_title.setText("Bills");

                    btnExportData.setVisibility(View.INVISIBLE);


                    Fragment fragment = new BillFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.mainFragmentFrame, fragment,"bills").commit();
                }

                if (position == 3)

                {
                    toolbar_title.setText("Payment");

                    btnExportData.setVisibility(View.INVISIBLE);

                    Fragment fragment = new PaymentFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.mainFragmentFrame, fragment,"payments").commit();
                }
                if (position == 4)
                {
                    btnExportData.setVisibility(View.INVISIBLE);

                    //session.logoutUser();
                    Util.setLogin(false,getApplicationContext());
                    Intent i = new Intent(DataUsedActivity.this, MainActivity.class);
                    startActivity(i);
                }

            }
        });


        MyClient client = new MyClient("https://103.48.187.2:8000/api/v1/get_details/"+userId,getApplicationContext());

        client.myListener  = new MyListener() {
            @Override
            public void onResult(String response) {

                try {

                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray jsonArray = jsonObj.getJSONArray("data");

                    for(int i = 0;i<jsonArray.length();i++){

                        /* -------------------------------User Object----------------------------------------------- */

                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        JSONObject jsonObjectUser = jsonObject.getJSONObject("User");

                        username = jsonObjectUser.getString("username");
                        String name = jsonObjectUser.getString("name");
                        String mobile= jsonObjectUser.getString("phone");
                        email = jsonObjectUser.getString("email");
                        String address_line1 = jsonObjectUser.getString("address_line1");
                        String activationTime = jsonObjectUser.getString("activationTime");
                        String expirationTime = jsonObjectUser.getString("expirationTime");

                        user.setUsername(username);
                        user.setName(name);
                        user.setPhone(mobile);
                        user.setEmail(email);
                        user.setAddressLine1(address_line1);
                        user.setActivationTime(activationTime);
                        user.setExpirationTime(expirationTime);



                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textViewUserName.setText(username);
                                textViewUserEmail.setText(email);

                            }
                        });

/*
                         -----------------------------Current Billing Cycle Object------------------------------------
*/
                        JSONObject jsonObject2 = jsonArray.getJSONObject(1);
                        JSONObject jsonObjectBillingCycle = jsonObject2.getJSONObject("currentBillingCycleUsage");

                        uploadDataUsage = jsonObjectBillingCycle.getString("uploadDataUsage");
                        downloadDataUsage = jsonObjectBillingCycle.getString("downloadDataUsage");
                        totalDataUsage = jsonObjectBillingCycle.getLong("totalDataUsage");
                        bandwidthTemplateName = jsonObjectBillingCycle.getString("bandwidthTemplateName");



                        CurrentBillingCycleUsage currentBillingCycleUsage = CurrentBillingCycleUsage.getInstance();


                        currentBillingCycleUsage.setBandwidthTemplateName(bandwidthTemplateName);

                        /*------------------upload data------------------------------------- */
/*
                        long uploadData = Long.parseLong(uploadDataUsage);



                        if(uploadData>=1024 && uploadData<1048576){

                            double lengthdownloadbytes1 =  uploadData/1024;

                            String formatmb = String.format("%.02f", lengthdownloadbytes1);

                            currentBillingCycleUsage.setDownloadDataUsage(formatmb+" KB");


                        }

                        else if(uploadData>=1048576 && uploadData<1073741824){

                            double lengthdownloadbytes1 =  uploadData/1048576;

                            String formatmb = String.format("%.02f", lengthdownloadbytes1);

                            currentBillingCycleUsage.setDownloadDataUsage(formatmb+" MB");

                        }

                        else if(uploadData>=1073741824){

                            double lengthdownloadbytes1 =  uploadData/1073741824;

                            String formatmb = String.format("%.02f", lengthdownloadbytes1);

                            currentBillingCycleUsage.setDownloadDataUsage(formatmb+" GB");

                        }*/


                        /*------------------download data------------------------------------- */

                    /*    long downloadData = Long.parseLong(downloadDataUsage);


                        if(downloadData>=1024 && downloadData<1048576){

                           double lengthdownloadbytes =  downloadData/1024;

                            String formatmb = String.format("%.02f", lengthdownloadbytes);

                            currentBillingCycleUsage.setDownloadDataUsage(formatmb+" KB");


                        }

                        else if(downloadData>=1048576 && downloadData<1073741824){

                            double lengthdownloadbytes =  downloadData/1048576;

                            String formatmb = String.format("%.02f", lengthdownloadbytes);

                            currentBillingCycleUsage.setDownloadDataUsage(formatmb+" MB");

                        }

                        else if(downloadData>=1073741824){

                            double lengthdownloadbytes =  downloadData/1073741824;

                            String formatmb = String.format("%.02f", lengthdownloadbytes);

                            currentBillingCycleUsage.setDownloadDataUsage(formatmb+" GB");

                        }*/

/*-----------------------------------------------------------------------------------------------------------------*/
                      /*  long TotalData = totalDataUsage;

                        if(TotalData>=1024 && TotalData<1048576){

                            double lengthTotalbytes1 = TotalData/1024;

                            String formatgb = String.format("%.02f", lengthTotalbytes1);

                            currentBillingCycleUsage.setTotalDataUsage(Double.valueOf(formatgb+" KB"));
                        }

                       else if(TotalData>=1048576 && TotalData<1073741824){

                            double lengthTotalbytes1 = TotalData/1048576;

                            String formatgb = String.format("%.02f", lengthTotalbytes1);

                            currentBillingCycleUsage.setTotalDataUsage(Double.valueOf(formatgb+" MB"));
                        }

                        else if(TotalData>=1073741824){

                            double lengthTotalbytes1 = TotalData/1073741824;

                            String formatgb = String.format("%.02f", lengthTotalbytes1);

                            currentBillingCycleUsage.setTotalDataUsage(Double.valueOf(formatgb+" GB"));
                        }*/


                        bundle = new Bundle();
                        bundle.putLong("totalDataUsage", totalDataUsage);


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

        if (savedInstanceState == null) {

            bundle = new Bundle();
            bundle.putLong("total", totalDataUsage);
            bundle.putDouble("bal",balance);

            Fragment fragment = new ProfileFragment();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mainFragmentFrame, fragment).commit();
        }
    }

    public void profileFragmentApi(){

        MyClient client = new MyClient("https://103.48.187.2:8000//api/v1/get_balance/"+userId,getApplicationContext());
        try {
            client.Execute(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        client.myListener  = new MyListener() {
            @Override
            public void onResult(String response) {

                Gson gson = new Gson();

                GetBalance gsonObj = gson.fromJson(response, GetBalance.class);

                balance = gsonObj.getBalance();
            }

        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.general_information) {

            Fragment general_information = new General_Information();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mainFragmentFrame, general_information).commit();
        }

        else if(id==R.id.current_cycle) {

            Fragment current_cycle = new Current_cycle();
            FragmentManager fragmentManager1 = getSupportFragmentManager();
            fragmentManager1.beginTransaction().replace(R.id.mainFragmentFrame, current_cycle).commit();
        }

      /*  else if(id==R.id.cocurrent_sessions) {

            Fragment concurrent_sessions = new Concurrent_Sessions();
            FragmentManager fragmentManager2 = getSupportFragmentManager();
            fragmentManager2.beginTransaction().replace(R.id.mainFragmentFrame, concurrent_sessions).commit();

        }*/

       /* else if(id==R.id.billing_information) {
            Fragment fragment = new Billing_Information();
            FragmentManager fragmentManager3 = getSupportFragmentManager();
            fragmentManager3.beginTransaction().replace(R.id.mainFragmentFrame, fragment).commit();
        }
        else if(id==R.id.additional_information) {

            Fragment additional_information = new Additional_Information();
            FragmentManager fragmentManager4 = getSupportFragmentManager();
            fragmentManager4.beginTransaction().replace(R.id.mainFragmentFrame, additional_information).commit();
        }*/
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {

        if(view==btnExportData){

            PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.btnExport));
            popupMenu.inflate(R.menu.export_type);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item)
                {
                    int id = item.getItemId();

                    switch (id)
                    {
                        case R.id.saveCSV:

                            saveCSV(getApplicationContext());

                            addNotification();


                            break;
                        case R.id.saveTXT:


                            saveTXT(getApplicationContext());

                            addNotification();

                            break;

                        case R.id.saveXML:

                            saveXML(getApplicationContext());
                            addNotification();

                            break;

                        case R.id.saveSQL:

                            saveSQL(getApplicationContext());
                            addNotification();

                            break;

                        case R.id.saveJSON:

                            saveJSON(getApplicationContext());
                            addNotification();

                            break;

                        case R.id.saveEXCEL:

                            saveEXCEL(getApplicationContext());
                            addNotification();

                            break;


                        case R.id.saveWORD:

                            saveWORD(getApplicationContext());
                            addNotification();

                            break;


                        case R.id.savePOWERPOINT:

                            savePOWERPOINT(getApplicationContext());
                            addNotification();

                            break;


                    }

                    return true;
                }
            });
            popupMenu.show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)


    public void runtimePermissions(){

        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);


    }
    @Override
    public void sendArrayListToActivity(ArrayList<DataModelSession> dataList) {
        listSessionData = dataList;
    }


    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.download)
                        .setContentTitle("File Downloaded")
                        .setContentText("Saved in internal Storage");

        Uri selectedUri = Uri.parse(String.valueOf(Environment.getExternalStorageDirectory()));

        Intent notificationIntent = new Intent(Intent.ACTION_GET_CONTENT);
        notificationIntent.setAction(Intent.ACTION_VIEW);
        notificationIntent.setDataAndType(selectedUri, "file");


        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    public void saveCSV(final Context context) {

        File sdCard = Environment.getExternalStorageDirectory();

        dataFile = new File(sdCard.getAbsolutePath() + "/chetan.csv");
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(dataFile);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {

                ObjectOutputStream oos = null;
                try {

                    oos = new ObjectOutputStream(fos);

                    if (oos != null) {


                        for(int i=0;i<sessionArrays.size();i++)
                        {
                            oos.writeObject(sessionArrays.get(i).getStart_time()+""+sessionArrays.get(i).getUploadBytes()+""+sessionArrays.get(i).getDownloadBytes() + "\n\r");

                        }
                    }
                    assert oos != null;
                    oos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveTXT(final Context context) {

        File sdCard = Environment.getExternalStorageDirectory();

        dataFile = new File(sdCard.getAbsolutePath() + "/chetan.txt");
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(dataFile);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {

                ObjectOutputStream oos = null;
                try {

                    oos = new ObjectOutputStream(fos);

                    if (oos != null) {


                        for(int i=0;i<sessionArrays.size();i++)
                        {
                            oos.writeObject(sessionArrays.get(i).getStart_time()+""+sessionArrays.get(i).getUploadBytes()+""+sessionArrays.get(i).getDownloadBytes() + "\n\r");

                        }
                    }
                    assert oos != null;
                    oos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void saveXML(final Context context) {

        File sdCard = Environment.getExternalStorageDirectory();

        dataFile = new File(sdCard.getAbsolutePath() + "/chetan.xml");
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(dataFile);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {

                ObjectOutputStream oos = null;
                try {

                    oos = new ObjectOutputStream(fos);

                    if (oos != null) {


                        for(int i=0;i<sessionArrays.size();i++)
                        {
                            oos.writeObject(sessionArrays.get(i).getStart_time()+""+sessionArrays.get(i).getUploadBytes()+""+sessionArrays.get(i).getDownloadBytes() + "\n\r");

                        }
                    }
                    assert oos != null;
                    oos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void saveSQL(final Context context) {

        File sdCard = Environment.getExternalStorageDirectory();

        dataFile = new File(sdCard.getAbsolutePath() + "/chetan.sql");
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(dataFile);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {

                ObjectOutputStream oos = null;
                try {

                    oos = new ObjectOutputStream(fos);

                    if (oos != null) {


                        for(int i=0;i<sessionArrays.size();i++)
                        {
                            oos.writeObject(sessionArrays.get(i).getStart_time()+""+sessionArrays.get(i).getUploadBytes()+""+sessionArrays.get(i).getDownloadBytes() + "\n\r");

                        }
                    }
                    assert oos != null;
                    oos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void saveJSON(final Context context) {

        File sdCard = Environment.getExternalStorageDirectory();

        dataFile = new File(sdCard.getAbsolutePath() + "/chetan.json");
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(dataFile);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {

                ObjectOutputStream oos = null;
                try {

                    oos = new ObjectOutputStream(fos);

                    if (oos != null) {


                        for(int i=0;i<sessionArrays.size();i++)
                        {
                            oos.writeObject(sessionArrays.get(i).getStart_time()+""+sessionArrays.get(i).getUploadBytes()+""+sessionArrays.get(i).getDownloadBytes() + "\n\r");

                        }
                    }
                    assert oos != null;
                    oos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void saveEXCEL(final Context context) {

        File sdCard = Environment.getExternalStorageDirectory();

        dataFile = new File(sdCard.getAbsolutePath() + "/chetan.excel");
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(dataFile);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {

                ObjectOutputStream oos = null;
                try {

                    oos = new ObjectOutputStream(fos);

                    if (oos != null) {


                        for(int i=0;i<sessionArrays.size();i++)
                        {
                            oos.writeObject(sessionArrays.get(i).getStart_time()+""+sessionArrays.get(i).getUploadBytes()+""+sessionArrays.get(i).getDownloadBytes() + "\n\r");

                        }
                    }
                    assert oos != null;
                    oos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void saveWORD(final Context context) {

        File sdCard = Environment.getExternalStorageDirectory();

        dataFile = new File(sdCard.getAbsolutePath() + "/chetan.doc");
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(dataFile);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {

                ObjectOutputStream oos = null;
                try {

                    oos = new ObjectOutputStream(fos);

                    if (oos != null) {


                        for(int i=0;i<sessionArrays.size();i++)
                        {
                            oos.writeObject(sessionArrays.get(i).getStart_time()+""+sessionArrays.get(i).getUploadBytes()+""+sessionArrays.get(i).getDownloadBytes() + "\n\r");

                        }
                    }
                    assert oos != null;
                    oos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void savePOWERPOINT(final Context context) {

        File sdCard = Environment.getExternalStorageDirectory();

        dataFile = new File(sdCard.getAbsolutePath() + "/chetan.ppt");

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(dataFile);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {

                ObjectOutputStream oos = null;
                try {

                    oos = new ObjectOutputStream(fos);

                    if (oos != null) {

                        for(int i=0;i<sessionArrays.size();i++)
                        {
                            oos.writeObject(sessionArrays.get(i).getStart_time()+""+sessionArrays.get(i).getUploadBytes()+""+sessionArrays.get(i).getDownloadBytes() + "\n\r");

                        }
                    }
                    assert oos != null;
                    oos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
       this.finishAffinity();
    }


}