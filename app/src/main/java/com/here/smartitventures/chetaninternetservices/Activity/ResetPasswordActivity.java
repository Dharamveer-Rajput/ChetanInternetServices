package com.here.smartitventures.chetaninternetservices.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.here.smartitventures.chetaninternetservices.ApiClient.MyClient;
import com.here.smartitventures.chetaninternetservices.MyListener;
import com.here.smartitventures.chetaninternetservices.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {


    private static EditText mUserId, medUserPsw;
    private static TextView mSubmitbtn, mBackBtn;


    public static String userId;

    String message;

    public ResetPasswordActivity() {
    }


    @Override
    protected void onResume() {
        super.onResume();

        mUserId.setText(userId);
    }

    @Override
    protected void onPause() {
        super.onPause();

        userId = mUserId.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initViews();
        setListeners();
    }


    @Override
    public void onBackPressed() {
        // save data first
        super.onBackPressed();
        finish();
    }

    // Initialize the views
    private void initViews() {
        mUserId = (EditText) findViewById(R.id.edUserId);
        //medUserPsw = (EditText) findViewById(R.id.edUserPsw);

        mBackBtn = (TextView) findViewById(R.id.textViewBackBtn);
        mSubmitbtn = (TextView) findViewById(R.id.textViewSubmitBtn);

    }

    // Set Listeners over buttons
    private void setListeners() {
        mBackBtn.setOnClickListener(ResetPasswordActivity.this);
        mSubmitbtn.setOnClickListener(ResetPasswordActivity.this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBackBtn) {

            Intent MainActivityIntent = new Intent(ResetPasswordActivity.this, MainActivity.class);
            startActivity(MainActivityIntent);
        }
        // Call Submit button task
        if (view == mSubmitbtn) {
            submitButtonTask();
        }
    }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private void submitButtonTask() {

        final String getUserId = mUserId.getText().toString();
        //final String getUserPsw = medUserPsw.getText().toString();


        MyClient client = new MyClient("https://103.48.187.2:8000/api/v1/reset_password",getApplicationContext());

        client.myListener  = new MyListener() {
            @Override
            public void onResult(String response) {

                try {
                    JSONObject jsonObj = new JSONObject(response);

                    for(int i = 0;i<jsonObj.length();i++) {

                        message = jsonObj.getString("message");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

        if(getUserId.trim().length()==0) {
            mUserId.setError("User Id is not entered");
            mUserId.requestFocus();
        }

//        if(getUserPsw.length()<8 &&!isValidPassword(getUserPsw)) {
//            medUserPsw.setError("Not Valid");
//            medUserPsw.requestFocus();
//        }

        client.AddParam("userId",getUserId);


        try {
            client.Execute(1);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}



