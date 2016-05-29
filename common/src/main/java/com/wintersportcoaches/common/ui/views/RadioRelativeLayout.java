package com.wintersportcoaches.common.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 29.05.16.
 */
public class RadioRelativeLayout extends RelativeLayout {

    List<Checkable> checkables = new ArrayList<>();

    public RadioRelativeLayout(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {

    }

    public RadioRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }


    @Override
    public void addView(final View child, int index, ViewGroup.LayoutParams params) {
        if(child instanceof Checkable) {
            checkables.add((Checkable) child);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Checkable item : checkables) {
                        if(item.equals(v)) {
                            item.setChecked(true);
                        } else {
                            item.setChecked(false);
                        }
                    }
                }
            });
        }
        super.addView(child, index, params);
    }


    public RadioRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }



}
