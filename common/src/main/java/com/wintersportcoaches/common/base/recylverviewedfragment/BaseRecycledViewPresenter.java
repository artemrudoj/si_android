package com.wintersportcoaches.common.base.recylverviewedfragment;

import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.user.BaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 27.05.16.
 */
public abstract class BaseRecycledViewPresenter<M,V> extends BasePresenter<M,V> {
    protected boolean isLoadingData = false;
    @Override
    protected void updateView() {
        if (((List)model).size() == 0) {
            ((RecycledBaseView)view()).showEmpty();
        } else {
            ((RecycledBaseView)view()).showData(((List)model));
        }
    }

    @Override
    public void bindView(V view) {
        super.bindView(view);

        // Let's not reload data if it's already here
        if (model == null && !isLoadingData) {
            ((RecycledBaseView)view()).showLoading();
            isLoadingData = true;
            loadData();
        }
    }

    protected CommonErrorHandleRetofitCallback mLoadDataCallback = new CommonErrorHandleRetofitCallback<List<M>>() {
        @Override
        public void onFailure(Call<List<M>> call, Throwable t) {
            super.onFailure(call, t);
            isLoadingData = false;
            V view = view();
            if(view != null) ((RecycledBaseView) view).stopLoading();
        }

        @Override
        public void onResponse(Call<List<M>> call, Response<List<M>> response) {
            super.onResponse(call, response);
            isLoadingData = false;
            V view = view();
            if(view != null) ((RecycledBaseView) view).stopLoading();
        }

        @Override
        protected void success(Call<List<M>> call, Response<List<M>> response) {
            super.success(call, response);
            setModel((M) response.body());
        }
    };


    protected abstract void loadData();

}
