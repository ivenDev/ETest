package com.cloniamix.etest.pojo;


public class Answer {

    private String answerText;
    private boolean isTrueOrFalse;

    //region getters & setters
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isTrueOrFalse() {
        return isTrueOrFalse;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        isTrueOrFalse = trueOrFalse;
    }
    //endregion
}
