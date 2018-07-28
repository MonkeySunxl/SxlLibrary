package com.sxl.sxllibrary.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
//        RetrofitManager.initRetrofit(this,null,"http://api6.pkusky.org/index.php/");
////         Log.i("aaa", "onCreate: "+context);
////         Log.i("aaa", "onCreate: "+this);
//         HomeLoader homeLoader = new HomeLoader();
//         homeLoader.getVersonInfo().subscribe(new SxlSubscriber<BaseInfoBean<VersionBean>>() {
//             @Override
//             public void onSuccess(BaseInfoBean<VersionBean> infoBean) {
//                 Log.i("检测版本更新1", "onNext: "+infoBean.getInfo().toString());
//             }
//
//             @Override
//             protected void onFinish() {
//
//             }
//         });
//         BaseUrl.API_BASE_URL="";
//         SXLSingle.getBuilder().baseUrl(BaseUrl.API_BASE_URL);
//         // 添加公共参数拦截器
//         //TODO:
//         SXLSingle.getHttpClientNoCacheBuilder().addInterceptor(new HttpCommonInterceptor());
//         SXLSingle.createService(ApiService.class,false).getVersion1()
//                 .subscribeOn(Schedulers.newThread())
//                 .observeOn(AndroidSchedulers.mainThread())
//                 .subscribe(new Subscriber<BaseInfoBean<VersionBean>>() {
//                     @Override
//                     public void onCompleted() {

//                     }

//                     @Override
//                     public void onError(Throwable e) {

//                     }

//                     @Override
//                     public void onNext(BaseInfoBean<VersionBean> infoBean) {
//                         Log.i("检测版本更新2", "onNext: "+infoBean.getInfo().toString());
//                         if (infoBean.getStatus() == 1) {
                            
//                         }
//                     }
//                 });
    }
}
