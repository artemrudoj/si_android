package com.wintersportcoaches.common.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.artem.common.R;
import com.google.gson.annotations.SerializedName;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;

import java.util.ArrayList;

/**
 * Created by artem on 22.05.16.
 */
public class BaseUser {

    public void setPhotoLocalUrl(String photoLocalUrl) {
        this.photoLocalUrl = photoLocalUrl;
    }

    public String getPhotoLocalUrl() {
        return photoLocalUrl;
    }


    public static final String LEARNER = "l";
    public static final String COACH = "c";

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
    public static final String PHOTO_LOCAL_ARG = "PHOTO_LOCAL_ARG";
    private transient String photoLocalUrl;

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


    public void setPhotoUrl(String photoUrl) {
//        if (photoUrl == null || photoUrl.contains("default")) {
//            this.photoUrl = "";
//        } else {
//            this.photoUrl = NetworkServiceFactory.WSC_SERVER_URL + photoUrl;
//        }
        this.photoUrl = photoUrl;
    }
    public void setPhotoUrlWithServerUrl(String photoUrl) {
        String newUrl = NetworkServiceFactory.WSC_SERVER_URL.substring(0,NetworkServiceFactory.WSC_SERVER_URL.length()-1) + photoUrl;
        this.photoUrl = newUrl;
    }

    public String getPhotoUrlWithServerUrl() {
        return NetworkServiceFactory.WSC_SERVER_URL.substring(0,NetworkServiceFactory.WSC_SERVER_URL.length()-1) + photoUrl;
    }

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


    public void setLogin(boolean login) {
        isLogin = login;
    }
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(FIRST_NAME_ARG, firstName);
        bundle.putString(LAST_NAME_ARG, lastName);
        bundle.putString(TELEPHONE_ARG, phone);
        bundle.putString(PHOTO_BACKEND_ARG, photoUrl);
        bundle.putString(PHOTO_LOCAL_ARG, photoLocalUrl);
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
        photoLocalUrl = bundle.getString(PHOTO_LOCAL_ARG);
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
        photoLocalUrl = "";
        isLogin = false;
        hash = "";
        userId =  0;
        rating = 0;
        email = "";
    }

    public void init(BaseUser user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        phone = user.getPhone();
        setPhotoUrl(user.getPhotoUrl());
        userId =  user.getUserId();
        rating = user.getRating();
        email = user.getEmail();
    }


    public String getFullName() {
        if(firstName.equals("") && lastName.equals(""))
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
