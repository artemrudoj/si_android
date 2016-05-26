package com.wintersportcoaches.common.base.presenter;

import android.os.Bundle;

import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.service.BindedServiceFragment;

/**
 * Created by artem on 27.05.16.
 */
public abstract class PresenteredFragment extends BindedServiceFragment {

    protected BasePresenter presenter;


    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        presenter.unbindView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(presenter != null) PresenterManager.getInstance().savePresenter(presenter, outState);
    }
}
