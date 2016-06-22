package com.wintersportcoaches.pupil.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.wefika.flowlayout.FlowLayout;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.NetworkUtils;
import com.wintersportcoaches.pupil.R;

/**
 * Created by artem on 19.06.16.
 */
public class RadioButtonWithFlow extends LinearLayout implements Checkable {
    RadioButton radioButton;
    FlowLayout flowLayout;
    public RadioButtonWithFlow(Context context) {
        super(context);
        initView(context);
    }

    public RadioButtonWithFlow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.radio_button_with_flow_layout, this);
    }
    public RadioButtonWithFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        radioButton = (RadioButton)findViewById(R.id.to_all_rbt);
        flowLayout = (FlowLayout)findViewById(R.id.coaches_fl);
    }

    @Override
    public void setChecked(boolean checked) {
        radioButton.setChecked(checked);
    }

    @Override
    public boolean isChecked() {
        return radioButton.isChecked();
    }

    @Override
    public void toggle() {
        radioButton.toggle();
    }

    public void addUser(BaseUser user) {
        InstructorSmallWithButton instructorSmallWithButton = new InstructorSmallWithButton(getContext());
        instructorSmallWithButton.bindUser(user);
        FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        instructorSmallWithButton.setLayoutParams(layoutParams);
        flowLayout.addView(instructorSmallWithButton, 0);
        NetworkUtils.loadProfileImage(user.getPhotoUrlWithServerUrl(),
                instructorSmallWithButton.profileImage, null, null,  getContext());

    }
}
