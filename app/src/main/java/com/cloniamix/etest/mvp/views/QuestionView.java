package com.cloniamix.etest.mvp.views;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpView;
import com.cloniamix.etest.R;

import java.util.List;

public interface QuestionView extends BaseView {
    void showQuestionText(String questionText);
    void showAnswers(List<String> answers);
    void showCorrectAnswer(String yourAnswer, String correctAnswer);

    void getData();
}
