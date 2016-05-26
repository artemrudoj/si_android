package com.wintersportcoaches.pupil.chats.dialog;



import com.wintersportcoaches.common.Chat;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;
import java.util.List;
import com.wintersportcoaches.common.base.recylverviewedfragment.BaseRecycledViewPresenter;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 24.05.16.
 */
public class MessagesPresenter extends BaseRecycledViewPresenter<List<Message>,MessagesView> {

    private final int mOpponentId;
    private BaseUser mUser;
    private int mChatId;
    NetworkService mNetworkService;




    public MessagesPresenter(int mChatId, int mOpponentId,NetworkService mNetworkService, BaseUser mUser) {
        this.mChatId = mChatId;
        this.mOpponentId = mOpponentId;
        this.mNetworkService = mNetworkService;
        this.mUser = mUser;
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
        });
        view().clearInputArea();
    }

    void receiveMessage(Message message) {
        view().appendOneMessage(message);
    }




    private void getChatIdAndMessages(){
        mNetworkService.get_chat_with_user(mUser.getHash(), mOpponentId).enqueue(new CommonErrorHandleRetofitCallback<Chat>() {
            @Override
            protected void success(Call<Chat> call, Response<Chat> response) {
                super.success(call, response);
                mChatId = response.body().getId();
                loadData();
            }
        });
    }

    @Override
    protected void loadData() {
        if (mChatId != 0) {
            loadMessages();
        }else {
            getChatIdAndMessages();
        }
    }

    private void loadMessages() {
        mNetworkService.get_chat_history(mUser.getHash(), mChatId).enqueue(mLoadDataCallback);
    }
}
