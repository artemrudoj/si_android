package com.wintersportcoaches.common.service;

import com.wintersportcoaches.common.model.Message;

/**
 * Created by artem on 25.05.16.
 */
public interface MessageListener {

    void onMessage(Message message);
}