package com.wintersportcoaches.pupil.coaches;

import android.support.annotation.NonNull;

import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.base.recylverviewedfragment.BaseRecycledViewPresenter;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.chats.dialog.MessagesView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 23.05.16.
 */
public class CoachesMainPresenter extends BaseRecycledViewPresenter<List<BaseUser>,CoachesView> {



    NetworkService mNetworkService;

    public CoachesMainPresenter(NetworkService networkService) {
        mNetworkService = networkService;
    }

    @Override
    protected void loadData() {
        mNetworkService.users_get().enqueue(mLoadDataCallback);
    }

}
