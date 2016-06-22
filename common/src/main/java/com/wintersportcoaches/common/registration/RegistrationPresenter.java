package com.wintersportcoaches.common.registration;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.login.LoginView;
import com.wintersportcoaches.common.model.LocalDataRepository;
import com.wintersportcoaches.common.rest.LoginResponseSerializer;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.CommonUtils;
import com.wintersportcoaches.common.utils.Utils;

import java.io.IOException;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 18.06.16.
 */
public class RegistrationPresenter extends BasePresenter<BaseUser, RegistrationView> {

    private boolean mIsLoadingData = false;
    NetworkService mNetworkService;
    LocalDataRepository repository;
    BaseUser user;

    public RegistrationPresenter(NetworkService networkService, LocalDataRepository repository, BaseUser user) {
        this.mNetworkService = networkService;
        this.repository = repository;
        this.user = user;
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


                Uri newProfileImageUri = registrationView.getPhotoImageURI();
                MultipartBody.Part imageBody = null;
                if (newProfileImageUri != null) {
                    try {
                        imageBody = CommonUtils.createImageJsonObject(newProfileImageUri.getPath());
                        user.setPhotoLocalUrl(newProfileImageUri.getPath() + CommonUtils.SCALED_DOWN_SUFFIX);
                    } catch (IOException e) {
                        e.printStackTrace();
                        imageBody = null;
                    }

                }
                mNetworkService.user_register(CommonUtils.fromStringToRequestBody(Utils.getPhoneNumberFromFormattedNumber(registrationView.getPhoneNumber())),
                        CommonUtils.fromStringToRequestBody(registrationView.getPassword()),
                        CommonUtils.fromStringToRequestBody(registrationView.getFirstName()),
                        CommonUtils.fromStringToRequestBody(registrationView.getLastName()),
                        CommonUtils.fromStringToRequestBody( registrationView.getEmail()),
                        registrationView.isCoach(),imageBody)
                        .enqueue(new CommonErrorHandleRetofitCallback<LoginResponseSerializer>() {

                            @Override
                            public void onFailure(Call<LoginResponseSerializer> call, Throwable t) {
                                super.onFailure(call, t);
                                mIsLoadingData = false;
                            }

                            @Override
                            protected void success(Call<LoginResponseSerializer> call, Response<LoginResponseSerializer> response) {
                                LoginResponseSerializer loginResponseSerializer = response.body();
                                model.setHash(loginResponseSerializer.getHash());
                                mNetworkService.user_get(response.body().getId()).enqueue(new CommonErrorHandleRetofitCallback<BaseUser>() {
                                    @Override
                                    public void onResponse(Call<BaseUser> call, Response<BaseUser> response) {
                                        super.onResponse(call, response);
                                        mIsLoadingData = false;
                                    }

                                    @Override
                                    public void onFailure(Call<BaseUser> call, Throwable t) {
                                        super.onFailure(call, t);
                                        mIsLoadingData = false;
                                    }

                                    @Override
                                    protected void success(Call<BaseUser> call, Response<BaseUser> response) {
                                        super.success(call, response);
                                        model.init(response.body());
                                        model.setLogin(true);
                                        repository.saveUser(model);
                                        if(view() != null) view().successRegistration();
                                    }
                                });

                            }
                        });
                mIsLoadingData = true;
            }
        }
    }

}
