package com.cloniamix.etest.pojo;

import java.util.List;

public class Ticket {

    /*private int ticketId;//added 9.11.18*/

    private int ticketNum;
    private List<Integer> questionsNumList;

    private int percentAnswered;//added 9.11.18
    private int usedTicketCount;//added 9.11.18

    //region


   /* public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
*/
    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public List<Integer> getQuestionsNumList() {
        return questionsNumList;
    }

    public void setQuestionsNumList(List<Integer> questionsNumList) {
        this.questionsNumList = questionsNumList;
    }

    public int getPercentAnswered() {
        return percentAnswered;
    }

    public void setPercentAnswered(int percentAnswered) {
        this.percentAnswered = percentAnswered;
    }

    public int getUsedTicketCount() {
        return usedTicketCount;
    }

    public void setUsedTicketCount(int usedTicketCount) {
        this.usedTicketCount = usedTicketCount;
    }

    //endregion
}
