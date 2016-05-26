package com.wintersportcoaches.pupil.chats;

import android.support.annotation.NonNull;

import com.wintersportcoaches.common.Chat;
import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.base.recylverviewedfragment.BaseRecycledViewPresenter;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.coaches.CoachesView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ChatsMainPresenter  extends BaseRecycledViewPresenter<List<Chat>, ChatsListFragment> {



    NetworkService mNetworkService;

    public ChatsMainPresenter(NetworkService networkService) {
        mNetworkService = networkService;
    }

    @Override
    protected void loadData() {
        mNetworkService.chats_get(view().getHash()).enqueue(mLoadDataCallback);
    }

}
