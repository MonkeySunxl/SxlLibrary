package com.sxl.sxllibrary.example;

import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 创建时间: 2018/6/29
 * 功能描述:可自己定义
 */

public interface ApiService {
    //版本更新
    @POST("riyurumenApp/index/getVersion")
    Observable<BaseInfoBean<VersionBean>> getVersion();
    @GET("index/getVersion")
    Observable<BaseInfoBean<VersionBean>> getVersion1();
}
