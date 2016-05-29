package com.wintersportcoaches.common.base.recylverviewedfragment;

import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.service.BindedServiceFragment;
import com.wintersportcoaches.common.ui.FragmentProgressBarHelper;

import java.util.List;

/**
 * Created by artem on 27.05.16.
 */
public abstract class RecyclerViewedFragment extends BindedServiceFragment implements RecycledBaseView {
    protected BaseRecyclerViewAdapter mAdapter;
    protected FragmentProgressBarHelper mFragmentProgressBarHelper;

    @Override
    public void showLoading() {
        mFragmentProgressBarHelper.beginAnimation();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void stopLoading() {
        mFragmentProgressBarHelper.endAnimation();
    }

    @Override
    public void showData(List data) {
        mAdapter.clearAndAddAll(data);
    }
}
