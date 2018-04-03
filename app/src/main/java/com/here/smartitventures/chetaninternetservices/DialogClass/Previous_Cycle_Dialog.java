package com.here.smartitventures.chetaninternetservices.DialogClass;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.here.smartitventures.chetaninternetservices.R;



public class Previous_Cycle_Dialog extends Dialog implements View.OnClickListener {

    Button mbtnOk,mbtnCancel;

    public Previous_Cycle_Dialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.previous_cycle_dialog_layout);


        mbtnOk = (Button) findViewById(R.id.btnOk);
        mbtnCancel = (Button) findViewById(R.id.btnCancel);

        mbtnOk.setOnClickListener(this);
        mbtnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view==mbtnOk){
            dismiss();
        }
        if(view==mbtnCancel){
            dismiss();
        }

    }
}
