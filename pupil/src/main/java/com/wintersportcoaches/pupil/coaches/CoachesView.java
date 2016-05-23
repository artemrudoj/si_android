package com.wintersportcoaches.pupil.coaches;

import com.wintersportcoaches.common.user.BaseUser;

import java.util.List;

/**
 * Created by artem on 23.05.16.
 */
public interface CoachesView {

    void showCoaches(List<BaseUser> coaches);

    void showLoading();

    void showEmpty();

    void stopLoading();


}
