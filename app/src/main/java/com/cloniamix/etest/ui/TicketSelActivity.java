package com.cloniamix.etest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.cloniamix.etest.mvp.views.SelView;
import com.cloniamix.etest.pojo.Ticket;
import com.cloniamix.etest.mvp.presenters.PresenterOfSelections;
import com.cloniamix.etest.R;
import java.util.List;


public class TicketSelActivity extends MvpAppCompatActivity implements SelView{

    @InjectPresenter
    PresenterOfSelections mPresenterOfSelections;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_selection);

        mPresenterOfSelections.setGroupNum(getIntent().getIntExtra("groupNum", 0));

        List<Ticket> mTickets = mPresenterOfSelections.getTickets();

        int mNumberOfTickets = mPresenterOfSelections.getQuantityOfTickets();

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
                            mPresenterOfSelections.setTicketNum(a);
                            mPresenterOfSelections.selectTicket();
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



    }


    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void btnClick(View view) {

    }

    @Override
    public void setTitle() {
        setTitle("Группа " + mPresenterOfSelections.getGroupNum());
    }

    @Override
    public void goToActivity() {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("groupNum",mPresenterOfSelections.getGroupNum());
        intent.putExtra("ticketNum",mPresenterOfSelections.getTicketNum());
        intent.putExtra("mode", 2);
        startActivity(intent);
    }



}
