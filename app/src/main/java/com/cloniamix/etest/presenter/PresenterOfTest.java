package com.cloniamix.etest.presenter;

import com.cloniamix.etest.pojo.Answer;
import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.pojo.Ticket;
import com.cloniamix.etest.view.QuestionActivity;

import java.util.List;


public class PresenterOfTest extends MainPresenter<QuestionActivity> {

    private int mGroupNum;
    private int mTicketNum;
    private int mQuestionNum;
    private QuestionActivity mView;
    private Question mQuestion;

    private Ticket mTicket;


    public PresenterOfTest(QuestionActivity view, int groupNum, int ticketNum){
        super(view);

        mGroupNum = groupNum;
        mTicketNum = ticketNum;
        mQuestionNum = 1;
        mView = super.mView;
        mQuestion = new Question();
    }



    public void onCreated(int questionNum){
        mQuestionNum = questionNum;
        updateView();
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
        mView.setQuestionNum(mQuestionNum);

        if (answerText.equals(correctAnswerText)){
            mQuestion.setCorrect(true);
            mModel.updateDB(mQuestion, mGroupNum, mTicketNum);
            updateView();

        } else {
            mQuestion.setCorrect(false);
            mModel.updateDB(mQuestion, mGroupNum, mTicketNum);
            mView.showCorrectAnswer(answerText,correctAnswerText);
        }

    }

    public void updateView(){

        if (mQuestionNum <= mModel.getQuestions(mGroupNum,mTicketNum).size() ) {
            mTicket = mModel.getTicket(mGroupNum, mTicketNum);
            mQuestion = mModel.getQuestion(mGroupNum, mTicketNum, mQuestionNum);
            String questionText = mQuestion.getQuestionText();
            mView.setQuestionNum(mQuestionNum);
            mView.showQuestionText(questionText);
            List<String> answers = mQuestion.getAnswersInString();

            mView.showAnswers(answers);

        }else {
            mView.goToActivity();
        }

    }


}
