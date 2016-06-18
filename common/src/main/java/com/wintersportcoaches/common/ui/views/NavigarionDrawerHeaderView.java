package com.wintersportcoaches.common.ui.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.artem.common.R;
import com.wintersportcoaches.common.profile.ProfileFragment;
import com.wintersportcoaches.common.user.BaseUser;

public class NavigarionDrawerHeaderView extends LinearLayout {
    ImageView profileImageView;
    Class<?> profileActivity;
    BaseUser user;

    public void setUser(BaseUser user) {
        this.user = user;
    }

    public void setProfileActivityClass(Class<?> profileActivity ){
        this.profileActivity = profileActivity;
        setImageClickListener();
    }
    public NavigarionDrawerHeaderView(Context context) {
        super(context);
        initView(context);
    }

    public NavigarionDrawerHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NavigarionDrawerHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.header_view, this);
    }

    void setImageClickListener() {
        profileImageView = (ImageView)findViewById(R.id.profile_image);
        profileImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChangeProfileActivity();
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setImageClickListener();
    }

    void goToChangeProfileActivity(){
        if (profileActivity == null) throw new IllegalStateException("profile activity must be set");
        Intent intent = new Intent(getContext(), profileActivity);
        intent.putExtra(ProfileFragment.EXTRA_USER_ID, user.getUserId());
        getContext().startActivity(intent);
    }
}
