package com.cloniamix.etest.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

public interface QuestionView extends BaseView {
    void showQuestionText(String questionText);
    void showAnswers(List<String> answers);
    void showCorrectAnswer(String yourAnswer, String correctAnswer);
    void goToBackActivity();

    @StateStrategyType(SkipStrategy.class)
    void getData();
}
