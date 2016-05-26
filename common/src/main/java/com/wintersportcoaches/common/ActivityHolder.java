package com.wintersportcoaches.common;

import android.app.Activity;

public interface ActivityHolder {
    public interface ActivityCaller{
        void perform(Activity activity);
    }
    void performWithActivity(ActivityCaller activityCaller);
}
