package com.cloniamix.etest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.cloniamix.etest.R;
import com.cloniamix.etest.presenter.PresenterOfSelections;

public class ModeSelActivity extends Activity<PresenterOfSelections> implements View.OnClickListener{

    private int mGroupNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);

        mPresenter = new PresenterOfSelections(this);
        mGroupNum = getIntent().getIntExtra("groupNum", 2);
        setTitle("Группа " + mGroupNum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question_mode_btn:
                break;
            case R.id.ticket_mode_btn:
                mPresenter.selectMode(R.string.ticket_mode_btn_text);
                break;
            case R.id.exam_mode_btn:
                break;
        }
    }

    @Override
    public void goToActivity() {
            Intent intent = new Intent(this,TicketSelActivity.class);
            intent.putExtra("groupNum",mGroupNum);
            startActivity(intent);

    }
}
