package com.wintersportcoaches.pupil.lesson.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.base.profile.ProfilePresenter;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.pupil.R;

/**
 * Created by artem on 29.05.16.
 */
public class CreateLessonFragment extends PresenteredFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_lesson, container, false);
        if (savedInstanceState == null) {
            presenter = new CreateLessonPresenter();
           // presenter.setModel(((UserActivity)getActivity()).getUser());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
      //  initViews(view);

        return view;
    }
}
