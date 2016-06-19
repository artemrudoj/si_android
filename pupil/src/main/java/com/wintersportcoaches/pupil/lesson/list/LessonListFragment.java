package com.wintersportcoaches.pupil.lesson.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.recylverviewedfragment.RecyclerViewedFragment;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.ui.FragmentProgressBarHelper;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.coaches.CoachesRecyclerViewAdapter;

/**
 * Created by artem on 19.06.16.
 */
public class LessonListFragment extends RecyclerViewedFragment implements LessonsView{

    RecyclerView mRecyclerView;

    @Override
    protected void onMessage(Message msg) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            BaseUser user = ((WinterSportCoachesApplication)getActivity().getApplication()).getUser();
            presenter = new LessonsPresenter(NetworkServiceFactory.getNetworkService(), user);
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        View view = inflater.inflate(R.layout.fragment_coaches_list, container, false);
        setUpRecyclerView(view);
        mFragmentProgressBarHelper = new FragmentProgressBarHelper(mRecyclerView,
                getActivity(), (ViewGroup) view);


        return view;
    }

    public void setUpRecyclerView(View containerView) {
        mRecyclerView = (RecyclerView)containerView.findViewById(R.id.coaches_rv);
        mAdapter = new LessonsRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }
}
