package com.wintersportcoaches.pupil.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.coaches.CoachesListContainerActivity;

/**
 * Created by artem on 18.06.16.
 */
public class InstructorSmallWithButton extends LinearLayout {

    int userId;
    final String EXTRA_USERID = "InstructorSmallWithButton.userId";
    final String EXTRA_SUPER_STATE = "InstructorSmallWithButton.EXTRA_SUPER_STATE";
    TextView nameTextView;
    Button actionButton;
    ImageView profileImage;


    public void bindUser(BaseUser user) {
        userId = user.getUserId();
        setName(user.getFullName());
    }

    void setName(String name) {
        bindViews();
        nameTextView.setVisibility(VISIBLE);
        nameTextView.setText(name);
        actionButton.setText(R.string.delete);
    }

    void bindViews() {
        if(nameTextView == null) nameTextView = (TextView) findViewById(R.id.name_tv);
        if(actionButton == null) actionButton = (Button) findViewById(R.id.action_btn);
        actionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionButton.getText().equals(getContext().getString(R.string.add))) {
                    Intent intent = new Intent(getContext(), CoachesListContainerActivity.class);
                    getContext().startActivity(intent);
                } else if(actionButton.getText().equals(getContext().getString(R.string.delete))) {
                    ((ViewGroup) getParent()).removeView(InstructorSmallWithButton.this);
                }
            }
        });
        if(profileImage == null) profileImage = (ImageView) findViewById(R.id.profile_image);
    }


    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle)state;
            userId = bundle.getInt(EXTRA_USERID);
            state = bundle.getParcelable(EXTRA_SUPER_STATE);
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SUPER_STATE, super.onSaveInstanceState());
        bundle.putInt(EXTRA_USERID, userId);
        return bundle;
    }

    public InstructorSmallWithButton(Context context) {
        super(context);
        initView(context, null);
    }

    public InstructorSmallWithButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.instuctor_small_view, this);
    }

    public InstructorSmallWithButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        bindViews();
    }
}
