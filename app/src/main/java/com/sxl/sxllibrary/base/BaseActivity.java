package com.sxl.sxllibrary.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sxl.sxllibrary.utils.AppManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getAppManager().addActivity(this);
        initBefore(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    public void initBefore(Bundle savedInstanceState) {

    }


    protected void init() {
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity();
    }
}
