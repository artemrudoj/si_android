package com.wintersportcoaches.common.base.recylverviewedfragment;

import android.support.v7.widget.RecyclerView;

import com.wintersportcoaches.common.Chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 27.05.16.
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder,T> extends RecyclerView.Adapter<VH>{

    protected List<T> data = new ArrayList<T>();

    public  void clearAndAddAll(List data) {
        this.data.clear();
        for(T item : (List<T>)data) {
            this.data.add(item);
        }
        notifyDataSetChanged();
    };


    @Override
    public int getItemCount() {
            return data.size();
    }

}
