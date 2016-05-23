package com.wintersportcoaches.common.base;

import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.user.BaseUser;

/**
 * Created by artem on 23.05.16.
 */
public class UserActivity extends BackButtonActivity {
    public BaseUser getUser() {
        return ((WinterSportCoachesApplication)getApplication()).getUser();
    }
}
