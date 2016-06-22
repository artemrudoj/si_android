package com.wintersportcoaches.pupil.profile;


import android.os.Bundle;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.profile.MyProfileFragment;
import com.wintersportcoaches.common.profile.ProfileFragment;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;


public class ProfileContainerActivity extends UserActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar);
        setToolbar(R.string.profile);
        Bundle bundle = getIntent().getExtras();
        if(savedInstanceState == null) {
            BaseUser user = getUser();
            int id = bundle.getInt(ProfileFragment.EXTRA_USER_ID);
            if(id != user.getUserId()) {
                getFragmentManager().beginTransaction().add(R.id.container, ProfileFragment.newInstance(bundle)).commit();
            }
            else {
                getFragmentManager().beginTransaction().add(R.id.container, MyProfileFragment.newInstance(bundle)).commit();
            }
        }
    }
}
