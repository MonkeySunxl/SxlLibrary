package com.sxl.sxllibrary.retrofit;


import com.sxl.sxllibrary.utils.NetWorkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 需要保存json拦截
 */

public class CacheStrategyInterceptorCache implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        Log.i("aaa", "intercept: "+request.toString());
        if (!NetWorkUtils.isConnected(SXLSingle.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();

        }
        Response response = chain.proceed(request);

        if (NetWorkUtils.isConnected(SXLSingle.getContext())) {
            int maxAge = 60 * 60 * 4 ;//4小时
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        } else {
            int maxStale = 60 * 60 * 24;//24小时
            return response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
//        return response;
    }
}
