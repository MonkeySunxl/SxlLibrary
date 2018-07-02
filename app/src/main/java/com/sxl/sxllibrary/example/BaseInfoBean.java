package com.sxl.sxllibrary.example;

/**
 * @ProjectName pkusky
 * @ClassDescribe
 * @Author
 * @Date 2017/7/25 11:58
 * @Copyright 未名天
 */

public class BaseInfoBean<T> {
    private int status;
    private String msg;
    private T info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

}
