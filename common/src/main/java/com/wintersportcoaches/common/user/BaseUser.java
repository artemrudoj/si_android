package com.wintersportcoaches.common.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.artem.common.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by artem on 22.05.16.
 */
public class BaseUser {

    public static final String DEFAULT_FIRST_NAME = "Регистрация";
    private static final String DEFAULT_LAST_NAME = "";
    private static final String DEFAULT_TELEPOHNE = "";

    private static final String FIRST_NAME_ARG = "firstName";
    private static final String LAST_NAME_ARG = "lastName";
    private static final String TELEPHONE_ARG = "telephone";
    private static final String PHOTO_BACKEND_ARG = "PHOTO_BACKEND_ARG";
    private static final String LOGIN_ARG = "login";
    public static final String HASH_ARG = "hash";
    private static final String ID_ARGS = "id";
    private static final String RATING_ARG = "rating";
    private static final String EMAIL_ARG = "EMAIL_ARG";

    protected SharedPreferences preferences;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @SerializedName("id") private int userId;

    @SerializedName("first_name") private String firstName;

    @SerializedName("last_name") private String lastName;

    @SerializedName("email") private String email;

    @SerializedName("phone") private String phone;

    @SerializedName("rating") private float rating;

    @SerializedName("image") private String photoUrl;


    private transient boolean isLogin;
    private String hash;

    public String getHash() {
        return hash;
    }

    public boolean isLogin() {
        return hash != null && !hash.equals("");
    }


    public BaseUser(Context context) {
        String appName = context.getString(R.string.app_name);
        preferences = context.getSharedPreferences(appName, Context.MODE_PRIVATE);
        firstName = preferences.getString(FIRST_NAME_ARG, DEFAULT_FIRST_NAME);
        lastName = preferences.getString(LAST_NAME_ARG, DEFAULT_LAST_NAME);
        phone = preferences.getString(TELEPHONE_ARG, DEFAULT_TELEPOHNE);
        photoUrl = preferences.getString(PHOTO_BACKEND_ARG, "");
        isLogin = preferences.getBoolean(LOGIN_ARG, false);
        hash = preferences.getString(HASH_ARG, "");
        userId = preferences.getInt(ID_ARGS, 0);
        rating = preferences.getFloat(RATING_ARG, 0);
        email = preferences.getString(EMAIL_ARG, "");
    }

    public void saveUser(Context context) {
        String appName = context.getString(R.string.app_name);
        preferences = context.getSharedPreferences(appName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LOGIN_ARG, true);
        editor.putString(FIRST_NAME_ARG, firstName);
        editor.putString(LAST_NAME_ARG, lastName);
        editor.putString(TELEPHONE_ARG, phone);
        editor.putString(PHOTO_BACKEND_ARG, photoUrl);
        editor.putString(HASH_ARG, hash);
        editor.putInt(ID_ARGS, userId);
        editor.putString(EMAIL_ARG, email);
        editor.putFloat(RATING_ARG, rating);
        editor.apply();
    }

    public void exit(Context context) {
        String appName = context.getString(R.string.app_name);
        preferences = context.getSharedPreferences(appName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        clearProperties();
    }

    public void clearProperties() {
        firstName = DEFAULT_FIRST_NAME;
        lastName = DEFAULT_LAST_NAME;
        phone = DEFAULT_TELEPOHNE;
        photoUrl = "";
        isLogin = false;
        hash = "";
        userId =  0;
        rating = 0;
        email = "";
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
