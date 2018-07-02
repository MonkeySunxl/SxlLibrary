package com.sxl.sxllibrary.base;


import com.sxl.sxllibrary.retrofit.ExceptionHandle;

/**
 *
 */
public interface BaseView {
    void onShow();
    void onFial(ExceptionHandle.ResponeThrowable e);
    void onFinish();
}
