package com.cloniamix.etest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.cloniamix.etest.R;
import com.cloniamix.etest.mvp.presenters.PresenterOfSelections;
import com.cloniamix.etest.mvp.views.SelView;

public class GroupSelActivity extends MvpAppCompatActivity implements SelView {

    @InjectPresenter
    PresenterOfSelections mPresenterOfSelections;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_selection);
    }

    @Override
    public void setTitle() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToActivity(){
        Intent intent = new Intent(this, ModeSelActivity.class);
        intent.putExtra("groupNum", mPresenterOfSelections.getGroupNum());
        startActivity(intent);
    }

    @Override
    public void btnClick(View view){
        mPresenterOfSelections.groupSelBtnClicked(view.getId());
    }


}
