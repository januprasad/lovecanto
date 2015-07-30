package com.jenuine.lovetips;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jenu on 30/7/15.
 */
public class AppDataPref {
    public static final String TEMP_PHOTO_FILE_NAME = "fbprofileimg.jpg";
    private String DIRECTORY_NAME = "SocialNetGate";
    public static boolean CALL_USER_DETAILS = false;
    public static boolean CALL_INFLUENCE_CALC = true;
    private Context context;
    private SharedPreferences prefs;
    public SharedPreferences prefs1;

    public AppDataPref(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences("lovecanto-pref",
                Context.MODE_PRIVATE);
    }
    public String getPrefs(String name) {
        return prefs.getString(name, "");
    }

    public void setPrefs(String key, String value) {
        prefs.edit().putString(key, value).commit();
    }

}