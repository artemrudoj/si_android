package com.wintersportcoaches.pupil.coaches;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wintersportcoaches.common.base.BaseFragment;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;

import java.util.List;


public class CoachesListFragment extends BaseFragment {


    public CoachesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_coaches_list, container, false);
        setUpRecyclerView(view);

        return view;
    }

    private void setUpRecyclerView(View containerView) {
        Activity activity = getActivity();
        if(activity != null) {
            List<BaseUser> list = null;
            RecyclerView mRecyclerView = (RecyclerView)containerView.findViewById(R.id.coaches_rv);
            CoachesRecyclerViewAdapter simpleViewHolderAdapter =
                    new CoachesRecyclerViewAdapter(activity.getLayoutInflater(), activity, list,
                            new CoachesRecyclerViewAdapter.IClickListener() {
                                @Override
                                public void onClick(int position) {
//                                    Fragment fragment = TechnologyViewPagerFragment.newInstance(position);
//                                    TechnologiesListFragment.this.getActivity().getSupportFragmentManager().beginTransaction().
//                                            replace(R.id.tech_container_fl,fragment).addToBackStack(null).commit();
                                }
                            });
            mRecyclerView.setAdapter(simpleViewHolderAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setHasFixedSize(true);
        }
    }

}
