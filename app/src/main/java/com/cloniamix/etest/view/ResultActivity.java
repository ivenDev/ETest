package com.cloniamix.etest.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cloniamix.etest.R;
import com.cloniamix.etest.presenter.PresenterOfResult;

public class ResultActivity extends Activity<PresenterOfResult> {

    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); если активность уже есть в стеке
    // ,то она вызывается,все над ней в стеке закрываются

    private int mGroupNum;
    private int mTicketNum;

    private int mMode;

    private TextView mQuantityOfCorrectAnswerText;
    private TextView mQuantityOfIncorrectAnswerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mPresenter = new PresenterOfResult(this);
        mGroupNum = getIntent().getIntExtra("groupNum",0);
        mTicketNum = getIntent().getIntExtra("ticketNum",0);
        mMode = getIntent().getIntExtra("mode", 0);

        mQuantityOfCorrectAnswerText = findViewById(R.id.quantityCorrectAnswersTextView);
        mQuantityOfIncorrectAnswerText = findViewById(R.id.quantityIncorrectAnswersTextView);

        
        mPresenter.onCreated(mGroupNum, mTicketNum,mMode);

    }

    public void setQuantityOfCorrectAnswerText(int resId, int quantityOfCorrect){
        String text = getResources().getString(resId,quantityOfCorrect);
        mQuantityOfCorrectAnswerText.setText(text);
    }

    public void setQuantityOfIncorrectAnswerText(int resId, int quantityOfIncorrect){
        String text = getResources().getString(resId,quantityOfIncorrect);
        mQuantityOfIncorrectAnswerText.setText(text);
    }

    public void onClick(View v) {
        mPresenter.onBtnClicked(v.getId());
    }

    public void changeBtns(){
        /*Button homeBtn = findViewById(R.id.home_btn);
        Button repeatBtn = findViewById(R.id.repeat_btn);*/
        Button ticketSelBtn = findViewById(R.id.ticket_sel_btn);

        ticketSelBtn.setText("К выбору вопроса");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToActivity(TicketSelActivity.class);
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
        intent.putExtra("groupNum",mGroupNum);
        intent.putExtra("ticketNum",mTicketNum);
        intent.putExtra("mode",mMode);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter = null;
        mQuantityOfCorrectAnswerText = null;
        mQuantityOfIncorrectAnswerText = null;
        /*mTicketNum = 0;
        mGroupNum = 0;*/
    }
}
