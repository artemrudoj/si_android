package com.wintersportcoaches.pupil;


import android.content.Intent;
import android.os.Bundle;

import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.base.navigationdrawer.NavigationDrawerListBaseAdapter;
import com.wintersportcoaches.common.base.navigationdrawer.NavigationDrawerProfileActivity;
import com.wintersportcoaches.common.base.navigationdrawer.NavigationItem;
import com.wintersportcoaches.common.base.navigationdrawer.TapHandler;
import com.wintersportcoaches.common.service.SocketListenerService;
import com.wintersportcoaches.pupil.chats.ChatsContainerActivity;
import com.wintersportcoaches.pupil.coaches.CoachesListContainerActivity;
import com.wintersportcoaches.pupil.lesson.create.CreateLessonActivity;
import com.wintersportcoaches.pupil.lesson.list.HistoryLessonActivity;
import com.wintersportcoaches.pupil.lesson.list.LessonListActivity;
import com.wintersportcoaches.pupil.profile.ProfileContainerActivity;

import java.util.ArrayList;

public class NavigationDrawerPupilActivity extends NavigationDrawerProfileActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headerView.setProfileActivityClass(ProfileContainerActivity.class);
    }

    @Override
    protected NavigationDrawerListBaseAdapter createAdapter() {
        ArrayList<NavigationItem> items = new ArrayList<>();
        items.add(new NavigationItem(R.drawable.vector_add_ic, R.string.new_lesson, false,false, new TapHandler() {
            @Override
            public void onTap() {
                startActivity(new Intent(NavigationDrawerPupilActivity.this, CreateLessonActivity.class));
            }
        }));
        items.add(new NavigationItem(R.drawable.vector_list_ic, R.string.my_lessons, true, false,new TapHandler() {
            @Override
            public void onTap() {
                startActivity(new Intent(NavigationDrawerPupilActivity.this, LessonListActivity.class));
            }
        }));
        items.add(new NavigationItem(R.drawable.vector_coaches_ic, R.string.instructors, false, false, new TapHandler() {
            @Override
            public void onTap() {
                startActivity(new Intent(NavigationDrawerPupilActivity.this, CoachesListContainerActivity.class));
            }
        }));
        items.add(new NavigationItem(R.drawable.vector_chats_ic, R.string.chat_list, true, false, new TapHandler() {
            @Override
            public void onTap() {
                startActivity(new Intent(NavigationDrawerPupilActivity.this, ChatsContainerActivity.class));
            }
        }));
        items.add(new NavigationItem(R.drawable.vector_history_ic, R.string.history_list, true, false, new TapHandler() {
            @Override
            public void onTap() {
                startActivity(new Intent(NavigationDrawerPupilActivity.this, HistoryLessonActivity.class));
            }
        }));
        items.add(new NavigationItem(R.drawable.vector_log_out_icon, R.string.log_out, false, false, new TapHandler() {
            @Override
            public void onTap() {
                WinterSportCoachesApplication.get(NavigationDrawerPupilActivity.this).
                        exitFromCurrentUser();
                SocketListenerService.stop(getApplicationContext());
                headerView.updateView();
            }
        }));

        NavigationDrawerListBaseAdapter adapter = new NavigationDrawerListBaseAdapter(this, items);
        //adapter.registerDataSetObserver(adapter.getDataSetObserver());
        return adapter;
    }
}
