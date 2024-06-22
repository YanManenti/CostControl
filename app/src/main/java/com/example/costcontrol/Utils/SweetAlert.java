package com.example.costcontrol.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

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

    public static void showErrorDialog(Activity activity, String errorText){
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE);
        pDialog.setContentText(errorText);
        pDialog.setConfirmText((String) activity.getResources().getText(R.string.close));
        pDialog.show();
    }

    public static void closeAnyDialog(SweetAlertDialog dialog){
        dialog.dismissWithAnimation();
    }
}
