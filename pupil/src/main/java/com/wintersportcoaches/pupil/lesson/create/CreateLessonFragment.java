package com.wintersportcoaches.pupil.lesson.create;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.ui.tools.DatePickerFragment;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.ui.view.ChooseCoachView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by artem on 29.05.16.
 */
public class CreateLessonFragment extends PresenteredFragment implements CreateLessonView {

    private static final int REQUEST_DATE = 1234 ;
    ChooseCoachView chooseCoachView;
    public void onNewIntent(Intent intent) {
        if(intent.hasExtra(BaseUser.EXTRA_USER)) {
            BaseUser user = new BaseUser(intent.getExtras().getBundle(BaseUser.EXTRA_USER));
            chooseCoachView.addUser(user);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_lesson, container, false);
        if (savedInstanceState == null) {
            presenter = new CreateLessonPresenter(NetworkServiceFactory.getNetworkService());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
        initViews(view);

        return view;
    }


    TimePickerDialog createTimePickerDialog(TimePickerDialog.OnTimeSetListener listener) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), listener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    private void initViews(View view) {
        view.findViewById(R.id.date_icwht).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(new Date());
                dialog.setTargetFragment(CreateLessonFragment.this, REQUEST_DATE);
                dialog.show(manager, null);
            }
        });
//        view.findViewById(R.id.time_icwht).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TimePickerDialog dialog =  createTimePickerDialog(null);
//                dialog.show();
//            }
//        });
        chooseCoachView = (ChooseCoachView)view.findViewById(R.id.choise_coach_ccv);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
        }
    }

    @Override
    public String getHash() {
        return null;
    }

    @Override
    public String getPlace() {
        return "Рощза хутор";
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public String getStartTime() {
        return null;
    }

    @Override
    public String getCoach() {
        return null;
    }

    @Override
    public void successCreated() {

    }

    @Override
    public boolean isValidate() {
        return false;
    }

    @Override
    public void showLoading() {

    }
}
