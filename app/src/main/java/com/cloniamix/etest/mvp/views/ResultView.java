package com.cloniamix.etest.mvp.views;

import android.view.View;

public interface ResultView extends BaseView {
    void setQuantityOfCorrectAnswerText(int resId, int quantityOfCorrect);
    void setQuantityOfIncorrectAnswerText(int resId, int quantityOfIncorrect);
    void onMyBtnClick(View v);
    void changeBtns();
    void goToActivity(Class<?> cls);
}
