package com.wintersportcoaches.pupil.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;

/**
 * Created by artem on 18.06.16.
 */
public class ChooseCoachView extends LinearLayout {
    RadioButtonWithFlow radioButtonWithFlow;
    public ChooseCoachView(Context context) {
        super(context);
        initView(context);
    }

    public void addUser(BaseUser user){
        radioButtonWithFlow.addUser(user);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.choose_coach_view, this);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        radioButtonWithFlow = (RadioButtonWithFlow)findViewById(R.id.to_one_rbwf);
    }

    public ChooseCoachView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public ChooseCoachView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
}
