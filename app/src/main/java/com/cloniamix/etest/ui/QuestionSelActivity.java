package com.cloniamix.etest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.cloniamix.etest.R;
import com.cloniamix.etest.mvp.views.SelView;
import com.cloniamix.etest.mvp.presenters.PresenterOfSelections;

public class QuestionSelActivity extends MvpAppCompatActivity implements SelView {

    @InjectPresenter
    PresenterOfSelections mPresenterOfSelections;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_selection);

        mPresenterOfSelections.setGroupNum(getIntent().getIntExtra("groupNum", 0));

        int numberOfQuestions = mPresenterOfSelections.getQuantityOfQuestions();

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
                            mPresenterOfSelections.setQuestionNum(a);
                            mPresenterOfSelections.questionSelBtnClicked();
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
    }

    @Override
    public void goToActivity(){
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("groupNum",mPresenterOfSelections.getGroupNum());
        intent.putExtra("questionNum",mPresenterOfSelections.getQuestionNum());
        intent.putExtra("mode", 1);
        startActivity(intent);

    }

    @Override
    public void btnClick(View view) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void setTitle() {

    }
}
