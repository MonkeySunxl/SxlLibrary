package com.sxl.sxllibrary.retrofit;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建时间: 2018/7/27
 * 功能描述:
 */
public class RetrofitManager {
    private volatile static RetrofitManager retrofitManager;
    private static File httpCacheDirectory;
    private static Cache cache;
    private static OkHttpClient.Builder httpClientNoCacheBuilder;
    private static OkHttpClient.Builder httpClientCacheBuilder;
    private static Retrofit.Builder builder;
    public static Context context;
    //构造方法私有
    public RetrofitManager() {
    }



    /**
     * 初始化 上下文,Retrofit.Builder,baseUrl
     * 可初始化前配置 RetrofitConfig 文件
     * @param
     *
     */
    public static void initRetrofit(Context mContext, Retrofit.Builder mBuilder, String baseUrl) {
        context=mContext;
        httpCacheDirectory = new File(context.getCacheDir(), RetrofitConfig.CACHE_PATH);
        cache = new Cache(httpCacheDirectory, RetrofitConfig.CACHE_MAXSIZE);//本地保存50M
        httpClientCacheBuilder = createCacheHttpClient();
        httpClientNoCacheBuilder = createNoCacheHttpClient();
        //是否使用默认GsonConverterFactory
        if (mBuilder==null){
            builder = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }else {
            builder = mBuilder;
        }

    }


    //获取单例
    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }
    //单例模式获取okhttp.Builder
    public static OkHttpClient.Builder createCacheHttpClient() {
//        Log.i("cache", "isCache:" + isCache);
            CacheStrategyInterceptorCache cacheStrategyInterceptor = new CacheStrategyInterceptorCache();
        if (httpClientCacheBuilder == null) {
            synchronized (OkHttpClient.Builder.class) {
                if (httpClientCacheBuilder == null) {
                    httpClientCacheBuilder = new OkHttpClient
                            .Builder()
                            .cache(cache)
                            .addInterceptor(cacheStrategyInterceptor)
                            .addNetworkInterceptor(cacheStrategyInterceptor)
                            .connectTimeout(RetrofitConfig.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(RetrofitConfig.DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
                }
            }
        }
            return httpClientCacheBuilder;
    }

    private static OkHttpClient.Builder createNoCacheHttpClient() {
        CacheStrategyInterceptorNoCache cacheStrategyInterceptor = new CacheStrategyInterceptorNoCache();
        if (httpClientNoCacheBuilder == null) {
            synchronized (OkHttpClient.Builder.class) {
                if (httpClientNoCacheBuilder == null) {
                    httpClientNoCacheBuilder = new OkHttpClient()
                            .newBuilder()
                            .cache(cache)
                            .addInterceptor(cacheStrategyInterceptor)
                            .addNetworkInterceptor(cacheStrategyInterceptor)
                            .connectTimeout(RetrofitConfig.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(RetrofitConfig.DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
                }
            }
        }
        return httpClientNoCacheBuilder;
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
        if (context==null) throw new RuntimeException("no init context!");
        if (isCache){
            return getBuilder().client(getHtpClientCacheBuilder().build()).build().create(serviceClass);
        }else {
            return getBuilder().client(getHttpClientNoCacheBuilder().build()).build().create(serviceClass);
        }
    }
}
