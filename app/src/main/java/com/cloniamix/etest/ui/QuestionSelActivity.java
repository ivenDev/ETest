package com.cloniamix.etest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cloniamix.etest.R;
import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.mvp.presenters.PresenterOfSelections;

import java.util.List;

public class QuestionSelActivity extends Activity<PresenterOfSelections> {

    private int mGroupNum;
    private int mQuestionNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_selection);

        mPresenter = new PresenterOfSelections(this);
        mGroupNum = getIntent().getIntExtra("groupNum", 2);
        setTitle("Группа " + mGroupNum);

        List<Question> questionList = mPresenter.getQuestions(mGroupNum);
        int numberOfQuestions = questionList.size();
        /*if (questionList != null){*/

            GridLayout gridLayout = findViewById(R.id.question_selection_buttons_container);
            gridLayout.setColumnCount(4);

            if (numberOfQuestions != 0) {
                for (int i = 1; i <= numberOfQuestions; i++) {
                    GridLayout.LayoutParams buttonParam = new GridLayout
                            .LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f),
                            GridLayout.spec(GridLayout.UNDEFINED, 1f));
                    buttonParam.width = 0;

                    final int a = i;
                    final Button button = new Button(this);

                    String btnText = "" + i;
                    button.setText(btnText);
                    button.setId(i);
                    button.setBackgroundColor(getResources().getColor(R.color.colorButtons));
                    button.setAllCaps(false);
                    button.setLayoutParams(buttonParam);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mQuestionNum = a;
                            mPresenter.selectQuestion();
                        }
                    });

                    gridLayout.addView(button);
                }
            } else {
                TextView textView = new TextView(this);
                GridLayout.LayoutParams textParam = new GridLayout
                        .LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f),
                        GridLayout.spec(GridLayout.UNDEFINED, 1f));
                /*textParam.width = 0;*/
                textParam.setGravity(Gravity.CENTER);
                textView.setLayoutParams(textParam);
                textView.setText(R.string.no_data_text);
                gridLayout.addView(textView);
            }
        /*}else {
            mPresenter.receivedADataErr();
        }*/
    }

    @Override
    public void goToActivity(){
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("groupNum",mGroupNum);
        intent.putExtra("questionNum",mQuestionNum);
        intent.putExtra("mode", 1);
        startActivity(intent);

    }
}
