package com.wintersportcoaches.common.login;


import android.os.Bundle;

import com.artem.common.R;
import com.wintersportcoaches.common.base.UserActivity;


public class LoginActivity extends UserActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar);
        setToolbar("Вход");
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new LoginFragment()).commit();
        }
    }
}
