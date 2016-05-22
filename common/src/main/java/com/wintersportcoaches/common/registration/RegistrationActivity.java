package com.wintersportcoaches.common.registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.artem.common.R;
import com.wintersportcoaches.common.base.ToolbarActivity;

public class RegistrationActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar);
    }
}
