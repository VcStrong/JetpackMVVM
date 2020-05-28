package com.vc.wd.common.bean;

/**
 * desc
 * author VcStrong
 * github VcStrong
 * date 2020/5/28 1:42 PM
 */
public class Result<T>  {
    String status;
    String message;
    T result;
    String headPath;

    public Result(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }
}
