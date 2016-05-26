package com.wintersportcoaches.pupil.chats.dialog;

import android.support.annotation.NonNull;

import com.wintersportcoaches.common.Chat;
import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.coaches.CoachesView;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 24.05.16.
 */
public class MessagesPresenter extends BasePresenter<List<Message>,MessagesView> {

    private final int mOpponentId;
    private BaseUser mUser;
    private int mChatId;
    NetworkService mNetworkService;

    private boolean isLoadingData = false;


    public MessagesPresenter(int mChatId, int mOpponentId,NetworkService mNetworkService, BaseUser mUser) {
        this.mChatId = mChatId;
        this.mOpponentId = mOpponentId;
        this.mNetworkService = mNetworkService;
        this.mUser = mUser;
    }

    @Override
    protected void updateView() {
        if (model.size() == 0) {
            view().showEmpty();
        } else {
            view().showMessages(model);
        }
    }

    void sendMessage(final String message) {
        if(message == null || message.equals(""))
            return;
        mNetworkService.send_message(mUser.getHash(), message, mChatId).enqueue(new CommonErrorHandleRetofitCallback<Object>() {
            @Override
            protected void success(Call<Object> call, Response<Object> response) {
                super.success(call, response);
                Message msg = new Message();
                msg.setChatId(mChatId);
                msg.setText(message);
                msg.setSenderId(mUser.getUserId());
                view().appendOneMessage(msg);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                super.onFailure(call, t);
            }

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                super.onResponse(call, response);
            }
        });
        view().clearInputArea();
    }

    void receiveMessage(Message message) {
        view().appendOneMessage(message);
    }


    @Override
    public void bindView(MessagesView view) {
        super.bindView(view);

        // Let's not reload data if it's already here
        if (model == null && !isLoadingData) {
            view().showLoading();
            loadData();
        }
    }

    private void getChatIdAndMessages(){
        mNetworkService.get_chat_with_user(mUser.getHash(), mOpponentId).enqueue(new CommonErrorHandleRetofitCallback<Chat>() {
            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                super.onFailure(call, t);
                view().stopLoading();
            }

            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                super.onResponse(call, response);
                view().stopLoading();
            }

            @Override
            protected void success(Call<Chat> call, Response<Chat> response) {
                super.success(call, response);
                mChatId = response.body().getId();
                loadData();
            }
        });
    }

    private void loadData() {
        if (mChatId != 0) {
            loadMessages();
        }else {
            getChatIdAndMessages();
        }
    }

    private void loadMessages() {
        isLoadingData = true;
        mNetworkService.get_chat_history(mUser.getHash(), mChatId).enqueue(new CommonErrorHandleRetofitCallback<List<Message>>() {
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                super.onFailure(call, t);
                isLoadingData = false;
                view().stopLoading();
            }

            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                super.onResponse(call, response);
                isLoadingData = false;
                view().stopLoading();
            }

            @Override
            protected void success(Call<List<Message>> call, Response<List<Message>> response) {
                super.success(call, response);
                model = response.body();
                view().showMessages(model);
            }
        });
    }
}
