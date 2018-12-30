package com.cloniamix.etest.mvp.views;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {
    void showToast(String message);
    void goToActivity();

    void setTitle();
}
