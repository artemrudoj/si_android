package com.wintersportcoaches.pupil.lesson.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wintersportcoaches.common.base.recylverviewedfragment.BaseRecyclerViewAdapter;
import com.wintersportcoaches.common.model.Lesson;
import com.wintersportcoaches.pupil.R;

/**
 * Created by artem on 19.06.16.
 */
public class LessonsRecyclerViewAdapter extends BaseRecyclerViewAdapter<LessonsViewHolder,Lesson> {
    @Override
    public LessonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LessonsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_row, parent, false));
    }

    @Override
    public void onBindViewHolder(LessonsViewHolder holder, int position) {
        holder.setItem(data.get(position));
    }
}
