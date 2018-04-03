package com.here.smartitventures.chetaninternetservices.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.here.smartitventures.chetaninternetservices.ApiClient.MyClient;
import com.here.smartitventures.chetaninternetservices.MyListener;
import com.here.smartitventures.chetaninternetservices.R;
import com.here.smartitventures.chetaninternetservices.UserSession.UserSessionManager;
import com.here.smartitventures.chetaninternetservices.Utils.Util;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends Activity implements View.OnClickListener {

    String username,password;
    Button btnSignIn;
    TextView mtextViewForgetPsw;
    EditText medUserName, medPassword;
    String strings[];

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String UserId = "userId";


    public static String username1;
    public static String password1;

    SharedPreferences SP;

    String userId;


    String[] key = { "Chetan Internet Services Nurpur Bedi", "Chetan Internet Services Ropar",  };

    Spinner spin;


    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_layout);

        checkForFirst();

        initViews();

    }

    private void checkForFirst() {

        if(Util.getLogin(getApplicationContext()))
        {
            Intent intent = new Intent(getApplicationContext(),DataUsedActivity.class);
            startActivity(intent);

        }
    }

    public void initViews() {

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        userId = sharedpreferences.getString(UserId,"");

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        mtextViewForgetPsw = (TextView) findViewById(R.id.textViewForgetPsw);

        medUserName = (EditText) findViewById(R.id.edUserName);
        medPassword = (EditText) findViewById(R.id.edPassword);

        medUserName.setMaxLines(1);

        spin = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinnertext,key);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                SP.edit().putInt("last index", spin.getSelectedItemPosition()).commit();




                ((TextView) view).setTextColor(Color.WHITE);

                if(++check > 1){


                    switch (parent.getSelectedItemPosition()){

                        //Chetan Internet Services Nurpur Bedi ----0
                        case 0:{

                            int flag = 1;
                            Util.setFlag(flag, getApplicationContext());

                            break;
                        }

                        //Chetan Internet Services Ropar------1
                        case 1:
                        {
                            int flag = 0;

                            Util.setFlag(flag, getApplicationContext());
                            break;
                        }

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {



            }
        });


        btnSignIn.setOnClickListener(this);
        mtextViewForgetPsw.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==btnSignIn) {

            username =  medUserName.getText().toString();
            password = medPassword.getText().toString();


            MyClient client = new MyClient("https://103.48.187.2:8000/api/v1/authenticate_user",getApplicationContext());

            client.myListener  = new MyListener() {
                @Override
                public void onResult(String response) {

                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(response);


                        //Save the username and passwoord in shared preferences
                        String userId = jsonArray.getString(1);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(UserId, userId);
                        editor.commit();


                        strings = new String[jsonArray.length()];
                        for(int i=0;i<strings.length;i++)
                            strings[i] = jsonArray.getString(i);


                        if(strings[1].equals("null")){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                    builder1.setMessage("Username does not exist in the account");
                                    builder1.setCancelable(true);

                                    builder1.setPositiveButton(
                                            "Yes",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                                    builder1.setNegativeButton(
                                            "No",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();

                                }
                            });

                        }

                        else if(strings[0].equalsIgnoreCase("error")){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                    builder1.setMessage("Invalid username and password");
                                    builder1.setCancelable(true);

                                    builder1.setPositiveButton(
                                            "Yes",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                                    builder1.setNegativeButton(
                                            "No",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();

                                }
                            });

                        }

                        else {

                            Intent intent = new Intent(getApplicationContext(),DataUsedActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            // Add new Flag to start new Activity
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();



                        }/*end of else block*/

                    } /*end of outer try block*/

                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                } /*end of interface method onResult()*/
            };

            if(username.trim().length()==0)
            {
                medUserName.setError("Username is not entered");
                medUserName.requestFocus();
            }
            else if(password.trim().length()==0)
            {
                medPassword.setError("Password is not entered");
                medPassword.requestFocus();
            }

            client.AddParam("username",username);
            client.AddParam("password", password);

            try {
                client.Execute(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(v==mtextViewForgetPsw)
        {
            Intent intent = new Intent(getApplicationContext(),ResetPasswordActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        medUserName.setText(username1);
        medPassword.setText(password1);


        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(SP!=null){
            int pos = SP.getInt("last index", 0);

            spin.setSelection(pos);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        username1 =  medUserName.getText().toString();
        password1 = medPassword.getText().toString();





    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }



}
