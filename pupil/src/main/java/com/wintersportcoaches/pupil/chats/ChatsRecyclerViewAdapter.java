package com.wintersportcoaches.pupil.chats;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wintersportcoaches.common.base.recylverviewedfragment.BaseRecyclerViewAdapter;

import com.wintersportcoaches.common.Chat;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.coaches.CoachesViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexeykhatskevich on 24.05.16.
 */
public class ChatsRecyclerViewAdapter  extends  BaseRecyclerViewAdapter<ChatViewHolder, Chat> {

    IClickListener clickListener;


    public ChatsRecyclerViewAdapter( IClickListener clickListener) {

        this.clickListener = clickListener;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatViewHolder((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_row, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        if(data != null) {
            holder.setItem(data.get(position));
            holder.setPosition(position);
        }
    }




    public  interface IClickListener {
        void onClick(int position);
    }
}
