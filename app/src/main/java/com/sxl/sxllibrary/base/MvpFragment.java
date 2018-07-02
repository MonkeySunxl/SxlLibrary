package com.sxl.sxllibrary.base;

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {
    public P prsenter;

    public abstract P initPresenter();

    @Override
    protected void initBefore() {
        super.initBefore();
        prsenter = initPresenter();
        prsenter.addView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        prsenter.dettachView();
    }
}
