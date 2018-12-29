package com.cloniamix.etest.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.cloniamix.etest.R;
import com.cloniamix.etest.model.Model;
import com.cloniamix.etest.mvp.views.SelView;
import com.cloniamix.etest.pojo.Ticket;

import java.util.List;

@InjectViewState
public class PresenterOfSelections extends MvpPresenter<SelView> {

    private Model mModel;

    private int mGroupNum;
    private int mMode;
    private int mQuestionNum;
    private int mTicketNum;

    private int mQuantityOfQuestions;
    private int mQuantityOfTickets;

    public PresenterOfSelections() {
        mModel = new Model();
        getViewState().setTitle();

    }

    public void groupSelBtnClicked(int viewId){
        String messageText;
        switch (viewId){
            case R.id.group_2_btn:
                mGroupNum = 2;
                break;

            case R.id.group_3_btn:
                mGroupNum = 3;
                break;

            case R.id.group_4_btn:
                mGroupNum = 4;
                break;

            case R.id.group_5_btn:
                mGroupNum = 5;
                break;
        }

        messageText = "Группа " + mGroupNum;
        getViewState().showToast(messageText);
        getViewState().goToActivity();
    }

    public void modeSelBtnClicked(int viewId) {
        switch (viewId){
            case R.id.question_mode_btn:
                mMode = 1;
                getViewState().goToActivity();
                break;

            case R.id.ticket_mode_btn:
                mMode = 2;
                getViewState().goToActivity();
                break;

            case R.id.exam_mode_btn:
                mMode = 3;
                break;
    }

}

    public void questionSelBtnClicked(){
        getViewState().goToActivity();
    }

    public void ticketSelBtnClicked() {
        String messageText = "Билет " + mTicketNum;
        getViewState().showToast(messageText);
        getViewState().goToActivity();
}

    public List<Ticket> getTickets() {
        return mModel.getTickets(mGroupNum);
    }

    public void updateData(){
        if (mMode == 1){
            mQuantityOfQuestions = mModel.getGroupQuestions(mGroupNum).size();
        }
        if (mMode == 2){
            mQuantityOfTickets = mModel.getTickets(mGroupNum).size();
        }

    }


    //region getters & setters
    public int getGroupNum() {
        return mGroupNum;
    }

    public void setGroupNum(int groupNum) {
        mGroupNum = groupNum;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    public int getQuestionNum() {
        return mQuestionNum;
    }

    public void setQuestionNum(int questionNum) {
        mQuestionNum = questionNum;
    }

    public int getTicketNum() {
        return mTicketNum;
    }

    public void setTicketNum(int ticketNum) {
        mTicketNum = ticketNum;
    }

    public int getQuantityOfQuestions() {
        return mQuantityOfQuestions;
    }

    public void setQuantityOfQuestions(int quantityOfQuestions) {
        mQuantityOfQuestions = quantityOfQuestions;
    }

    public int getQuantityOfTickets() {
        return mQuantityOfTickets;
    }

    public void setQuantityOfTickets(int quantityOfTickets) {
        mQuantityOfTickets = quantityOfTickets;
    }

    //endregion


    /*

    public void receivedADataErr() {
        mView.showToast(R.string.dataErr);

    }


    public List<Question> getQuestions(int groupNum){
        return mModel.getGroupQuestions(groupNum);
    }


    */

}
