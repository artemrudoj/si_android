package com.wintersportcoaches.common.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * Created by artem on 25.01.16.
 */
public class FragmentProgressBarHelper {
    ProgressBar progressBar;
    View rootView;

    public FragmentProgressBarHelper(View viewById, Context context, ViewGroup view) {
        progressBar = new ProgressBar(context);
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        progressBar.setLayoutParams(params);
        view.addView(progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        this.rootView = viewById;
    }



    public void beginAnimation() {
        if(rootView.getVisibility() != View.INVISIBLE)
            rootView.setVisibility(View.INVISIBLE);
        if(progressBar.getVisibility() != View.VISIBLE)
            progressBar.setVisibility(View.VISIBLE);
    }

    public void endAnimation() {
        if(progressBar.getVisibility() != View.INVISIBLE)
            progressBar.setVisibility(View.INVISIBLE);
        if(rootView.getVisibility() != View.VISIBLE)
            rootView.setVisibility(View.VISIBLE);

    }
}
