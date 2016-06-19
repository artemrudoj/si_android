package com.wintersportcoaches.pupil.lesson.create;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.wintersportcoaches.pupil.NavigationDrawerPupilActivity;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.ui.view.ChooseCoachView;

/**
 * Created by artem on 29.05.16.
 */
public class CreateLessonActivity extends NavigationDrawerPupilActivity {


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.container);
        if(currentFragment instanceof CreateLessonFragment) {
            ((CreateLessonFragment)currentFragment).onNewIntent(intent);
        }

    }

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