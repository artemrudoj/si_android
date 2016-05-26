package com.wintersportcoaches.pupil.chats.dialog;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wintersportcoaches.common.base.BaseFragment;
import com.wintersportcoaches.common.base.ToolbarActivity;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.base.recylverviewedfragment.RecyclerViewedFragment;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.service.BindedServiceFragment;
import com.wintersportcoaches.common.ui.FragmentProgressBarHelper;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.coaches.CoachesMainPresenter;
import com.wintersportcoaches.pupil.coaches.CoachesRecyclerViewAdapter;

import java.util.List;

public class MessagesListFragment extends RecyclerViewedFragment implements MessagesView {


    private static final String ARG_CHAT_ID = "MessagesListFragment.ARG_CHAT_ID";
    private static final String ARG_OPPONENT_ID = "MessagesListFragment.ARG_OPPONENT_ID";
    private int mChatId;
    private int mOpponentId;



    RecyclerView mRecyclerView;

    private EditText inputEditText;


    public MessagesListFragment() {
        // Required empty public constructor
    }


    public static MessagesListFragment newInstance(int chatId) {
        MessagesListFragment fragment = new MessagesListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CHAT_ID, chatId);
        fragment.setArguments(args);
        return fragment;
    }

    public static MessagesListFragment newInstanceWithOpponentId(int opponentId) {
        MessagesListFragment fragment = new MessagesListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_OPPONENT_ID, opponentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
                mOpponentId = getArguments().getInt(ARG_OPPONENT_ID, 0);
                mChatId = getArguments().getInt(ARG_CHAT_ID, 0);
            }
    }

    @Override
    protected void onMessage(Message msg) {
        ((MessagesPresenter)presenter).receiveMessage(msg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            presenter = new MessagesPresenter(mChatId, mOpponentId, NetworkServiceFactory.getNetworkService(),
                    ((UserActivity)getActivity()).getUser());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
        View view =  inflater.inflate(R.layout.fragment_messages_list, container, false);
        setUpRecyclerView(view);
        initViews(view);
        mFragmentProgressBarHelper = new FragmentProgressBarHelper(mRecyclerView,
                getActivity(), (ViewGroup) view);

        ((ToolbarActivity)getActivity()).setToolbar();
        return view;
    }

    private void initViews(View view) {
        inputEditText = (EditText)view.findViewById(R.id.new_message_et);
        Button sendMessageButton = (Button)view.findViewById(R.id.send_message_btn);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendedMessage = inputEditText.getText().toString();
                if(sendedMessage.equals("")) {
                    Toast.makeText(getActivity(), "Сообщение не может быть пустым", Toast.LENGTH_LONG).show();
                } else {
                    ((MessagesPresenter)presenter).sendMessage(sendedMessage);
                }
            }
        });
    }

    private void setUpRecyclerView(View view) {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.messages_rv);
        mAdapter = new MessagesRecyclerViewAdapter(((UserActivity)getActivity()).getUser());
        mRecyclerView.setAdapter(mAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

    }


    @Override
    public void clearInputArea() {
        inputEditText.setText("");
    }

    @Override
    public void appendOneMessage(Message message) {
        ((MessagesRecyclerViewAdapter)mAdapter).addOneMessage(message);
        mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
    }
}
