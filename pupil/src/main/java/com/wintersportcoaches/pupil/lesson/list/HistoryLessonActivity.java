package com.wintersportcoaches.pupil.lesson.list;

import android.os.Bundle;

import com.wintersportcoaches.pupil.NavigationDrawerPupilActivity;
import com.wintersportcoaches.pupil.R;

public class HistoryLessonActivity extends NavigationDrawerPupilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(R.string.history_list);
        addHomeProfileButton();
    }
}
