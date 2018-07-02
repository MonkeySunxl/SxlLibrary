package com.sxl.sxllibrary.retrofit;


import rx.Subscriber;

/**
 * @ProjectName TEST
 * @ClassDescribe
 * @Author SXL
 * @Date 2018/1/26 10:43
 * 封装请求  错误处理可以自行扩展
 */

public abstract class SxlSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
                onFail(ExceptionHandle.handleException(e));
                onFinish();
        int code = ExceptionHandle.handleException(e).code;
//        if (code==2) {
//            UIHelper.ToastMessage(PkuskyApplication.getAppContext(), "请重新登录!");
//            SPUtils.setLoginStatus(PkuskyApplication.getAppContext(), false);
//            AppManager.getAppManager().finishAllActivity();
//            // 清除 JPush 接口来设置别名。
//            JPushInterface.setAliasAndTags(context, "", null);
//            IntentUtils.startActivity(context, LoginActivity.class);
//        } else {
//            UIHelper.ToastMessage(PkuskyApplication.getAppContext(), ExceptionHandle.handleException(e).message);
//        }
    }

    /**
     * 选择性调用错误处理
     * @param e
     */
    public void onFail(ExceptionHandle.ResponeThrowable e) {
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }
    public abstract void onSuccess(T t);

    protected abstract void onFinish();

//    protected abstract void onFail(ExceptionHandle.ResponeThrowable e);

}
