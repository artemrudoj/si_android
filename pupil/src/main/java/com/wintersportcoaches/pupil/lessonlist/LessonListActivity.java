package com.wintersportcoaches.pupil.lessonlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wintersportcoaches.pupil.NavigationDrawerPupilActivity;
import com.wintersportcoaches.pupil.R;

public class LessonListActivity extends NavigationDrawerPupilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(R.string.my_lessons);
        addHomeProfileButton();
    }
}
