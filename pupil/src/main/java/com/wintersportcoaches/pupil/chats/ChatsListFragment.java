package com.wintersportcoaches.pupil.chats;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wintersportcoaches.common.Chat;
import com.wintersportcoaches.common.base.BaseFragment;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.service.BindedServiceFragment;
import com.wintersportcoaches.common.ui.FragmentProgressBarHelper;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.chats.dialog.MessagesListFragment;

import java.util.List;

public class ChatsListFragment  extends BindedServiceFragment {


    public ChatsListFragment() {
        // Required empty public constructor
    }

    RecyclerView mRecyclerView;
    FragmentProgressBarHelper fragmentProgressBarHelper;
    private ChatsMainPresenter presenter;
    private ChatsRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            presenter = new ChatsMainPresenter(NetworkServiceFactory.getNetworkService());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        View view =  inflater.inflate(R.layout.fragment_chats_list, container, false);
        setUpRecyclerView(view);
        fragmentProgressBarHelper = new FragmentProgressBarHelper(mRecyclerView,
                getActivity(), (ViewGroup) view);

        return view;
    }

    private void setUpRecyclerView(View containerView) {
        Activity activity = getActivity();
        if(activity != null) {
            mRecyclerView = (RecyclerView)containerView.findViewById(R.id.chats_rv);
            adapter = new ChatsRecyclerViewAdapter(
                    new ChatsRecyclerViewAdapter.IClickListener() {
                        @Override
                        public void onClick(int position) {
                            Fragment fragment = MessagesListFragment.newInstance(position);
                            FragmentTransaction fragmentTransaction = ChatsListFragment.this.getActivity().getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.container,fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    });
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setHasFixedSize(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        presenter.unbindView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        PresenterManager.getInstance().savePresenter(presenter, outState);
    }

    public void showChats(List<Chat> chats) {
        adapter.clearAndAddAll(chats);
    }

    public void showLoading() {
        fragmentProgressBarHelper.beginAnimation();
    }

    public void showEmpty() {

    }

    public void stopLoading() {
        fragmentProgressBarHelper.endAnimation();
    }

    public String getHash() {
        UserActivity userActivity = (UserActivity) getActivity();
        if(userActivity != null) {
            return userActivity.getUser().getHash();
        } else {
            return null;
        }
    }


    @Override
    protected void onMessage(Message msg) {
        Toast.makeText(getActivity(), "message", Toast.LENGTH_LONG).show();
    }
}
