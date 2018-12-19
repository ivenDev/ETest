package com.cloniamix.etest.presenter;

import com.cloniamix.etest.R;
import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.pojo.Ticket;
import com.cloniamix.etest.view.GroupSelActivity;
import com.cloniamix.etest.view.QuestionSelActivity;
import com.cloniamix.etest.view.ResultActivity;
import com.cloniamix.etest.view.QuestionActivity;
import com.cloniamix.etest.view.TicketSelActivity;

import java.util.ArrayList;
import java.util.List;

public class PresenterOfResult extends MainPresenter<ResultActivity> {

    private ResultActivity mView;

    private int mMode;
    private List<Question> mQuestionUsedList;

    public PresenterOfResult(ResultActivity view){
        super(view);
        this.mView = super.mView;
        mQuestionUsedList = new ArrayList<>();
    }

    public void onCreated(int groupNum, int ticketNum, int mode){

        mMode = mode;
        mQuestionUsedList.clear();

        int quantityOfCorrect = 0;
        int quantityOfInCorrect = 0;

        if (mMode == 1){

            mView.changeBtns();
            //режим всех вопросов группы
            for (Question question : mModel.getGroupQuestions(groupNum)){

                if (question.isUsed()) {
                    if (question.isCorrect()){
                        quantityOfCorrect++;
                    }else {
                        quantityOfInCorrect++;
                    }
                    mQuestionUsedList.add(question);
                }
            }
        }
        if (mMode == 2){
            //режим повотрения билетов
            Ticket ticket = mModel.getTicket(groupNum,ticketNum);
            quantityOfCorrect = ticket.getCorrectAnsweredCount();
            quantityOfInCorrect = ticket.getQuestionsNumList().size() - quantityOfCorrect;


        }
        /*if (mMode == 3){
            // режим экзамена

        }*/



        mView.setQuantityOfCorrectAnswerText(R.string.quantityOfCorrectAnswersText,quantityOfCorrect);
        mView.setQuantityOfIncorrectAnswerText(R.string.quantityOfIncorrectAnswersText,quantityOfInCorrect);
    }
    public void onBtnClicked(int resId){
        mModel.resetQuestionUsed(mQuestionUsedList);
        switch (resId){

            case R.id.home_btn:
                this.mView.goToActivity(GroupSelActivity.class);
                break;

            case R.id.repeat_btn:
                this.mView.goToActivity(QuestionActivity.class);
                break;

            case R.id.ticket_sel_btn:
                if (mMode == 1){
                    //режим всех вопросов группы
                    this.mView.goToActivity(QuestionSelActivity.class);
                }
                if (mMode == 2){
                    //режим повотрения билетов
                    this.mView.goToActivity(TicketSelActivity.class);
                }
        /*if (mMode == 3){
            // режим экзамена

        }*/

                break;
        }
    }
}
