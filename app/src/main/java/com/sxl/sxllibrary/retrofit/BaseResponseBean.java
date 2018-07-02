package com.sxl.sxllibrary.retrofit;

/**
 * @ProjectName TEST
 * @ClassDescribe
 * @Author SXL
 * @Date 2018/1/26 10:32
 * 根据基础json数据设置
 */

public class BaseResponseBean {
    private String msg;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
