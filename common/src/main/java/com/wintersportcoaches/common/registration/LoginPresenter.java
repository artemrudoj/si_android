package com.wintersportcoaches.common.registration;

import android.support.annotation.NonNull;

import com.wintersportcoaches.common.rest.LoginResponseSerializer;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.Utils;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 23.05.16.
 */
public class LoginPresenter extends BasePresenter<BaseUser, LoginView> {
    private boolean mIsLoadingData = false;
    NetworkService mNetworkService;

    public LoginPresenter(NetworkService mNetworkService) {
        this.mNetworkService = mNetworkService;
    }

    @Override
    protected void updateView() {
    }

    @Override
    public void bindView(@NonNull LoginView view) {
        super.bindView(view);
        if(mIsLoadingData) {
            view().showLoading();
        }
    }


    public void loginButtonPressed() {
        LoginView loginView = view();
        if(loginView != null) {
            if (loginView.validatePhoneNumber() && loginView.validatePhoneNumber()) {
                mNetworkService.user_login(Utils.getPhoneNumberFromFormattedNumber(loginView.getPhoneNumber()), loginView.getPassword())
                        .enqueue(new CommonErrorHandleRetofitCallback<LoginResponseSerializer>() {

                            @Override
                            public void onFailure(Call<LoginResponseSerializer> call, Throwable t) {
                                super.onFailure(call, t);
                                mIsLoadingData = false;
                            }

                            @Override
                            public void onResponse(Call<LoginResponseSerializer> call, Response<LoginResponseSerializer> response) {
                                super.onResponse(call, response);
                                mIsLoadingData = false;
                            }

                            @Override
                            protected void success(Call<LoginResponseSerializer> call, Response<LoginResponseSerializer> response) {
                                model.setHash(response.body().getHash());
                                model.setUserId(response.body().getId());
                                model.saveUser(view().getContext());
                                view().finish();
                            }
                        });
                mIsLoadingData = true;
            }
        }
    }
}
