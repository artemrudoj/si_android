package com.wintersportcoaches.common.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.artem.common.R;

/**
 * Created by artem on 22.06.16.
 */
public class MyProfileFragment  extends ProfileFragment{

    public static MyProfileFragment newInstance(Bundle args) {
        MyProfileFragment profileFragment = new MyProfileFragment();
        profileFragment.setArguments(args);
        return profileFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater, container, savedInstanceState);
        view.findViewById(R.id.send_message_container).setVisibility(View.INVISIBLE);
        ((ImageView)view.findViewById(R.id.plus_icon_iv)).setImageResource(R.drawable.vector_setting_ic);
        return view;
    }
}
