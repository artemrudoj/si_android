package com.wintersportcoaches.pupil.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.wintersportcoaches.pupil.R;

/**
 * Created by artem on 19.06.16.
 */
public class RadioButtonWithText extends LinearLayout implements Checkable {
    RadioButton radioButton;
    public RadioButtonWithText(Context context) {
        super(context);
        initView(context);
    }

    public RadioButtonWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.radio_button_with_text, this);
    }
    public RadioButtonWithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        radioButton = (RadioButton)findViewById(R.id.to_all_rbt);
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
}
