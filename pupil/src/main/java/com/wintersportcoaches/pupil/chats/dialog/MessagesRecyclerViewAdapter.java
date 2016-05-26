package com.wintersportcoaches.pupil.chats.dialog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_row, parent, false);
        MessagesViewHolder messagesViewHolder = new MessagesViewHolder(v);
        messagesViewHolder.mMessageTextView = (TextView)v.findViewById(R.id.message_tv);
        return  messagesViewHolder;
    }

    @Override
    public void onBindViewHolder(MessagesViewHolder holder, int position) {
        holder.mMessageTextView.setText(mMessages.get(position).getText());
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
