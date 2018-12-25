package com.cloniamix.etest.mvp.views;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {
    void showToast(int resId);
    void goToActivity();
}
