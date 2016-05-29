package com.wintersportcoaches.common.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artem.common.R;

/**
 * Created by artem on 28.05.16.
 */
public class IconWithHeaderTextView extends LinearLayout {
    TextView headerTextView;
    ImageView iconImageView;
    int iconId;
    String title;


    public void setIconId(int iconId) {
        this.iconId = iconId;
        if(iconImageView == null)
            iconImageView = (ImageView) findViewById(R.id.icon_iv);
        iconImageView.setImageResource(iconId);
    }

    public void setHeaderText(String title) {
        this.title = title;
        if(headerTextView == null)
            headerTextView = (TextView)findViewById(R.id.header_text_tv);
        headerTextView.setText(title);
    }

    public IconWithHeaderTextView(Context context) {
        super(context);
        initView(context, null);
    }

    public IconWithHeaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public IconWithHeaderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.IconWithHeaderTextView,
                0, 0);
        if(attrs != null) {
            try {
                title = a.getString(R.styleable.IconWithHeaderTextView_titleText);
                iconId = a.getResourceId(R.styleable.IconWithHeaderTextView_iconImage, -1);
            } finally {
                a.recycle();
            }
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.icon_with_header_text_layout, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerTextView = (TextView)findViewById(R.id.header_text_tv);
        headerTextView.setText(title);
        iconImageView = (ImageView) findViewById(R.id.icon_iv);
        iconImageView.setImageResource(iconId);
    }
}
