package com.wintersportcoaches.pupil.coaches;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.user.PupilUser;

import java.util.List;

/**
 * Created by artem on 22.05.16.
 */
public class CoachesRecyclerViewAdapter extends  RecyclerView.Adapter<CoachesViewHolder> {
    private final LayoutInflater mInflater;
    Context mContext;
    List<BaseUser> technologyList;
    IClickListener clickListener;



    public CoachesRecyclerViewAdapter(LayoutInflater inflater, Context context, List<BaseUser> technologies, IClickListener clickListener) {
        this.mInflater = inflater;
        this.mContext = context;
        this.technologyList = technologies;
        this.clickListener = clickListener;
    }

    @Override
    public CoachesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater != null) {
            return new CoachesViewHolder((CardView)mInflater.inflate(R.layout.intructor_list_row, parent, false), clickListener);
        }
        else {
            throw new RuntimeException("Oooops, looks like activity is dead");
        }
    }

    @Override
    public void onBindViewHolder(CoachesViewHolder holder, int position) {
        if(technologyList != null) {
            holder.setItem(technologyList.get(position));
            holder.setPosition(position);
        }
    }

    @Override
    public int getItemCount() {
        if(technologyList != null) {
            return technologyList.size();
        }
        return 0;
    }

    public  interface IClickListener {
        void onClick(int position);
    }
}
