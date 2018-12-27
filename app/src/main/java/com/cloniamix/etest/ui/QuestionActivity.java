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

   /* private int mGroupNum;
    private int mTicketNum;
    private int mQuestionNum;

    private int mMode;

    private TextView mQuestionTextView;
    private LinearLayout mContainerForAnswers;
    private LinearLayout.LayoutParams lp;*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        mPresenterOfTest.


        if (savedInstanceState == null){
            mQuestionNum = getIntent().getIntExtra("questionNum",1);
            mGroupNum = getIntent().getIntExtra("groupNum",3);
            mTicketNum = getIntent().getIntExtra("ticketNum",0);

            mMode = getIntent().getIntExtra("mode", 0);
        }else {
            mQuestionNum = savedInstanceState.getInt("questionNum");
            mGroupNum = savedInstanceState.getInt("groupNum");
            mTicketNum = savedInstanceState.getInt("ticketNum");

            mMode = savedInstanceState.getInt("mode");
        }



        mPresenter = new PresenterOfTest(this,mGroupNum, mTicketNum);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mContainerForAnswers = findViewById(R.id.container_for_answer_buttons);

        mPresenter.onCreated(mQuestionNum, mMode);

    }

    public void setQuestionNum(int questionNum){
        mQuestionNum = questionNum;
    }

    public void setTicketNum(int ticketNum){
        mTicketNum = ticketNum;
    }


    public void setTitle(){
        if (mTicketNum != 0){
            setTitle("Гр" + mGroupNum + " Б" + mTicketNum + " Вопрос " + mQuestionNum);
        }else {
            setTitle("Гр" + mGroupNum + " Вопрос " + mQuestionNum);
        }

    }

    public void showQuestionText(String questionText){
        mQuestionTextView.setText(questionText);

    }

    public void showAnswers(List<String> answers){
        mContainerForAnswers = (LinearLayout) findViewById(R.id.container_for_answer_buttons);
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

                    mPresenter.onAnswerClicked(button.getText().toString());
                }
            });
            mContainerForAnswers.addView(button);
        }

    }

    public void showCorrectAnswer(String yourAnswer, String correctAnswer){

        mContainerForAnswers.removeAllViews();
        TextView yourAnswerView = new TextView(this);
        yourAnswerView.setTextColor(getResources().getColor(R.color.colorIncorrectText));
        yourAnswerView.setText(getResources().getString(R.string.answerText,yourAnswer) );
        yourAnswerView.setTextSize(16);
        mContainerForAnswers.addView(yourAnswerView);

        TextView correctAnswerView = new TextView(this);
        correctAnswerView.setTextColor(getResources().getColor(R.color.colorCorrectText));
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
                mPresenter.updateView();
            }
        });

        mContainerForAnswers.addView(button);
    }


    @Override
    public void goToActivity() {

        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("groupNum",mGroupNum);
        intent.putExtra("ticketNum",mTicketNum);
        intent.putExtra("mode",mMode);
        startActivity(intent);
    }

    @Override
    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        intent.putExtra("groupNum",mGroupNum);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenter.onBack();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("questionNum",mQuestionNum);
        outState.putInt("groupNum",mGroupNum);
        outState.putInt("ticketNum",mTicketNum);
        outState.putInt("mode",mMode);
    }


}