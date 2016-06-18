package com.wintersportcoaches.common.profile;

import android.support.annotation.NonNull;

import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 28.05.16.
 */
public class ProfilePresenter extends BasePresenter<BaseUser, ProfileView> {
    NetworkService networkService;
    private boolean mIsLoadingData = false;
    BaseUser currentUser;
    int userId;

    public void setCurrentUser(BaseUser currentUser) {
        this.currentUser = currentUser;
    }

    public ProfilePresenter(NetworkService networkService, int userId) {
        this.networkService = networkService;
        this.userId = userId;
    }

    @Override
    protected void updateView() {
        if(model != null)
            view().showUser(model);
        return;
    }

    @Override
    public void bindView(@NonNull ProfileView view) {
        super.bindView(view);
        if(mIsLoadingData) {
            view().showLoading();
        }

        if (model == null && !mIsLoadingData) {
            view().showLoading();
            mIsLoadingData = true;
            loadData();
        }
    }

    private void loadData() {
        networkService.user_get(userId).enqueue(new CommonErrorHandleRetofitCallback<BaseUser>() {
            @Override
            public void onFailure(Call<BaseUser> call, Throwable t) {
                super.onFailure(call, t);
                mIsLoadingData = false;
            }

            @Override
            public void onResponse(Call<BaseUser> call, Response<BaseUser> response) {
                super.onResponse(call, response);
                mIsLoadingData = false;
            }

            @Override
            protected void success(Call<BaseUser> call, Response<BaseUser> response) {
                super.success(call, response);
                setModel(response.body());
            }
        });
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
