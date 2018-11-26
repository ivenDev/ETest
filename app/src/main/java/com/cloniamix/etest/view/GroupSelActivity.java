package com.cloniamix.etest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.cloniamix.etest.R;
import com.cloniamix.etest.presenter.PresenterOfSelections;

public class GroupSelActivity extends Activity<PresenterOfSelections> {

    private static final String TAG = "myAppTag";
    private int mGroupNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG , "onCreate: from GroupSelActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_selection);

        /*if (savedInstanceState == null){
        }else {
            mGroupNum = savedInstanceState.getInt("groupNum");
        }*/
        mPresenter = new PresenterOfSelections(this);

    }

    @Override
    public void showToast(int resId) {
        String toastText = getResources().getString(resId,mGroupNum);
        Toast.makeText(this,toastText,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToActivity(){
        Intent intent = new Intent(this, TicketSelActivity.class);
        intent.putExtra("groupNum",mGroupNum);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState: ");

        outState.putInt("groupNum",mGroupNum);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause: ");


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
       /* mGroupNum = 0;*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: ");

        mPresenter.detachView();
        mPresenter = null;
    }

    public void btnClick(View view){


        switch (view.getId()){
            case R.id.group_2_btn:

                mGroupNum = 2;
                mPresenter.selectGroup();

                break;

            case R.id.group_3_btn:
                mGroupNum = 3;
                mPresenter.selectGroup();

                break;

            case R.id.group_4_btn:
                mGroupNum = 4;
                mPresenter.selectGroup();

                break;

            case R.id.group_5_btn:
                mGroupNum = 5;
                mPresenter.selectGroup();

                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}
