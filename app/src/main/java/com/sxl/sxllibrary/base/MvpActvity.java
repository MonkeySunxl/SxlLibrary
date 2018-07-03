package com.sxl.sxllibrary.base;

import android.os.Bundle;

public abstract class MvpActvity<P extends BasePresenter> extends BaseActivity implements BaseView {
   public P presenter;

    @Override
    public void initBefore(Bundle savedInstanceState) {
        super.initBefore(savedInstanceState);
        presenter = initPresenter();
        presenter.addView(this);
    }

    public abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
