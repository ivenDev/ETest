package com.cloniamix.etest.presenter;

import com.cloniamix.etest.R;
import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.view.GroupSelActivity;
import com.cloniamix.etest.view.ResultActivity;
import com.cloniamix.etest.view.QuestionActivity;
import com.cloniamix.etest.view.TicketSelActivity;

public class PresenterOfResult extends MainPresenter<ResultActivity> {

    private ResultActivity mView;

    public PresenterOfResult(ResultActivity view){
        super(view);
        this.mView = super.mView;

    }

    public void onCreated(int groupNum, int ticketNum){

        int quantityOfCorrect = 0;
        int quantityOfInCorrect = 0;
        for (Question question : mModel.getQuestions(groupNum, ticketNum)){

            if (question.isCorrect()){
                quantityOfCorrect++;
            }else {
                quantityOfInCorrect++;
            }
        }

        mView.setQuantityOfCorrectAnswerText(R.string.quantityOfCorrectAnswersText,quantityOfCorrect);
        mView.setQuantityOfIncorrectAnswerText(R.string.quantityOfIncorrectAnswersText,quantityOfInCorrect);
    }
    public void onBtnClicked(int resId){

        switch (resId){
            case R.id.homeBtn:
                this.mView.goToActivity(GroupSelActivity.class);
                break;

            case R.id.repeatBtn:
                this.mView.goToActivity(QuestionActivity.class);
                break;

            case R.id.ticketSelBtn:
                this.mView.goToActivity(TicketSelActivity.class);
                break;
        }
    }
}
