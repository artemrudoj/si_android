package com.wintersportcoaches.common.ui.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artem.common.R;

/**
 * Created by artem on 29.05.16.
 */
public class CircleButtonWithText extends LinearLayout implements Checkable {
    TextView textView;
    ImageView iconImageView;
    FrameLayout ovalFrameLayout;
    int iconId;
    String text;


    public CircleButtonWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.IconWithHeaderTextView,
                0, 0);
        if(attrs != null) {
            try {
                text = a.getString(R.styleable.IconWithHeaderTextView_titleText);
                iconId = a.getResourceId(R.styleable.IconWithHeaderTextView_iconImage, -1);
            } finally {
                a.recycle();
            }
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.circle_checkbox_with_text, this);
    }

    public CircleButtonWithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CircleButtonWithText(Context context) {
        super(context);
        initView(context, null);
    }

    @Override
    public void setChecked(boolean checked) {
        GradientDrawable bgShape = (GradientDrawable)ovalFrameLayout.getBackground();
        int color;
        if(checked) {
            color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        } else
            color = ContextCompat.getColor(getContext(), R.color.colorAccent);
        bgShape.setColor(color);
        textView.setTextColor(color);
    }

    @Override
    public boolean isChecked() {
        int trueColor = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        Boolean isChecked = textView.getCurrentTextColor() == trueColor;
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        textView = (TextView)findViewById(R.id.text_tv);
        textView.setText(text);
        iconImageView = (ImageView) findViewById(R.id.icon_iv);
        iconImageView.setImageResource(iconId);
        ovalFrameLayout = (FrameLayout)findViewById(R.id.oval_fl);
    }
}
