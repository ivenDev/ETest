package com.cloniamix.etest.presenter;

import com.cloniamix.etest.pojo.Answer;
import com.cloniamix.etest.pojo.Question;
/*import com.cloniamix.etest.pojo.Ticket;*/
import com.cloniamix.etest.view.QuestionActivity;
import com.cloniamix.etest.view.QuestionSelActivity;
import com.cloniamix.etest.view.TicketSelActivity;

import java.util.List;


public class PresenterOfTest extends MainPresenter<QuestionActivity> {

    private int mGroupNum;
    private int mTicketNum;
    private int mQuestionNum;

    private int mMode;

    private QuestionActivity mView;
    private Question mQuestion;
    private List<Question> mQuestions;

    /*private Ticket mTicket;*/


    public PresenterOfTest(QuestionActivity view, int groupNum, int ticketNum){
        super(view);

        mGroupNum = groupNum;
        mTicketNum = ticketNum;
        mQuestionNum = 1;
        mView = super.mView;
        /*mQuestions = mModel.getTicketQuestions(groupNum, ticketNum);*/
    }

    public void onBack(){
        if (mMode == 1){
            //режим всех вопросов группы
            mView.goToActivity(QuestionSelActivity.class);
        }
        if (mMode == 2){
            //режим повотрения билетов
            mView.goToActivity(TicketSelActivity.class);
        }
        /*if (mMode == 3){
            // режим экзамена

        }*/
    }

    public void onCreated(int questionNum, int mode){

        mMode = mode;

        mQuestionNum = questionNum;

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
            mView.showCorrectAnswer(answerText,correctAnswerText);
        }

    }

    public void updateView(){

        if (mQuestionNum <= mQuestions.size() ) {
            /*mTicket = mModel.getTicket(mGroupNum, mTicketNum);*/
            mQuestion = mQuestions.get(mQuestionNum-1);
            String questionText = mQuestion.getQuestionText();
            mView.setTitle();
            mView.setQuestionNum(mQuestionNum);
            mView.showQuestionText(questionText);
            List<String> answers = mQuestion.getAnswersInString();
            mView.showAnswers(answers);


        }else {
            mView.goToActivity();
        }

    }


}
