package com.wintersportcoaches.common.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.user.BaseUser;


/**
 *
 * Created by Vadim Matsishin on 05/12/15.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected String getHash() {
        return ((WinterSportCoachesApplication) getApplication()).getUser().getHash();
    }

    protected BaseUser getUser() {
        return ((WinterSportCoachesApplication) getApplication()).getUser();
    }

    protected boolean isLogin() {
        return ((WinterSportCoachesApplication) getApplication()).getUser().isLogin();
    }

}
