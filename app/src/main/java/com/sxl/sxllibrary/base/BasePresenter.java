package com.sxl.sxllibrary.base;

/**
 * Created by zhangwei on 2016/11/22.
 */


//V 视图层接口

public class BasePresenter<V extends BaseView> {
    public V view;

    public void addView(V view) {
        this.view = view;
    }

    public void dettachView() {
        if (view != null) {
            view = null;
        }
    }


}
