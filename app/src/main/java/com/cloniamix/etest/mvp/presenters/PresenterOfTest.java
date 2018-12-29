package com.cloniamix.etest.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.cloniamix.etest.model.Model;
import com.cloniamix.etest.mvp.views.QuestionView;
import com.cloniamix.etest.pojo.Answer;
import com.cloniamix.etest.pojo.Question;
/*import com.cloniamix.etest.pojo.Ticket;*/
import com.cloniamix.etest.ui.QuestionActivity;
import com.cloniamix.etest.ui.QuestionSelActivity;
import com.cloniamix.etest.ui.TicketSelActivity;

import java.util.List;

@InjectViewState
public class PresenterOfTest extends MvpPresenter<QuestionView> {

    private int mGroupNum;
    private int mTicketNum;
    private int mQuestionNum;
    private int mMode;

    private Model mModel;

    private Question mQuestion;
    private List<Question> mQuestions;

    /*private Ticket mTicket;*/


    public PresenterOfTest(){
        mModel = new Model();

    }

    public void nextBtnClicked(){
        updateView();
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getData();
        updateData();
    }

    public void onAnswerClicked(String answerText){

        String correctAnswerText = "";
        List<Answer> answers = mQuestion.getAnswers();
        for (Answer answer : answers){
            if (answer.isTrueOrFalse()){
                correctAnswerText = answer.getAnswerText();
            }
        }
        mQuestionNum++;
        mQuestion.setUsed(true);

        if (answerText.equals(correctAnswerText)){
            mQuestion.setCorrect(true);
            if (mMode == 1){
                //режим всех вопросов группы
                mModel.updateQuestionInDB(mQuestion);
            }
            if (mMode == 2){
                //режим повотрения билетов
                mModel.updateTicketInDB(mQuestion, mGroupNum, mTicketNum);
            }
        /*if (mMode == 3){
            // режим экзамена

        }*/

            updateView();

        } else {
            mQuestion.setCorrect(false);
            if (mMode == 1){
                //режим всех вопросов группы
                mModel.updateQuestionInDB(mQuestion);
            }
            if (mMode == 2){
                //режим повотрения билетов
                mModel.updateTicketInDB(mQuestion, mGroupNum, mTicketNum);
            }
        /*if (mMode == 3){
            // режим экзамена

        }*/
            getViewState().showCorrectAnswer(answerText,correctAnswerText);
        }

    }

    private void updateView(){

        if (mQuestionNum <= mQuestions.size() ) {
            mQuestion = mQuestions.get(mQuestionNum-1);
            String questionText = mQuestion.getQuestionText();
            getViewState().setTitle();
            getViewState().showQuestionText(questionText);
            List<String> answers = mQuestion.getAnswersInString();
            getViewState().showAnswers(answers);


        }else {
            getViewState().goToActivity();
        }

    }

    private void updateData(){
        if (mMode == 1){
            //режим всех вопросов группы
            mQuestions = mModel.getGroupQuestions(mGroupNum);
        }
        if (mMode == 2){
            //режим повотрения билетов
            mQuestions = mModel.getTicketQuestions(mGroupNum, mTicketNum);
        }
        /*if (mMode == 3){
            // режим экзамена

        }*/

        updateView();
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

    public int getQuestionNum() {
        return mQuestionNum;
    }

    public void setQuestionNum(int questionNum) {
        mQuestionNum = questionNum;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mode) {
        mMode = mode;
    }
    //endregion
}

/*public void onBack(){
        if (mMode == 1){
            //режим всех вопросов группы
            mView.goToActivity(QuestionSelActivity.class);
        }
        if (mMode == 2){
            //режим повотрения билетов
            mView.goToActivity(TicketSelActivity.class);
        }
        *//*if (mMode == 3){
            // режим экзамена

        }*//*
    }*/
