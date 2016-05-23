package com.wintersportcoaches.pupil.chats;

import android.app.Activity;
import android.os.Bundle;

import com.wintersportcoaches.common.base.ToolbarActivity;
import com.wintersportcoaches.pupil.NavigationDrawerPupilActivity;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.coaches.CoachesListFragment;

public class ChatsContainerActivity extends NavigationDrawerPupilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(R.string.chat_list);
        addHomeProfileButton();
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new ChatsListFragment()).commit();
        }
    }
}
