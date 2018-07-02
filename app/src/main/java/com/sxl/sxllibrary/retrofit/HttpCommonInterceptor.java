package com.sxl.sxllibrary.retrofit;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 拦截器
 *
 * 向请求头里添加公共参数：可自行扩展
 */

public class HttpCommonInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原先的请求
        Request originalRequest = chain.request();
        //重新构建url
        HttpUrl.Builder builder = originalRequest.url().newBuilder();
        //如果是post请求的话就把参数重新拼接一下，get请求的话就可以直接加入公共参数了
        if(originalRequest.method().equals("POST")){
            FormBody body = (FormBody) originalRequest.body();
            for(int i = 0; i < body.size();i++){
//                Log.i("RequestFatory",body.name(i) + "---" + body.value(i));
                builder.addQueryParameter(body.name(i),body.value(i));
            }
        }
        //这里是我的2个公共参数：举个栗子
        //添加
        builder.addQueryParameter("mallId", "mallId")
                .addQueryParameter("robotNo","robotNo");
        //取消特定字段
        builder.removeAllQueryParameters("mallId");
        //新的url
        HttpUrl httpUrl = builder.build();
        Request request = originalRequest.newBuilder()
                .method(originalRequest.method(),originalRequest.body())
                .url(httpUrl).build();
        return chain.proceed(request);
    }
}
