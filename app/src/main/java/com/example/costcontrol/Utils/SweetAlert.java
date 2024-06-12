package com.example.costcontrol.Utils;

import android.app.Activity;

import com.example.costcontrol.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SweetAlert {

    public static SweetAlertDialog showLoadingDialog(Activity activity){
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(activity.getResources().getColor(R.color.surfaceOrange, activity.getTheme()));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        return pDialog;
    }

    public static SweetAlertDialog showErrorDialog(Activity activity){
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitleText("Oops...");
        pDialog.setContentText("Something went wrong!");
        pDialog.show();
        return pDialog;
    }

    public static void closeAnyDialog(SweetAlertDialog dialog){
        dialog.dismissWithAnimation();
    }
}
