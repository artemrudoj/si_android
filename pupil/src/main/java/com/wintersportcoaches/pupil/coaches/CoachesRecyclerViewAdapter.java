package com.wintersportcoaches.pupil.coaches;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.user.PupilUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 22.05.16.
 */
public class CoachesRecyclerViewAdapter extends  RecyclerView.Adapter<CoachesViewHolder> {

    List<BaseUser> coaches = new ArrayList<>();
    IClickListener clickListener;


    public CoachesRecyclerViewAdapter( IClickListener clickListener) {

        this.clickListener = clickListener;
    }

    @Override
    public CoachesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CoachesViewHolder((CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.intructor_list_row, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(CoachesViewHolder holder, int position) {

        holder.setItem(coaches.get(position));
        holder.setPosition(position);

    }

    @Override
    public int getItemCount() {
        return coaches.size();
    }

    public void clearAndAddAll(List<BaseUser> newcoaches) {
        coaches.clear();
        for(BaseUser user : newcoaches) {
            coaches.add(user);
        }
        notifyDataSetChanged();
    }

    public  interface IClickListener {
        void onClick(int position);
    }
}
