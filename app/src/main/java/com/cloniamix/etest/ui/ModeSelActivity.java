package com.cloniamix.etest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.cloniamix.etest.R;
import com.cloniamix.etest.mvp.presenters.PresenterOfSelections;
import com.cloniamix.etest.mvp.views.SelView;

public class ModeSelActivity extends MvpAppCompatActivity implements SelView {

    @InjectPresenter
    PresenterOfSelections mPresenterOfSelections;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);
        int groupNum = getIntent().getIntExtra("groupNum",0);
        mPresenterOfSelections.setGroupNum(groupNum);
    }

    @Override
    public void setTitle() {
        setTitle("Группа " + mPresenterOfSelections.getGroupNum());
    }

    @Override
    public void btnClick(View v) {
        mPresenterOfSelections.modeSelBtnClicked(v.getId());
    }

    @Override
    public void goToActivity() {

        if (mPresenterOfSelections.getMode() != 3) {
            Intent intent = new Intent(this, isQuestionMode() ? QuestionSelActivity.class
                    : TicketSelActivity.class);
            intent.putExtra("groupNum", mPresenterOfSelections.getGroupNum());
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("mode", 3);
            startActivity(intent);
        }

        /*Intent intent;
        if (mPresenterOfSelections.getMode() == 1){
            intent = getIntent(QuestionSelActivity.class);
            startActivity(intent);
        }
        if (mPresenterOfSelections.getMode() == 2){
            intent = getIntent(TicketSelActivity.class);
            startActivity(intent);
        }
        if (mPresenterOfSelections.getMode() == 3){
            intent = getIntent(QuestionActivity.class);
            intent.putExtra("mode", 3);
            startActivity(intent);
        }*/
    }

    @Override
    public void showToast(String message) {

    }

    private boolean isQuestionMode(){
        return (mPresenterOfSelections.getMode() == 1);
    }

}
