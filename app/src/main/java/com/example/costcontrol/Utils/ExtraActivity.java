package com.example.costcontrol.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.costcontrol.NewTrip;

import java.util.concurrent.Callable;

public class ExtraActivity {
    public static void start(Context context, Callable<Intent> callback){
        Intent intent = null;
        try {
            intent = callback.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        context.startActivity(intent);
    }

    public static Intent setUserId(Intent intent, long userId){
        intent.putExtra("userId", userId);
        return intent;
    }

    public static Intent setTripId(Intent intent, long tripId){
        intent.putExtra("tripId", tripId);
        return intent;
    }

    public static Intent setUserEmail(Intent intent, String userEmail){
        intent.putExtra("userEmail", userEmail);
        return intent;
    }

    public static long getUserId(Activity activity){
        try {
            Bundle extras = activity.getIntent().getExtras();
            if (extras != null) {
                return extras.getLong("userId");
            }
            return -1;
        }catch (Exception e){
            Toast.makeText(activity.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            return -1;
        }
    }

    public static Integer getTripId(Activity activity){
        try {
            Bundle extras = activity.getIntent().getExtras();
            if (extras != null) {
                return extras.getInt("tripId");
            }
            return null;
        }catch (Exception e){
            Toast.makeText(activity.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static String getUserEmail(Activity activity){
        try {
            Bundle extras = activity.getIntent().getExtras();
            if (extras != null) {
                return extras.getString("userEmail");
            }
            return null;
        }catch (Exception e){
            Toast.makeText(activity.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
