package com.cloniamix.etest.pojo;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int questionId;
    private int questionNum;
    private String questionText;
    private boolean isCorrect;
    private boolean isUsed;
    private List<Answer> answers;

    //region getters & setters


    public Question() {
    }

    /*public Question(int questionId, String questionText, boolean isCorrect, List<Answer> answers) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.isCorrect = isCorrect;
        this.answers = answers;
    }*/

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    //endregion


    public List<String> getAnswersInString(){
        List<String> stringAnswers = new ArrayList<>();
        for (int i = 0; i< getAnswers().size(); i++){
            stringAnswers.add(getAnswers().get(i).getAnswerText());
        }
        return stringAnswers;
    }

}
