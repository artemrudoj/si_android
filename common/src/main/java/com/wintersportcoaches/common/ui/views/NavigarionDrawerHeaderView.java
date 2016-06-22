package com.wintersportcoaches.common.ui.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.artem.common.R;
import com.wintersportcoaches.common.login.LoginActivity;
import com.wintersportcoaches.common.profile.ProfileFragment;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.NetworkUtils;

public class NavigarionDrawerHeaderView extends LinearLayout {
    ImageView profileImageView;
    Class<?> profileActivity;
    BaseUser user;
    TextView nameTextView;


    protected ImageView profileImage;
    protected ImageView photoIcon;
    protected ProgressBar photoAnimation;

    protected String profileImageURI;


    public void setUser(BaseUser user) {
        this.user = user;
        bindViews();
        nameTextView.setText(user.getFullName());
    }

    public void updateView() {
        if((user != null) && nameTextView != null) {
            nameTextView.setText(user.getFullName());
        }

        updateImageView();
    }

    void updateImageView() {
        if (user.isLogin()) {
            String photoLocal = user.getPhotoLocalUrl();
            String photo = user.getPhotoUrl();
            if (photoLocal != null && !photoLocal.equals("") && !photoLocal.equals(profileImageURI)) {
                photoIcon.setVisibility(View.GONE);
                profileImage.setImageURI(null);
                profileImage.setImageURI(Uri.parse(photoLocal));
                profileImageURI = photoLocal;
                profileImage.setBackground(null);
            } else if (photo != null && !photo.equals("")
                    && (photoLocal == null || photoLocal.equals(""))) {
                loadProfileImage(photo);
            }

        } else {
            photoIcon.setVisibility(View.VISIBLE);
            profileImage.setBackground(getContext().getResources().getDrawable(R.drawable.circle));
        }
    }

    protected void loadProfileImage(final String photo) {
//        photoAnimation.getIndeterminateDrawable().setColorFilter(
//                ContextCompat.getColor(context, R.color.grey_brown), PorterDuff.Mode.MULTIPLY);
        NetworkUtils.loadProfileImage(photo, profileImage, photoIcon, photoAnimation, getContext());
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
                if(user.isLogin()) {
                    goToChangeProfileActivity();
                } else {
                    LoginActivity.go(getContext());
                }
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setImageClickListener();
        bindViews();
    }

    void bindViews() {
        if(nameTextView == null) nameTextView = (TextView)findViewById(R.id.name_tv);
        photoAnimation = (ProgressBar) findViewById(R.id.progressbar);
        profileImage = (ImageView) findViewById(R.id.profile_image);
        photoIcon = (ImageView) findViewById(R.id.photo_icon_iv);

    }

    void goToChangeProfileActivity(){
        if (profileActivity == null) throw new IllegalStateException("profile activity must be set");
        Intent intent = new Intent(getContext(), profileActivity);
        intent.putExtra(ProfileFragment.EXTRA_USER_ID, user.getUserId());
        getContext().startActivity(intent);
    }
}
