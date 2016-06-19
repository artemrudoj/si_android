package com.wintersportcoaches.pupil.lesson.create;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.login.LoginActivity;
import com.wintersportcoaches.common.model.Lesson;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.ui.tools.DatePickerFragment;
import com.wintersportcoaches.common.ui.views.CircleButtonWithText;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.lesson.list.LessonListActivity;
import com.wintersportcoaches.pupil.ui.view.ChooseCoachView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by artem on 29.05.16.
 */
public class CreateLessonFragment extends PresenteredFragment implements CreateLessonView {

    CircleButtonWithText skiCircleButtonWithText;
    CircleButtonWithText snowboardCircleButtonWithText;

    private static final int REQUEST_DATE = 1234 ;
    ChooseCoachView chooseCoachView;
    TextView dateTextView;
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
        dateTextView = (TextView)view.findViewById(R.id.date_tv);
//        view.findViewById(R.id.time_icwht).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TimePickerDialog dialog =  createTimePickerDialog(null);
//                dialog.show();
//            }
//        });
        chooseCoachView = (ChooseCoachView)view.findViewById(R.id.choise_coach_ccv);
        skiCircleButtonWithText = (CircleButtonWithText)view.findViewById(R.id.ski_cbwt);
        snowboardCircleButtonWithText = (CircleButtonWithText)view.findViewById(R.id.snowboard_cbwt);
        view.findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CreateLessonPresenter)presenter).createLessonPressed();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            String reportDate = df.format(date);
            setDate(reportDate);
        }
    }

    void setDate(String date) {
        dateTextView.setVisibility(View.VISIBLE);
        dateTextView.setText(date);
    }

    @Override
    public String getHash() {
        Activity activity = getActivity();
        if(activity != null) {
            BaseUser user = ((WinterSportCoachesApplication)activity.getApplication()).getUser();
            if (user.getHash().equals("")) LoginActivity.go(activity);
            else return user.getHash();
        }
        return null;
    }

    @Override
    public String getPlace() {
        return "Роза хутор";
    }

    @Override
    public int getType() {
        if(snowboardCircleButtonWithText.isChecked()) return Lesson.SNOWBOARD;
        else  if(skiCircleButtonWithText.isChecked()) return Lesson.SKI;
        else throw new IllegalStateException("type must be chosen");
    }

    @Override
    public String getStartTime() {
        return dateTextView.getText().toString();
    }

    @Override
    public String getCoach() {
        return null;
    }

    @Override
    public void successCreated() {
        Activity activity = getActivity();
        if(activity != null) {
            Intent intent = new Intent(activity, LessonListActivity.class);
            activity.startActivity(intent);
        }
    }

    @Override
    public boolean isValidate() {
        return getPlace() != null && getHash() != null && getStartTime() != null && !getStartTime().equals("");
    }

    @Override
    public void showLoading() {

    }
}
