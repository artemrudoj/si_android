package com.wintersportcoaches.pupil.coaches;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.user.PupilUser;

/**
 * Created by artem on 22.05.16.
 */
public class CoachesViewHolder  extends RecyclerView.ViewHolder {
    private TextView mFirstName;
    private TextView mSecondName;
    private CardView rootView;
    private BaseUser item;
    private int position;

    public CoachesViewHolder(CardView itemView, final CoachesRecyclerViewAdapter.IClickListener onClickListener) {
        super(itemView);
        rootView = itemView;
        mFirstName = (TextView) itemView.findViewById(R.id.first_name_tv);
        mSecondName = (TextView)itemView.findViewById(R.id.second_name_tv);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });
    }




    public void setItem(BaseUser item) {
        this.item = item;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
