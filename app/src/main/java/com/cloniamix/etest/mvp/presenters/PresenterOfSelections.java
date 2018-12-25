package com.cloniamix.etest.mvp.presenters;

import com.cloniamix.etest.Contract;
import com.cloniamix.etest.R;
import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.pojo.Ticket;
import com.cloniamix.etest.ui.QuestionSelActivity;
import com.cloniamix.etest.ui.TicketSelActivity;

import java.util.List;

public class PresenterOfSelections extends MainPresenter<Contract.View> {


    public PresenterOfSelections(Contract.View view){
        super(view);
    }


    public void selectGroup() {
        mView.showToast(R.string.number_selected_group_text);

        mView.goToActivity();
    }


    public void selectTicket(/*int ticketNum*/) {

        mView.showToast(R.string.number_ticket_text);
        mView.goToActivity();
    }


    public void selectMode(/*int restTextId,*/ int btnId) {

        /*mView.showToast();*/
        switch (btnId){
            case R.id.question_mode_btn:
                mView.goToActivity(QuestionSelActivity.class);
                break;
            case R.id.ticket_mode_btn:
                mView.goToActivity(TicketSelActivity.class);
                break;
            case R.id.exam_mode_btn:
                break;
        }

    }


    public void selectQuestion(){
        mView.goToActivity();
    }


    public void receivedADataErr() {
        mView.showToast(R.string.dataErr);

    }


    public List<Question> getQuestions(int groupNum){
        return mModel.getGroupQuestions(groupNum);
    }

    public List<Ticket> getTickets(int groupNum) {
        return mModel.getTickets(groupNum);
    }

}
