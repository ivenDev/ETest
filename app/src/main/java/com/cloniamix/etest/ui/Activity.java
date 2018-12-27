package com.cloniamix.etest.ui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.cloniamix.etest.Contract;


public abstract class Activity<P> extends AppCompatActivity implements Contract.View {

    public P mPresenter;



    @Override
    public void showToast(int resId) {
        Toast.makeText(this,resId,Toast.LENGTH_SHORT).show();
    }

    @Override
    public abstract void goToActivity();

    @Override
    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*mPresenterOfSelections = null;*/
    }
}
