package com.wintersportcoaches.common.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.artem.common.R;

/**
 * Created by artem on 28.05.16.
 */
public class OneSkillEntry extends RelativeLayout {
    TextView skillNameTextView;
    TextView skillPriceTextView;
    public OneSkillEntry(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.one_skill_entry, this);
    }

    public OneSkillEntry(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public OneSkillEntry(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        skillNameTextView = (TextView)findViewById(R.id.skill_name_tv);
        skillPriceTextView = (TextView)findViewById(R.id.skill_price_tv);
    }

    public void setSkillName(String skillName) {
        if(skillNameTextView == null)
            skillNameTextView = (TextView)findViewById(R.id.skill_name_tv);
        skillNameTextView.setText(skillName);
    }

    public void setSkillPrice(String skillName) {
        if(skillPriceTextView == null)
            skillPriceTextView = (TextView)findViewById(R.id.skill_price_tv);
        skillPriceTextView.setText(skillName);
    }
}
