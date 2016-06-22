package com.wintersportcoaches.pupil.coaches;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wintersportcoaches.common.ActivityHolder;
import com.wintersportcoaches.common.base.recylverviewedfragment.BaseRecyclerViewAdapter;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.NetworkUtils;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.user.PupilUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 22.05.16.
 */
public class CoachesRecyclerViewAdapter extends BaseRecyclerViewAdapter<CoachesViewHolder,BaseUser> {


    ActivityHolder activityHolder;


    public CoachesRecyclerViewAdapter( ActivityHolder activityHolder) {
        this.activityHolder = activityHolder;
    }

    @Override
    public CoachesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CoachesViewHolder((CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.intructor_list_row, parent, false), activityHolder);
    }

    @Override
    public void onBindViewHolder(CoachesViewHolder holder, int position) {
        holder.setItem(data.get(position));
        holder.setPosition(position);
        NetworkUtils.loadProfileImage(data.get(position).getPhotoUrlWithServerUrl(),
                holder.profileImageView, null, null,  holder.profileImageView.getContext());
    }

    @Override
    public void onViewRecycled(final CoachesViewHolder holder) {
        holder.cleanup();
    }
}
