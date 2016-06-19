package com.wintersportcoaches.pupil.lesson.list;

import android.os.Bundle;

import com.wintersportcoaches.pupil.NavigationDrawerPupilActivity;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.coaches.CoachesListFragment;

public class LessonListActivity extends NavigationDrawerPupilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(R.string.my_lessons);
        addHomeProfileButton();
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new LessonListFragment()).commit();
        }
    }
}
