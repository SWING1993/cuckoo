package com.cuckoo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ResultModel {

    Integer code;

    String message;

    String url;

    Object result;

    String timestamp;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public Object getResult() {
        return result;
    }

    public String getTimestamp() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        timestamp = dateFormat.format(now);
        return timestamp;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                ", result=" + result +
                '}';
    }
}


