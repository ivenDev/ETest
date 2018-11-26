package com.cloniamix.etest.pojo;

import java.util.List;

public class GSONObj {
    private int groupNum;
    private List<Question> questions;
    private List<Ticket> tickets;

    //region getters & setters
    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    //endregion
}
