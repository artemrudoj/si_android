package com.wintersportcoaches.pupil.chats.dialog;

import com.wintersportcoaches.common.base.recylverviewedfragment.RecycledBaseView;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.user.BaseUser;

import java.util.List;

/**
 * Created by artem on 24.05.16.
 */
public interface MessagesView  extends RecycledBaseView {

    void clearInputArea();

    void appendOneMessage(Message message);
}
