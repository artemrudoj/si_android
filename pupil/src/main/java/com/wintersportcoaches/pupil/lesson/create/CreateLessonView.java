package com.wintersportcoaches.pupil.lesson.create;

/**
 * Created by artem on 29.05.16.
 */
public interface CreateLessonView {
    String getHash();
    String getPlace();
    int getType();
    String getStartTime();
    String getCoach();
    void successCreated();

    boolean isValidate();

    void showLoading();

}
