package com.cloniamix.etest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.cloniamix.etest.R;
import com.cloniamix.etest.mvp.presenters.PresenterOfTest;
import com.cloniamix.etest.mvp.views.QuestionView;

import java.util.List;

public class QuestionActivity extends MvpAppCompatActivity implements QuestionView {

    @InjectPresenter
    PresenterOfTest mPresenterOfTest;

    private TextView mQuestionTextView;
    private LinearLayout mContainerForAnswers;
    private LinearLayout.LayoutParams lp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        /*mPresenterOfTest.setGroupNum(getIntent().getIntExtra("groupNum",0));
        mPresenterOfTest.setTicketNum(getIntent().getIntExtra("ticketNum",0));
        mPresenterOfTest.setQuestionNum(getIntent().getIntExtra("questionNum",1));
        mPresenterOfTest.setMode(getIntent().getIntExtra("mode", 0));*/

        mQuestionTextView = findViewById(R.id.question_text_view);
        mContainerForAnswers = findViewById(R.id.container_for_answer_buttons);

        /*mPresenterOfTest.updateData();*/



    }


    @Override
    public void setTitle(){
        int groupNum = mPresenterOfTest.getGroupNum();
        int ticketNum = mPresenterOfTest.getTicketNum();
        int questionNum = mPresenterOfTest.getQuestionNum();
        if (ticketNum != 0){
            setTitle("Гр" + groupNum + " Б" + ticketNum + " Вопрос " + questionNum);
        }else {
            setTitle("Гр" + groupNum + " Вопрос " + questionNum);
        }
    }

    @Override
    public void showQuestionText(String questionText){
        mQuestionTextView.setText(questionText);

    }

    @Override
    public void showAnswers(List<String> answers){
        mContainerForAnswers.removeAllViews();
        lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        float dp = getResources().getDisplayMetrics().density;

        int bottomMargin = (int) dp * 12;
        lp.setMargins(0,0,0,bottomMargin);

        for (int i = 0; i < answers.size(); i++) {

            final Button button = new Button(this);
            button.setText(answers.get(i));
            button.setTextSize(16);
            button.setBackgroundColor(getResources().getColor(R.color.colorButtons));
            button.setAllCaps(false);
            button.setLayoutParams(lp);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mPresenterOfTest.onAnswerClicked(button.getText().toString());
                }
            });
            mContainerForAnswers.addView(button);
        }

    }

    @Override
    public void showCorrectAnswer(String yourAnswer, String correctAnswer){

        mContainerForAnswers.removeAllViews();
        TextView yourAnswerView = new TextView(this);
        yourAnswerView.setTextColor(getResources().getColor(R.color.colorIncorrect));
        yourAnswerView.setText(getResources().getString(R.string.answerText,yourAnswer) );
        yourAnswerView.setTextSize(16);
        mContainerForAnswers.addView(yourAnswerView);

        TextView correctAnswerView = new TextView(this);
        correctAnswerView.setTextColor(getResources().getColor(R.color.colorCorrect));
        correctAnswerView.setText(getResources().getString(R.string.correctAnswerText,correctAnswer));
        correctAnswerView.setTextSize(16);
        mContainerForAnswers.addView(correctAnswerView);

        Button button = new Button(this);
        button.setText(getResources().getString(R.string.nextButtonText));
        button.setAllCaps(false);
        button.setTextSize(16);
        button.setBackgroundColor(getResources().getColor(R.color.colorButtons));
        button.setLayoutParams(lp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenterOfTest.nextBtnClicked();
            }
        });

        mContainerForAnswers.addView(button);
    }


    @Override
    public void goToActivity() {

        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("groupNum",mPresenterOfTest.getGroupNum());
        intent.putExtra("ticketNum",mPresenterOfTest.getTicketNum());
        intent.putExtra("mode",mPresenterOfTest.getMode());
        startActivity(intent);


    }


    @Override
    public void goToBackActivity(){
        Intent intent;
        if (mPresenterOfTest.getMode() != 3) {
            intent = new Intent(this, isQuestionMode() ? QuestionSelActivity.class
                    : TicketSelActivity.class);

        }else {
            intent = new Intent(this, ModeSelActivity.class);
        }

        intent.putExtra("groupNum", mPresenterOfTest.getGroupNum());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void showToast(String message) {

    }

    public void getData(){
        mPresenterOfTest.setGroupNum(getIntent().getIntExtra("groupNum",0));
        mPresenterOfTest.setTicketNum(getIntent().getIntExtra("ticketNum",0));
        mPresenterOfTest.setQuestionNum(getIntent().getIntExtra("questionNum",1));
        mPresenterOfTest.setMode(getIntent().getIntExtra("mode", 0));
    }

    private boolean isQuestionMode(){
        return (mPresenterOfTest.getMode() == 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenterOfTest.onBack();
    }
}


/* @Override
    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        intent.putExtra("groupNum",mGroupNum);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }*/


