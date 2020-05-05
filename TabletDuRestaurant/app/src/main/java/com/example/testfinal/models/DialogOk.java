package com.example.testfinal.models;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testfinal.R;

public class DialogOk {

    private AlertDialog dialog;
    private View dialogView;
    private LayoutInflater inf;
    private TextView value,message;
    private Button ok;
    private ImageView image;


    public DialogOk(Activity activity,Context ctx,int i){

        dialog=new AlertDialog.Builder(ctx).create();
        inf = activity.getLayoutInflater();
        dialog.setCanceledOnTouchOutside(false);
        dialogView = inf.inflate(R.layout.ok_dialog_layout, null);
        ok=dialogView.findViewById(R.id.confirmer_dialog);
        value=dialogView.findViewById(R.id.value);
        message=dialogView.findViewById(R.id.message);
        image=dialogView.findViewById(R.id.imageDialog);


        ViewGroup.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if(i==1){

            ok.setBackgroundResource(R.drawable.rounded_button_green);
            message.setTextColor(activity.getResources().getColor(R.color.green));
            image.setImageResource(R.drawable.ok);
        }
        if(i==2){

            ok.setBackgroundResource(R.drawable.rounded_button_red);
            message.setTextColor(activity.getResources().getColor(R.color.red_errors));
            image.setImageResource(R.drawable.error);
        }

    }

    public void execute(String info){

        value.setText(info);
        dialog.setView(dialogView);
        dialog.show();

    }

    public void closeListener(){

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

    }

    public void close(){


                dialog.dismiss();

    }
    public Button getOk() {
        return ok;
    }



}
