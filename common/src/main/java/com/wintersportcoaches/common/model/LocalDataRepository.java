package com.wintersportcoaches.common.model;

import com.wintersportcoaches.common.user.BaseUser;

/**
 * Created by artem on 28.05.16.
 */
public interface LocalDataRepository {
    void saveUser(BaseUser model);
    void clearAll();
    void restoreUser(BaseUser user);
}
