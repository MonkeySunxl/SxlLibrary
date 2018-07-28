package com.sxl.sxllibrary.example;

import com.sxl.sxllibrary.retrofit.ObjectLoader;
import com.sxl.sxllibrary.retrofit.RetrofitManager;

/**
 * 创建时间: 2018/6/29
 * 功能描述:
 */

public class HomeLoader extends ObjectLoader {
     ApiService apiService;
     private Object versonInfo;

     public HomeLoader() {
         apiService = RetrofitManager.getInstance().createService(ApiService.class, false);
     }

//     public Observable<BaseInfoBean<VersionBean>> getVersonInfo() {
//         return baseSubscribe(apiService.getVersion());
//     }
}
