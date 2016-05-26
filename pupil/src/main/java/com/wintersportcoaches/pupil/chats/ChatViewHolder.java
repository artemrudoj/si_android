package com.wintersportcoaches.pupil.chats;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wintersportcoaches.common.Chat;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.coaches.CoachesRecyclerViewAdapter;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    private int id;
    private TextView chatName;
    private CardView rootView;
    private int position;
    private Chat item;

    public ChatViewHolder(CardView itemView, final ChatsRecyclerViewAdapter.IClickListener onClickListener) {
        super(itemView);
        rootView = itemView;
        chatName = (TextView) itemView.findViewById(R.id.chat_name);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(item.getId());
            }
        });
    }

    public void setItem(Chat item) {
        this.item = item;
        chatName.setText(item.getName());
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
