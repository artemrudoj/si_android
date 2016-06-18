package com.wintersportcoaches.pupil.lesson.create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.pupil.R;

import java.util.Calendar;

/**
 * Created by artem on 29.05.16.
 */
public class CreateLessonFragment extends PresenteredFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_lesson, container, false);
        if (savedInstanceState == null) {
            presenter = new CreateLessonPresenter();
           // presenter.setModel(((UserActivity)getActivity()).getUser());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
        initViews(view);

        return view;
    }




    DatePickerDialog createDatePickerDialog(DatePickerDialog.OnDateSetListener listener) {
        final Calendar c = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
               listener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        return datePickerDialog;
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
                DatePickerDialog dialog =  createDatePickerDialog(null);
                dialog.show();
            }
        });
        view.findViewById(R.id.time_icwht).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog =  createTimePickerDialog(null);
                dialog.show();
            }
        });
    }
}
