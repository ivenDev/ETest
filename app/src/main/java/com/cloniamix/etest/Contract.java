package com.cloniamix.etest;

import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.pojo.Ticket;
import java.util.List;

public interface Contract {
    interface View {
        void showToast(int resId);
        void goToActivity();

    }

    interface Presenter<V extends View> {
        void attachView(V view);
        void detachView();

    }

    interface Model {
        List<Ticket> getTickets(int groupNumber);
        Ticket getTicket(int groupNumber, int ticketNumber);
        /*Question getQuestion(int questionId);*/
        void updateDB(Question question, int groupNum, int ticketNum);
        /*void initData();*/


    }
}
