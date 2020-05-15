package com.mayaandroid.weather;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DemoSPUtils {

    public static ArrayList<AppCompatActivity> list = new ArrayList<>();

    private static final String MOBADS_PERMISSIONS = "center_permissions";

    public static void setBoolean(Context context, String key, boolean value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(MOBADS_PERMISSIONS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(MOBADS_PERMISSIONS, Context.MODE_PRIVATE);
            return preferences.getBoolean(key, defValue);
        }
        return defValue;
    }

}
