package com.wintersportcoaches.pupil.coaches;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wintersportcoaches.common.ActivityHolder;
import com.wintersportcoaches.common.base.BaseFragment;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.recylverviewedfragment.RecyclerViewedFragment;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.ui.FragmentProgressBarHelper;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;

import java.util.List;


public class CoachesListFragment extends RecyclerViewedFragment implements CoachesView, ActivityHolder {


    public CoachesListFragment() {
        // Required empty public constructor
    }

    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            presenter = new CoachesMainPresenter(NetworkServiceFactory.getNetworkService());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        View view =  inflater.inflate(R.layout.fragment_coaches_list, container, false);
        setUpRecyclerView(view);
        mFragmentProgressBarHelper = new FragmentProgressBarHelper(mRecyclerView,
                getActivity(), (ViewGroup) view);


        return view;
    }

    private void setUpRecyclerView(View containerView) {
        mRecyclerView = (RecyclerView)containerView.findViewById(R.id.coaches_rv);
        mAdapter = new CoachesRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }

    public void performWithActivity(ActivityCaller activityCaller) {
        activityCaller.perform(getActivity());
    }

    @Override
    protected void onMessage(Message msg) {

    }
}
