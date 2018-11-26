package com.cloniamix.etest.dataBase.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TicketForRoom {

    @PrimaryKey(autoGenerate = true)
    private int ticketId;
    private int groupNum;
    private int ticketNum;
    private int idFromQuestion;

    private boolean isUsed;//added 9.11.18
    private boolean isCorrectAnswered;//added 9.11.18

    //region constructor, getters & setters


    public TicketForRoom() {
    }

    /*public TicketForRoom(int ticketId, int groupNum, int ticketNum, int idFromQuestion) {
        this.ticketId = ticketId;
        this.groupNum = groupNum;
        this.ticketNum = ticketNum;
        this.idFromQuestion = idFromQuestion;
    }*/

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public int getIdFromQuestion() {
        return idFromQuestion;
    }

    public void setIdFromQuestion(int idFromQuestion) {
        this.idFromQuestion = idFromQuestion;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isCorrectAnswered() {
        return isCorrectAnswered;
    }

    public void setCorrectAnswered(boolean correctAnswered) {
        isCorrectAnswered = correctAnswered;
    }

    //endregion
}
