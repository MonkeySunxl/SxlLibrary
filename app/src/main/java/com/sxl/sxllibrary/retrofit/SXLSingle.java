package com.sxl.sxllibrary.retrofit;


import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * MonkeySun
 */
public class SXLSingle{
    private static final int DEFAULT_TIME_OUT = 20;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 20;
    private static Context mContext;
    private static File httpCacheDirectory;
    private static Cache cache;
    private static OkHttpClient.Builder httpClientNoCacheBuilder;
    private static OkHttpClient.Builder httpClientCacheBuilder;
    /**
     * 初始化
     * @param context
     */
    public static void init(Context context) {
        mContext=context;
        //设置站点名称
        httpCacheDirectory = new File(mContext.getCacheDir(), "responses");
        cache = new Cache(httpCacheDirectory, 50 * 1024 * 1024);//本地保存50M
        httpClientCacheBuilder = createHttpClient(true);
        httpClientNoCacheBuilder = createHttpClient(false);
    }
    public static Context getContext() {
        if (mContext==null) throw new RuntimeException("no init context!");
        return mContext;
    }


    public static OkHttpClient.Builder createHttpClient(boolean isCache) {
//        Log.i("cache", "isCache:" + isCache);
            if (isCache) {
                CacheStrategyInterceptorCache cacheStrategyInterceptor = new CacheStrategyInterceptorCache();
                return new OkHttpClient
                        .Builder()
                        .cache(cache)
                        .addInterceptor(cacheStrategyInterceptor)
                        .addNetworkInterceptor(cacheStrategyInterceptor)
                        .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                        .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
            } else {
                CacheStrategyInterceptorNoCache cacheStrategyInterceptor = new CacheStrategyInterceptorNoCache();
                return new OkHttpClient()
                        .newBuilder()
                        .cache(cache)
                        .addInterceptor(cacheStrategyInterceptor)
                        .addNetworkInterceptor(cacheStrategyInterceptor)
                        .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                        .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
            }
        }


    /**
     *  添加公共参数拦截器
     *  HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor();
     * @return
     * httpClientCacheBuilder 有缓存
     * httpClientNoCacheBuilder 无缓存
     */
    public static OkHttpClient.Builder getHtpClientCacheBuilder() {
        if (httpClientCacheBuilder==null) throw new RuntimeException("null !");
        return httpClientCacheBuilder;
    }
    public static OkHttpClient.Builder getHttpClientNoCacheBuilder() {
        if (httpClientNoCacheBuilder==null) throw new RuntimeException("null !");
        return httpClientNoCacheBuilder;
    }



    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BaseUrl.API_BASE_URL)
                    .addConverterFactory(MyGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    /**
     * 动态修改站点地址 调用createService 前做如下操作
     * BaseUrl.API_BASE_URL="";
     * SXLSingle.getBuilder().baseUrl(BaseUrl.API_BASE_URL);
     * @return
     */
    public static Retrofit.Builder getBuilder() {
        if (builder==null) throw new RuntimeException("null !");
        return builder;
    }

    public static <S> S createService(Class<S> serviceClass, boolean isCache) {
        if (mContext==null) throw new RuntimeException("no init context!");
        if (isCache){
            return builder.client(httpClientCacheBuilder.build()).build().create(serviceClass);
        }else {
            return builder.client(httpClientNoCacheBuilder.build()).build().create(serviceClass);
        }
    }


}
