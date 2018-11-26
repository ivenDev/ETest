package com.cloniamix.etest;

import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.pojo.Ticket;
import java.util.List;


// FIXME: 13.09.2018 2)переделать презентер,чтобы указывать какого типа объекты он будет принимать, при его объявлении
// https://github.com/remind101/android-arch-sample/tree/master/app/src пример с Cache
// https://github.com/czyrux/MvpLoaderSample пример с Loader
// FIXME: 13.09.2018 3)реализовать сохранение презентера
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
