package com.cloniamix.etest;

import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.pojo.Ticket;
import java.util.List;

public interface Contract {
    interface View {
        void showToast(int resId);
        void goToActivity();
        void goToActivity(Class<?> cls);

    }

    interface Presenter<V extends View> {
        void attachView(V view);
        void detachView();

    }

    interface Model {
        List<Ticket> getTickets(int groupNumber);
        Ticket getTicket(int groupNumber, int ticketNumber);
        void updateQuestionInDB(Question question);
        void updateTicketInDB(Question question, int groupNum, int ticketNum);


    }
}
