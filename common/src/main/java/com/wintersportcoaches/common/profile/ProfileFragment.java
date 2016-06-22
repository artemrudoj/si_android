package com.wintersportcoaches.common.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artem.common.R;
import com.squareup.picasso.Picasso;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.model.Skill;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.ui.views.SkillsView;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.FileUtils;
import com.wintersportcoaches.common.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by artem on 28.05.16.
 */
public class ProfileFragment extends PresenteredFragment<ProfilePresenter> implements ProfileView{
    int userId;
    final public  static String EXTRA_USER_ID = "ProfileFragment.userId";
    SkillsView skillsView;
    TextView fullNameTextView;
    TextView ageTextView;
    TextView genderTextView;
    ImageView photo;
    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(args);
        return profileFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getArguments().getInt(EXTRA_USER_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.main_profile_layout, container, false);
        if (savedInstanceState == null) {
            presenter = new ProfilePresenter(NetworkServiceFactory.getNetworkService(), userId);
            presenter.setUserId(userId);
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
        initViews(view);

        return view;
    }

    private void initViews(View view)
    {
        skillsView = (SkillsView)view.findViewById(R.id.skill_sv);
        fullNameTextView = (TextView)view.findViewById(R.id.full_name_tv);
        ageTextView = (TextView)view.findViewById(R.id.age_tv);
        genderTextView = (TextView)view.findViewById(R.id.gender_tv);
        photo = (ImageView)view.findViewById(R.id.profile_image_civ);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showUser(BaseUser baseUser) {
        fullNameTextView.setText(baseUser.getFullName());
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("для новичков", 1000);
        Skill skill = new Skill("Сноуборд", hashMap);
        hashMap.put("продвинутый уровень", 12000);
        Skill skil2l = new Skill("Горные лыжи", hashMap);
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill);
        skills.add(skil2l);
        skillsView.initView(skills);
        NetworkUtils.loadProfileImage(baseUser.getPhotoUrlWithServerUrl(), photo, null, null, getActivity());
    }
}
