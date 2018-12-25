package com.cloniamix.etest.mvp.presenters;

import com.cloniamix.etest.Contract;
import com.cloniamix.etest.model.Model;

public abstract class MainPresenter <V extends Contract.View> implements Contract.Presenter<V> {

    V mView;
    Model mModel;

    public MainPresenter(V view) {
        mModel = new Model();
        attachView(view);

    }

    @Override
    public void attachView(V view) {
        mView = view;

    }

    @Override
    public void detachView() {
        mView =null;
    }
}
