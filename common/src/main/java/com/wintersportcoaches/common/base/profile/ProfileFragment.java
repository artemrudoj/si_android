package com.wintersportcoaches.common.base.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artem.common.R;
import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.base.BaseFragment;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.model.Skill;
import com.wintersportcoaches.common.registration.LoginPresenter;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.ui.FragmentProgressBarHelper;
import com.wintersportcoaches.common.ui.views.SkillsView;
import com.wintersportcoaches.common.user.BaseUser;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by artem on 28.05.16.
 */
public class ProfileFragment extends PresenteredFragment<ProfilePresenter> implements ProfileView{
    int userId;
    SkillsView skillsView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.main_profile_layout, container, false);
        userId = 12;
        if (savedInstanceState == null) {
            presenter = new ProfilePresenter(NetworkServiceFactory.getNetworkService(), userId);
            presenter.setModel(((UserActivity)getActivity()).getUser());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        skillsView = (SkillsView)view.findViewById(R.id.skill_sv);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showUser(BaseUser baseUser) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("для новичков", 1000);
        Skill skill = new Skill("Сноуборд", hashMap);
        hashMap.put("продвинутый уровень", 12000);
        Skill skil2l = new Skill("Горные лыжи", hashMap);
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill);
        skills.add(skil2l);
        skillsView.initView(skills);
    }
}
