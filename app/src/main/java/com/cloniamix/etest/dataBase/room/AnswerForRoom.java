package com.cloniamix.etest.dataBase.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity/*(foreignKeys = @ForeignKey(entity = QuestionForRoom.class, parentColumns = "questionId"
        ,childColumns = "idFromQuestion"  , onDelete = CASCADE))*/
public class AnswerForRoom {

    @PrimaryKey(autoGenerate = true)
    private int answerId;
    private int groupNum;
    private String answerText;
    private int idFromQuestion;
    private boolean isTrueOrFalse;

    //region constructor, getters & setters

    public AnswerForRoom() {
    }

    /*public AnswerForRoom(int answerId, int groupNum, String answerText, int idFromQuestion, boolean isTrueOrFalse) {
        this.answerId = answerId;
        this.groupNum = groupNum;
        this.answerText = answerText;
        this.idFromQuestion = idFromQuestion;
        this.isTrueOrFalse = isTrueOrFalse;
    }*/

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getIdFromQuestion() {
        return idFromQuestion;
    }

    public void setIdFromQuestion(int idFromQuestion) {
        this.idFromQuestion = idFromQuestion;
    }

    public boolean isTrueOrFalse() {
        return isTrueOrFalse;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        isTrueOrFalse = trueOrFalse;
    }
    //endregion
}
