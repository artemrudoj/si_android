package com.wintersportcoaches.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.artem.common.R;
import com.wintersportcoaches.common.base.BaseActivity;


public class ToolbarActivity extends BaseActivity {

    public void setToolbar(int titleResourceId) {
        setToolbar(getString(titleResourceId));
    }

    public void setToolbar(String title) {
        android.support.v7.widget.Toolbar mActionBarToolbar =
                (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_actionbar);
        mActionBarToolbar.setTitle("");
        TextView titleToolbar = (TextView) mActionBarToolbar.findViewById(R.id.title_tv);
        titleToolbar.setText(title);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void setToolbar() {
        setToolbar("");
    }

    public void changeTitle(String title) {
        android.support.v7.widget.Toolbar mActionBarToolbar =
                (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_actionbar);
        TextView titleToolbar = (TextView) mActionBarToolbar.findViewById(R.id.title_tv);
        titleToolbar.setText(title);
    }

    public void changeTitle(int title) {
        changeTitle(getString(title));
    }
}
