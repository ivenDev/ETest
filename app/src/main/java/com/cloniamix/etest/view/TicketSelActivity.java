package com.cloniamix.etest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.cloniamix.etest.pojo.Ticket;
import com.cloniamix.etest.presenter.PresenterOfSelections;
import com.cloniamix.etest.R;
import java.util.List;


public class TicketSelActivity extends Activity<PresenterOfSelections>{

    private int mGroupNum;
    private int mTicketNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_selection);

        mPresenter = new PresenterOfSelections(this);
        mGroupNum = getIntent().getIntExtra("groupNum", 2);

        setTitle("Группа " + mGroupNum);

        List<Ticket> mTickets = mPresenter.getTickets(mGroupNum);

        if (mTickets != null) {
            int mNumberOfTickets = mTickets.size();

//region заполнение экрана кнопками билетов
            GridLayout gridLayout = findViewById(R.id.ticket_selection_buttons_container);
            gridLayout.setColumnCount(3);

            if (mNumberOfTickets != 0) {
                for (int i = 1; i <= mNumberOfTickets; i++) {
                    GridLayout.LayoutParams buttonParam = new GridLayout
                            .LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f),
                            GridLayout.spec(GridLayout.UNDEFINED, 1f));
                    buttonParam.width = 0;
                    final int a = i;

                    final Button button = new Button(this);
                    String btnText = getResources().getString(R.string.number_ticket_text, i);

                    int useCount = mTickets.get(i-1).getUsedTicketCount();

                    if (useCount == 0){
                         btnText = getResources().getString(R.string.number_ticket_text, i);
                    }
                    if (useCount>0 && useCount < mTickets.get(i-1).getQuestionsNumList().size()
                            && useCount != mTickets.get(i-1).getQuestionsNumList().size()){
                        btnText = getResources().getString(R.string.number_ticket_text, i)
                        + "\n" + "В процессе";
                    }
                        if (useCount == mTickets.get(i-1).getQuestionsNumList().size()){
                        btnText = getResources().getString(R.string.number_ticket_text, i)
                                + "\n" + mTickets.get(i-1).getPercentAnswered() + "%";
                    }

                    button.setText(btnText);
                    button.setId(i);
                    button.setBackgroundColor(getResources().getColor(R.color.colorButtons));
                    button.setAllCaps(false);
                    button.setLayoutParams(buttonParam);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mTicketNum = a;
                            mPresenter.selectTicket(/*a*/);
                        }
                    });

                    gridLayout.addView(button);
                }
            } else {
                TextView textView = new TextView(this);
                GridLayout.LayoutParams textParam = new GridLayout
                        .LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f),
                        GridLayout.spec(GridLayout.UNDEFINED, 1f));
                /*textParam.width = 0;*/
                textParam.setGravity(Gravity.CENTER);
                textView.setLayoutParams(textParam);
                textView.setText(R.string.no_data_text);
                gridLayout.addView(textView);
            }
            // endregion
        } else {
            mPresenter.receivedADataErr();
        }


    }


    @Override
    public void showToast(int resId) {
        String toastText = getResources().getString(resId,mTicketNum);
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void goToActivity() {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("groupNum",mGroupNum);
        intent.putExtra("ticketNum",mTicketNum);

        intent.putExtra("mode", 2);

        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.detachView();
        mPresenter = null;
    }
}
