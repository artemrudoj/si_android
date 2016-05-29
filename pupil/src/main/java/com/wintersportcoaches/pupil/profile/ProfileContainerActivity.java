package com.wintersportcoaches.pupil.profile;

import android.app.Activity;
import android.os.Bundle;

import com.wintersportcoaches.common.base.navigationdrawer.NavigationDrawerProfileActivity;
import com.wintersportcoaches.common.base.profile.ProfileFragment;
import com.wintersportcoaches.pupil.NavigationDrawerPupilActivity;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.coaches.CoachesListFragment;

public class ProfileContainerActivity extends NavigationDrawerPupilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(R.string.instructors);
        addHomeProfileButton();
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new ProfileFragment()).commit();
        }
    }
}
