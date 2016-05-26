package com.wintersportcoaches.pupil.chats;

import android.support.annotation.NonNull;

import com.wintersportcoaches.common.Chat;
import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.coaches.CoachesView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ChatsMainPresenter  extends BasePresenter<List<Chat>, ChatsListFragment> {
    private boolean isLoadingData = false;


    NetworkService mNetworkService;

    public ChatsMainPresenter(NetworkService networkService) {
        mNetworkService = networkService;
    }

    @Override
    protected void updateView() {
        if (model.size() == 0) {
            view().showEmpty();
        } else {
            view().showChats(model);
        }
    }


    @Override
    public void bindView(@NonNull ChatsListFragment view) {
        super.bindView(view);

        // Let's not reload data if it's already here
        if (model == null && !isLoadingData) {
            view().showLoading();
            loadData();
        }
    }

    private void loadData() {
        isLoadingData = true;
        mNetworkService.chats_get(view().getHash()).enqueue(new CommonErrorHandleRetofitCallback<List<Chat>>() {
            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                super.onFailure(call, t);
                isLoadingData = false;
                view().stopLoading();
            }

            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                super.onResponse(call, response);
                isLoadingData = false;
                view().stopLoading();
            }

            @Override
            protected void success(Call<List<Chat>> call, Response<List<Chat>> response) {
                super.success(call, response);
                model = response.body();
                view().showChats(model);
            }
        });
    }

}
