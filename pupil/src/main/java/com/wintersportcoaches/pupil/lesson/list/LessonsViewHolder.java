package com.wintersportcoaches.pupil.lesson.list;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wintersportcoaches.common.model.Lesson;
import com.wintersportcoaches.common.ui.views.IconWithHeaderTextView;
import com.wintersportcoaches.pupil.R;

/**
 * Created by artem on 19.06.16.
 */
public class LessonsViewHolder  extends RecyclerView.ViewHolder {

    IconWithHeaderTextView kindOfSportTextView;
    IconWithHeaderTextView startTimeTextView;
    IconWithHeaderTextView locationTextView;
    private Lesson item;


    public LessonsViewHolder(View itemView) {
        super(itemView);
        kindOfSportTextView = (IconWithHeaderTextView)itemView.findViewById(R.id.kind_of_sport_tv);
        startTimeTextView = (IconWithHeaderTextView) itemView.findViewById(R.id.start_time_tv);
        locationTextView = (IconWithHeaderTextView)itemView.findViewById(R.id.location_tv);
    }




    public void setItem(Lesson item) {
        this.item = item;
        kindOfSportTextView.setHeaderText(item.getKindOfSport());
        startTimeTextView.setHeaderText(item.getStartTime());
        locationTextView.setHeaderText(item.getPlace_string());
    }
}
