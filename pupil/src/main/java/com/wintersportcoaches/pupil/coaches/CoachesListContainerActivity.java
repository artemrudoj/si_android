package com.wintersportcoaches.pupil.coaches;


import android.os.Bundle;

import com.wintersportcoaches.pupil.NavigationDrawerPupilActivity;
import com.wintersportcoaches.pupil.R;

public class CoachesListContainerActivity extends NavigationDrawerPupilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(R.string.instructors);
        addHomeProfileButton();
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new CoachesListFragment()).commit();
        }
    }
}
