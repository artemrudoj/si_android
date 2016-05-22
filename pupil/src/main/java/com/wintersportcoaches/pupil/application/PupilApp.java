package com.wintersportcoaches.pupil.application;

import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.pupil.user.PupilUser;

/**
 * Created by artem on 22.05.16.
 */
public class PupilApp extends WinterSportCoachesApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        user = new PupilUser(this);
    }
}
