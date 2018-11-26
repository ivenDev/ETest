package com.cloniamix.etest.presenter;

import com.cloniamix.etest.Contract;
import com.cloniamix.etest.R;
import com.cloniamix.etest.pojo.Ticket;
import java.util.List;

public class PresenterOfSelections extends MainPresenter<Contract.View> {


    public PresenterOfSelections(Contract.View view){
        super(view);
    }

    public void selectGroup() {
        mView.showToast(R.string.number_selected_group_text);

        mView.goToActivity();
    }


    public void selectTicket(int ticketNum) {

        mView.showToast(R.string.number_ticket_text);
        mView.goToActivity();
    }


    public void receivedADataErr() {
        mView.showToast(R.string.dataErr);

    }


    public List<Ticket> getTickets(int groupNum) {
        return mModel.getTickets(groupNum);
    }

    /*public int getPercent(int groupNum, int ticketNum){

        return mModel.getPercentAnswered(groupNum, ticketNum);
    }*/

}
