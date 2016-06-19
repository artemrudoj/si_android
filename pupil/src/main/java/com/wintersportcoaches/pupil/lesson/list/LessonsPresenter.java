package com.wintersportcoaches.pupil.lesson.list;

import android.content.Intent;

import com.wintersportcoaches.common.base.recylverviewedfragment.BaseRecycledViewPresenter;
import com.wintersportcoaches.common.model.Lesson;
import com.wintersportcoaches.common.model.LessonStatus;
import com.wintersportcoaches.common.rest.service.NetworkService;
import com.wintersportcoaches.common.user.BaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 19.06.16.
 */
public class LessonsPresenter extends BaseRecycledViewPresenter<List<Lesson>,LessonsView> {
    NetworkService mNetworkService;
    BaseUser user;

    public LessonsPresenter(NetworkService networkService, BaseUser user) {
        this.mNetworkService = networkService;
        this.user = user;
    }
    @Override
    protected void loadData() {
        List<Integer> statuses = new ArrayList();
        statuses.add(LessonStatus.S_APPROVED);
        statuses.add(LessonStatus.S_WAITS_FOR_COACH);
        mNetworkService.lesson_get_by_statuses(user.getHash(), statuses, BaseUser.LEARNER).enqueue(mLoadDataCallback);
    }
}
