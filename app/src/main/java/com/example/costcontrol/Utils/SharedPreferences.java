package com.example.costcontrol.Utils;

import android.app.Activity;
import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferences {
    private final String keyName = "q3984n6yw48975yb";
    private final String keyPassword = "09247h56n98254q6";

    public void SPWrite(String name, String password, Activity activity){
        android.content.SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(keyName, name);
        editor.putString(keyPassword, password);
        editor.apply();
    }

    public List<String> SPRead(Activity activity){
        List<String> values = new ArrayList<>();
        String name, password;

        android.content.SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);

        name = sharedPref.getString(keyName, null);
        password = sharedPref.getString(keyPassword, null);
        values.add(name);
        values.add(password);

        return values;
    }
}
