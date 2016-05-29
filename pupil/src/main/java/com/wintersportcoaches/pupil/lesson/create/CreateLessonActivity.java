package com.wintersportcoaches.pupil.lesson.create;

import android.os.Bundle;

import com.wintersportcoaches.pupil.NavigationDrawerPupilActivity;
import com.wintersportcoaches.pupil.R;

/**
 * Created by artem on 29.05.16.
 */
public class CreateLessonActivity extends NavigationDrawerPupilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(R.string.create_lesson);
        addHomeProfileButton();
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new CreateLessonFragment()).commit();
        }
    }
}