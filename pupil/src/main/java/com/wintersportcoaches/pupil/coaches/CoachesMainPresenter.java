package com.wintersportcoaches.pupil.coaches;

import android.support.annotation.NonNull;

import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 23.05.16.
 */
public class CoachesMainPresenter extends BasePresenter<List<BaseUser>, CoachesView> {
    private boolean isLoadingData = false;


    NetworkService mNetworkService;

    public CoachesMainPresenter(NetworkService networkService) {
        mNetworkService = networkService;
    }

    @Override
    protected void updateView() {

    }


    @Override
    public void bindView(@NonNull CoachesView view) {
        super.bindView(view);

        // Let's not reload data if it's already here
        if (model == null && !isLoadingData) {
            view().showLoading();
            loadData();
        }
    }

    private void loadData() {
        isLoadingData = true;
        mNetworkService.users_get().enqueue(new CommonErrorHandleRetofitCallback<List<BaseUser>>() {
            @Override
            public void onFailure(Call<List<BaseUser>> call, Throwable t) {
                super.onFailure(call, t);
                isLoadingData = false;
            }

            @Override
            public void onResponse(Call<List<BaseUser>> call, Response<List<BaseUser>> response) {
                super.onResponse(call, response);
                isLoadingData = false;
            }

            @Override
            protected void success(Call<List<BaseUser>> call, Response<List<BaseUser>> response) {
                super.success(call, response);
                model = response.body();
                view().showCoaches(model);
            }
        });
    }

}
