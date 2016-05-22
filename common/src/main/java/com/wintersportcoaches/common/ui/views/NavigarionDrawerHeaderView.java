package com.wintersportcoaches.common.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.artem.common.R;

public class NavigarionDrawerHeaderView extends LinearLayout {
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

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }
}
