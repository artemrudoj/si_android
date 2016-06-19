package com.wintersportcoaches.pupil.lesson.create;

import android.support.annotation.NonNull;

import com.wintersportcoaches.common.base.presenter.BasePresenter;
import com.wintersportcoaches.common.login.LoginView;
import com.wintersportcoaches.common.model.Lesson;
import com.wintersportcoaches.common.rest.handleerror.CommonErrorHandleRetofitCallback;
import com.wintersportcoaches.common.rest.service.NetworkService;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by artem on 29.05.16.
 */
public class CreateLessonPresenter extends BasePresenter<Lesson, CreateLessonView > {
    private boolean mIsLoadingData = false;
    final NetworkService mNetworkService;

    public CreateLessonPresenter(NetworkService mNetworkService) {
        this.mNetworkService = mNetworkService;
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void bindView(@NonNull CreateLessonView view) {
        super.bindView(view);
        if(mIsLoadingData) {
            view().showLoading();
        }
    }

    public void createLessonPressed() {
        CreateLessonView view = view();
        if(view != null) {
            if( view.isValidate() ) {
                mNetworkService.create_lesson(view.getHash(),
                        view.getPlace(),
                        view.getType(),
                        view.getStartTime(),
                        view.getCoach()).enqueue(new CommonErrorHandleRetofitCallback<Lesson>() {
                    @Override
                    public void onFailure(Call<Lesson> call, Throwable t) {
                        super.onFailure(call, t);
                        mIsLoadingData = false;
                    }

                    @Override
                    public void onResponse(Call<Lesson> call, Response<Lesson> response) {
                        super.onResponse(call, response);
                        mIsLoadingData = false;
                    }

                    @Override
                    protected void success(Call<Lesson> call, Response<Lesson> response) {
                        super.success(call, response);
                        CreateLessonView view = view();
                        if(view != null) view.successCreated();
                    }
                });
            }
        }
    }
}
