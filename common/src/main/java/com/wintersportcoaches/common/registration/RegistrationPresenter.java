package com.wintersportcoaches.common.registration;

import android.support.annotation.NonNull;

import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.login.LoginView;
import com.wintersportcoaches.common.model.LocalDataRepository;
import com.wintersportcoaches.common.rest.LoginResponseSerializer;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.Utils;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 18.06.16.
 */
public class RegistrationPresenter extends BasePresenter<BaseUser, RegistrationView> {

    private boolean mIsLoadingData = false;
    NetworkService mNetworkService;
    LocalDataRepository repository;

    public RegistrationPresenter(NetworkService networkService, LocalDataRepository repository) {
        this.mNetworkService = networkService;
        this.repository = repository;
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void bindView(@NonNull RegistrationView view) {
        super.bindView(view);
        if(mIsLoadingData) {
            view().showLoading();
        }
    }


    public void registratinButtonPressed() {
        RegistrationView registrationView = view();
        if(registrationView != null) {
            if (registrationView.isValid()) {
                mNetworkService.user_register(Utils.getPhoneNumberFromFormattedNumber(registrationView.getPhoneNumber()),
                        registrationView.getPassword(),
                        registrationView.getFirstName(),
                        registrationView.getLastName(),
                        registrationView.getEmail(),
                        registrationView.isCoach())
                        .enqueue(new CommonErrorHandleRetofitCallback<String>() {

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                super.onFailure(call, t);
                                mIsLoadingData = false;
                            }

                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                super.onResponse(call, response);
                                mIsLoadingData = false;
                            }

                            @Override
                            protected void success(Call<String> call, Response<String> response) {
                                String hash = response.body();
//                                model.setHash(response.body().getHash());
//                                model.setUserId(response.body().getId());
//                                repository.saveUser(model);
//                                if(view() != null) view().successLogin();
                            }
                        });
                mIsLoadingData = true;
            }
        }
    }

}
