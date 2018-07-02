package com.sxl.sxllibrary.retrofit;

import java.io.IOException;

/**
 * @ProjectName TEST
 * @ClassDescribe
 * @Author SXL
 * @Date 2018/1/26 10:30
 * 错误返回
 */

public class DataResultException extends IOException {
    private String msg;
    private int status;

    public DataResultException(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }

    public DataResultException(String message, String msg, int status) {
        super(message);
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getstatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
