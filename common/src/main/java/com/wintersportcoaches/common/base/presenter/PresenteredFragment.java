package com.wintersportcoaches.common.base.presenter;

import android.os.Bundle;

import com.wintersportcoaches.common.base.BaseFragment;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.service.BindedServiceFragment;

/**
 * Created by artem on 27.05.16.
 */
public abstract class PresenteredFragment<T extends BasePresenter> extends BaseFragment {

    protected T presenter;


    @Override
    public void onResume() {
        super.onResume();
        if(presenter != null)
            presenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(presenter != null)
            presenter.unbindView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(presenter != null)
            PresenterManager.getInstance().savePresenter(presenter, outState);
    }

    public T getPresenter() {
        return presenter;
    }
}
