package com.wintersportcoaches.common.ui.views;


import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artem.common.R;

import java.util.Calendar;

/**
 * Created by artem on 29.05.16.
 */
public class ChoisedParameter extends LinearLayout {

    TextView choisedParameterTextView;
    public ChoisedParameter(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.choise_parameter_layout, this);
    }

    public ChoisedParameter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ChoisedParameter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        choisedParameterTextView = (TextView)findViewById(R.id.coised_tv);
    }

    public void setChoisedParameterTextView(String data) {
        choisedParameterTextView.setText(data);
    }

}
