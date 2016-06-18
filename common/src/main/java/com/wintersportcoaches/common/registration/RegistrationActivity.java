package com.wintersportcoaches.common.registration;

import android.os.Bundle;

import com.artem.common.R;
import com.wintersportcoaches.common.base.BackButtonActivity;
import com.wintersportcoaches.common.base.BaseActivity;
import com.wintersportcoaches.common.login.LoginFragment;

/**
 * Created by artem on 18.06.16.
 */
public class RegistrationActivity extends BackButtonActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar);
        setToolbar("Регистрация");
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new RegistrationFragment()).commit();
        }
    }
}
