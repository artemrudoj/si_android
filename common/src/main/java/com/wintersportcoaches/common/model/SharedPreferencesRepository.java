package com.wintersportcoaches.common.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.artem.common.R;
import com.wintersportcoaches.common.user.BaseUser;

/**
 * Created by artem on 28.05.16.
 */
public class SharedPreferencesRepository implements LocalDataRepository {
    SharedPreferences preferences;

    public SharedPreferencesRepository(Context context) {
        String appName = context.getString(R.string.app_name);
        preferences = context.getSharedPreferences(appName, Context.MODE_PRIVATE);
    }

    @Override
    public void saveUser(BaseUser user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(BaseUser.LOGIN_ARG, true);
        editor.putString(BaseUser.FIRST_NAME_ARG, user.getFirstName());
        editor.putString(BaseUser.LAST_NAME_ARG, user.getLastName());
        editor.putString(BaseUser.TELEPHONE_ARG, user.getPhone());
        editor.putString(BaseUser.PHOTO_BACKEND_ARG, user.getPhotoUrl());
        editor.putString(BaseUser.PHOTO_LOCAL_ARG, user.getPhotoLocalUrl());
        editor.putString(BaseUser.HASH_ARG, user.getHash());
        editor.putInt(BaseUser.ID_ARGS, user.getUserId());
        editor.putString(BaseUser.EMAIL_ARG, user.getEmail());
        editor.putFloat(BaseUser.RATING_ARG, user.getRating());

        editor.apply();
    }

    @Override
    public void restoreUser(BaseUser user) {
        user.setFirstName(preferences.getString(BaseUser.FIRST_NAME_ARG,
                BaseUser.DEFAULT_FIRST_NAME));
        user.setLastName(preferences.getString(BaseUser.LAST_NAME_ARG,
                BaseUser.DEFAULT_LAST_NAME));
        user.setPhone(preferences.getString(BaseUser.TELEPHONE_ARG,
                BaseUser.DEFAULT_TELEPOHNE));
        user.setPhotoUrl(preferences.getString(BaseUser.PHOTO_BACKEND_ARG,
                ""));
        user.setPhotoLocalUrl(preferences.getString(BaseUser.PHOTO_LOCAL_ARG,
                ""));
        user.setLogin(preferences.getBoolean(BaseUser.LOGIN_ARG, false));
        user.setHash(preferences.getString(BaseUser.HASH_ARG, ""));
        user.setUserId(preferences.getInt(BaseUser.ID_ARGS, 0));
        user.setRating(preferences.getFloat(BaseUser.RATING_ARG, 0));
        user.setEmail(preferences.getString(BaseUser.EMAIL_ARG, ""));
    }

    @Override
    public void clearAll(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}
