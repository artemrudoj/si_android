package com.wintersportcoaches.pupil.profile;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.profile.MyProfileFragment;
import com.wintersportcoaches.common.profile.ProfileFragment;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;


public class ProfileContainerActivity extends UserActivity {

    public static Intent goTo(Context context, int userId) {
        Intent intent = new Intent(context, ProfileContainerActivity.class);
        intent.putExtra(ProfileFragment.EXTRA_USER_ID, userId);
        return intent;
    }


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
