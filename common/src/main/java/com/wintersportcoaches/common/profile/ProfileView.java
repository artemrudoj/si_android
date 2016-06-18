package com.wintersportcoaches.common.profile;

import com.wintersportcoaches.common.user.BaseUser;

/**
 * Created by artem on 28.05.16.
 */
public interface ProfileView {
    void showLoading();
    void stopLoading();
    void showUser(BaseUser baseUser);
}
