package com.demo.store;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by GSJ
 * Date: 2016/10/20
 * Time: 14:17
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static BaseActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    protected abstract void initView();
    public static BaseActivity getForegroundActivity(){
        return activity;
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;

    }

    @Override
    protected void onStop() {
        super.onStop();
        activity=null;
    }
}
