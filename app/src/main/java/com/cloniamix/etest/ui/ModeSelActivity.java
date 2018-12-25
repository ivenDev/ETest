package com.cloniamix.etest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.cloniamix.etest.R;
import com.cloniamix.etest.mvp.presenters.PresenterOfSelections;

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

        mPresenter.selectMode(v.getId());

        /*switch (v.getId()){
            case R.id.question_mode_btn:
                mPresenter.selectMode(R.string.question_mode_btn_text);
                break;
            case R.id.ticket_mode_btn:

                break;
            case R.id.exam_mode_btn:
                break;
        }*/
    }

    @Override
    public void goToActivity() {

    }

    @Override
    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        intent.putExtra("groupNum",mGroupNum);
        startActivity(intent);
    }
}
