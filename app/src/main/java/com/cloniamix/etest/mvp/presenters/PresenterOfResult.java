package com.cloniamix.etest.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.cloniamix.etest.R;
import com.cloniamix.etest.model.Model;
import com.cloniamix.etest.mvp.views.ResultView;
import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.pojo.Ticket;
import com.cloniamix.etest.ui.GroupSelActivity;
import com.cloniamix.etest.ui.QuestionSelActivity;
import com.cloniamix.etest.ui.QuestionActivity;
import com.cloniamix.etest.ui.TicketSelActivity;
import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class PresenterOfResult extends MvpPresenter<ResultView> {

    private Model mModel;

    private int mGroupNum;
    private int mTicketNum;
    private int mMode;

    private List<Question> mQuestionUsedList;

    public PresenterOfResult(){
        mModel = new Model();
        mQuestionUsedList = new ArrayList<>();

    }

    public void onStarted(){
        updateView();
    }

    public void onBtnClicked(int resId){
        mModel.resetQuestionLocalUsed(mQuestionUsedList);
        switch (resId){

            case R.id.home_btn:
                getViewState().goToActivity(GroupSelActivity.class);
                break;

            case R.id.repeat_btn:
                getViewState().goToActivity(QuestionActivity.class);
                break;

            case R.id.ticket_sel_btn:
                if (mMode == 1){
                    //режим всех вопросов группы
                    getViewState().goToActivity(QuestionSelActivity.class);
                }
                if (mMode == 2){
                    //режим повотрения билетов
                    getViewState().goToActivity(TicketSelActivity.class);
                }
        /*if (mMode == 3){
            // режим экзамена

        }*/

                break;
        }
    }

    private void updateView(){

        mQuestionUsedList.clear();

        int quantityOfCorrect = 0;
        int quantityOfInCorrect = 0;

        if (mMode == 1){

            getViewState().changeBtns();
            //режим всех вопросов группы
            for (Question question : mModel.getGroupQuestions(mGroupNum)){

                if (question.isLocalUsed()) {
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
            Ticket ticket = mModel.getTicket(mGroupNum,mTicketNum);
            quantityOfCorrect = ticket.getCorrectAnsweredCount();
            quantityOfInCorrect = ticket.getQuestionsNumList().size() - quantityOfCorrect;


        }
        /*if (mMode == 3){
            // режим экзамена

        }*/



        getViewState().setQuantityOfCorrectAnswerText(R.string.quantityOfCorrectAnswersText,quantityOfCorrect);
        getViewState().setQuantityOfIncorrectAnswerText(R.string.quantityOfIncorrectAnswersText,quantityOfInCorrect);
    }

    //region getters & setters
    public int getGroupNum() {
        return mGroupNum;
    }

    public void setGroupNum(int groupNum) {
        mGroupNum = groupNum;
    }

    public int getTicketNum() {
        return mTicketNum;
    }

    public void setTicketNum(int ticketNum) {
        mTicketNum = ticketNum;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mode) {
        mMode = mode;
    }
    //endregion
}
