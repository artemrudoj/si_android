package com.wintersportcoaches.common.ui.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.artem.common.R;
import com.wintersportcoaches.common.model.Skill;
import com.wintersportcoaches.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * Created by artem on 28.05.16.
 */
public class SkillsView extends CardView {
    public SkillsView(Context context) {
        super(context);
    }

    public SkillsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkillsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(List<Skill> skillsList) {
        LinearLayout skillsContainer = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        skillsContainer.setOrientation(LinearLayout.VERTICAL);
        skillsContainer.setLayoutParams(params);
        for(Skill skill : skillsList) {
            IconWithHeaderTextView iconWithHeaderTextView = addIconWithHeaderText(skill);
            skillsContainer.addView(iconWithHeaderTextView);
            for (Map.Entry<String, Integer> entry : skill.getSkillsWithPrices().entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                OneSkillEntry oneSkillEntry = addOneSkillEntry(key, value);
                skillsContainer.addView(oneSkillEntry);
            }
        }

        addView(skillsContainer);

    }

    private OneSkillEntry addOneSkillEntry(String key, Integer value) {
        OneSkillEntry oneSkillEntry = new OneSkillEntry(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        oneSkillEntry.setLayoutParams(params);
        oneSkillEntry.setSkillName(key);
        oneSkillEntry.setSkillPrice(Utils.fromIntPriceToString(value));
        return oneSkillEntry;
    }

    IconWithHeaderTextView addIconWithHeaderText(Skill skill) {
        IconWithHeaderTextView iconWithHeaderTextView = new IconWithHeaderTextView(getContext());
        iconWithHeaderTextView.setIconId(R.drawable.vector_chats_ic);
        iconWithHeaderTextView.setHeaderText(skill.getKindOfSport());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        iconWithHeaderTextView.setLayoutParams(params);
        return iconWithHeaderTextView;
    }

}
