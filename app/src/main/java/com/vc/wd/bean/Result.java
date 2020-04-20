package com.vc.wd.bean;

/**
 * @author dingtao
 * @date 2018/12/28 10:05
 * qq:1940870847
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
