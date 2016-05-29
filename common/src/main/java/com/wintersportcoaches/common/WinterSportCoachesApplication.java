package com.wintersportcoaches.common;

import android.content.Context;

import com.wintersportcoaches.common.model.LocalDataRepository;
import com.wintersportcoaches.common.model.SharedPreferencesRepository;
import com.wintersportcoaches.common.service.SocketListenerService;
import com.wintersportcoaches.common.user.BaseUser;

/**
 * Created by artem on 22.05.16.
 */
public class WinterSportCoachesApplication extends android.app.Application {
    protected BaseUser user;
    protected LocalDataRepository repository;
    public static WinterSportCoachesApplication get(Context context) {
        return (WinterSportCoachesApplication)context.getApplicationContext();
    }
    public void exitFromCurrentUser() {
        repository.clearAll();
        user.setDefaultProperties();
    }

    public LocalDataRepository getRepository() {
        return repository;
    }

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
        repository = new SharedPreferencesRepository(this);
    }

    protected void startService() {
        if(isLogin()) {
            SocketListenerService.start(this);
        }
    }
}