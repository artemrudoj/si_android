package com.wintersportcoaches.pupil.chats.dialog;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 24.05.16.
 */
public class MessagesRecyclerViewAdapter extends  RecyclerView.Adapter<MessagesViewHolder>  {
    List<Message> mMessages = new  ArrayList<>();
    BaseUser baseUser;

    public MessagesRecyclerViewAdapter(BaseUser baseUser) {
        this.baseUser = baseUser;
    }

    private static final int TYPE_MY_MESSAGE = 1;
    private static final int TYPE_OTHER_MESSAGE = 2;


    @Override
    public int getItemViewType(int position) {
        return baseUser.getUserId() == mMessages.get(position).getSenderId() ?
                TYPE_MY_MESSAGE : TYPE_OTHER_MESSAGE;
    }


    void initTextViewForViewType(int viewType, TextView textView) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
        switch (viewType) {
            case TYPE_MY_MESSAGE:
                params.gravity = Gravity.RIGHT;
                textView.setBackgroundResource(R.drawable.my_message_buuble_shape);
                break;
            case TYPE_OTHER_MESSAGE:
                params.gravity = Gravity.LEFT;
                textView.setBackgroundResource(R.drawable.message_buuble_shape);
                break;
        }
        textView.setLayoutParams(params);
    }


    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_row, parent, false);
        MessagesViewHolder messagesViewHolder = new MessagesViewHolder(v);
        messagesViewHolder.mMessageTextView = (TextView)v.findViewById(R.id.message_tv);
        initTextViewForViewType(viewType, messagesViewHolder.mMessageTextView);

        return  messagesViewHolder;
    }

    @Override
    public void onBindViewHolder(MessagesViewHolder holder, int position) {
        holder.mMessageTextView.setText(mMessages.get(position).getText());
        initTextViewForViewType(holder.getItemViewType(), holder.mMessageTextView);
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public void clearAndAddAll(List<Message> newMessages) {
        mMessages.clear();
        for(Message message : newMessages) {
            mMessages.add(message);
        }
        notifyDataSetChanged();
    }

    public void addOneMessage(Message message) {
        mMessages.add(message);
        notifyDataSetChanged();
    }
}
