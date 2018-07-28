package com.sxl.sxllibrary.retrofit;

/**
 * 创建时间: 2018/7/27
 * 功能描述:配置
 */
public class RetrofitConfig {
    public static int DEFAULT_TIME_OUT = 20;//超时时间 5s
    public static int DEFAULT_READ_TIME_OUT = 20;
    public static int CACHE_MAXSIZE=50 * 1024 * 1024;
    public static String CACHE_PATH="responses";
}
