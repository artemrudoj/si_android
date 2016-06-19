package com.wintersportcoaches.common.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.artem.common.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by artem on 22.05.16.
 */
public class BaseUser {

    public static final String DEFAULT_FIRST_NAME = "Регистрация";
    public static final String DEFAULT_LAST_NAME = "";
    public static final String DEFAULT_TELEPOHNE = "";

    public static final String FIRST_NAME_ARG = "firstName";
    public static final String LAST_NAME_ARG = "lastName";
    public static final String TELEPHONE_ARG = "telephone";
    public static final String PHOTO_BACKEND_ARG = "PHOTO_BACKEND_ARG";
    public static final String LOGIN_ARG = "login";
    public static final String HASH_ARG = "hash";
    public static final String ID_ARGS = "id";
    public static final String RATING_ARG = "rating";
    public static final String EMAIL_ARG = "EMAIL_ARG";
    public static final String EXTRA_USER = "BaseUser.EXTRA_USER";


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

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public float getRating() {
        return rating;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public BaseUser() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(FIRST_NAME_ARG, firstName);
        bundle.putString(LAST_NAME_ARG, lastName);
        bundle.putString(TELEPHONE_ARG, phone);
        bundle.putString(PHOTO_BACKEND_ARG, photoUrl);
        bundle.putInt(ID_ARGS, userId);
        bundle.putFloat(RATING_ARG, rating);
        bundle.putString(EMAIL_ARG, email);
        return bundle;
    }
    public BaseUser(Bundle bundle) {
        firstName = bundle.getString(FIRST_NAME_ARG);
        lastName = bundle.getString(LAST_NAME_ARG);
        phone = bundle.getString(TELEPHONE_ARG);
        photoUrl = bundle.getString(PHOTO_BACKEND_ARG);
        hash = bundle.getString(HASH_ARG);
        userId =  bundle.getInt(ID_ARGS);
        rating = bundle.getFloat(RATING_ARG);
        email = bundle.getString(EMAIL_ARG);
    }
    public void setDefaultProperties() {
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



    public String getFullName() {
        if(firstName.equals("") || lastName.equals(""))
            throw new IllegalStateException("names cannot be empty");
        return firstName + " " + lastName;
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
