package com.wintersportcoaches.common.base;

import android.app.Application;

import com.wintersportcoaches.common.user.BaseUser;

/**
 * Created by artem on 22.05.16.
 */
public class WinterSportCoachesBaseApplication extends Application {
    protected BaseUser user;

    public BaseUser getUser() {
        return user;
    }
    public boolean isLogin() {
        return user != null && user.isLogin();
    }

    public void setUser(BaseUser user) {
        this.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}