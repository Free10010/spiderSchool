package com.spider.school.Util;

import java.util.Map;

public class ResponseData {
    private String msg;
    private Boolean status = false;
    private Object data;

    public ResponseData() {
    }

    public ResponseData(String msg, boolean status) {
        this.msg = msg;
        this.status = status;
    }

    public ResponseData(String msg, boolean status, Map data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public static ResponseData OK(String msg,Object data){
        ResponseData responseData = new ResponseData();
        responseData.setStatus(true);
        responseData.setMessage(msg);
        responseData.setData(data);
        return responseData;
    }
    public static ResponseData OK(String msg){
        ResponseData responseData = new ResponseData();
        responseData.setStatus(true);
        responseData.setMessage(msg);
        return responseData;
    }

    public static ResponseData ERROR(String msg,Object data){
        ResponseData responseData = new ResponseData();
        responseData.setStatus(false);
        responseData.setMessage(msg);
        responseData.setData(data);
        return responseData;
    }
    public static ResponseData ERROR(String msg){
        ResponseData responseData = new ResponseData();
        responseData.setStatus(true);
        responseData.setMessage(msg);
        return responseData;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setMessage(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
    public Boolean getStatus() {
        return status;
    }
    public Object getData() {
        return data;
    }
}
