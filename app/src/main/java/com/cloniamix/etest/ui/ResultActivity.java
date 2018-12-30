package com.cloniamix.etest.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.cloniamix.etest.R;
import com.cloniamix.etest.mvp.presenters.PresenterOfResult;
import com.cloniamix.etest.mvp.views.ResultView;

public class ResultActivity extends MvpAppCompatActivity implements ResultView {

    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); если активность уже есть в стеке
    // ,то она вызывается,все над ней в стеке закрываются

    @InjectPresenter
    PresenterOfResult mPresenterOfResult;

    private TextView mQuantityOfCorrectAnswerText;
    private TextView mQuantityOfIncorrectAnswerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mPresenterOfResult.setGroupNum(getIntent().getIntExtra("groupNum",0));
        mPresenterOfResult.setTicketNum(getIntent().getIntExtra("ticketNum",0));
        mPresenterOfResult.setMode(getIntent().getIntExtra("mode", 0));

        mQuantityOfCorrectAnswerText = findViewById(R.id.quantityCorrectAnswersTextView);
        mQuantityOfIncorrectAnswerText = findViewById(R.id.quantityIncorrectAnswersTextView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenterOfResult.onStarted();
    }

    @Override
    public void setQuantityOfCorrectAnswerText(int resId, int quantityOfCorrect){
        String text = getResources().getString(resId,quantityOfCorrect);
        mQuantityOfCorrectAnswerText.setText(text);
    }

    @Override
    public void setQuantityOfIncorrectAnswerText(int resId, int quantityOfIncorrect){
        String text = getResources().getString(resId,quantityOfIncorrect);
        mQuantityOfIncorrectAnswerText.setText(text);
    }

    @Override
    public void onMyBtnClick(View v) {
        mPresenterOfResult.onBtnClicked(v.getId());
    }

    @Override
    public void changeBtns(){
        /*Button homeBtn = findViewById(R.id.home_btn);
        Button repeatBtn = findViewById(R.id.repeat_btn);*/
        Button ticketSelBtn = findViewById(R.id.ticket_sel_btn);

        ticketSelBtn.setText("К выбору вопроса");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToActivity(ModeSelActivity.class);
        /*Intent intent = new Intent(this,TicketSelActivity.class);
        intent.putExtra("groupNum",mGroupNum);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @Override
    public void goToActivity() {


    }

    public void goToActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        intent.putExtra("groupNum",mPresenterOfResult.getGroupNum());
        intent.putExtra("ticketNum",mPresenterOfResult.getTicketNum());
        intent.putExtra("mode",mPresenterOfResult.getMode());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void setTitle() {

    }
}
