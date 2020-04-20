package com.vc.wd.core.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author dingtao
 * @date 2019/1/2 7:01 PM
 * 封装了自定义的异常，页面拿着我的异常做出友好提示
 */
public class ApiException extends Exception {
    private String code;//
    private String displayMessage;//提示的消息

    /**
     * 未知错误
     */
    public static final String UNKNOWN = "1000";

    /**
     * 解析错误
     */
    public static final String PARSE_ERROR = "1001";

    /**
     * 网络错误
     */
    public static final String NETWORK_ERROR = "1002";

    /**
     * 协议错误
     */
    public static final String HTTP_ERROR = "1003";


    public ApiException(String code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public ApiException(String code, String message, String displayMessage) {
        super(message);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    /**
     * 处理系统异常，封装成ApiException
     * Throwable包含Error和Exception
     */
    public static ApiException handleException(Throwable e) {

        e.printStackTrace();//打印异常

        ApiException ex;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //解析错误
            ex = new ApiException(PARSE_ERROR, "解析异常");
            return ex;
        } else if (e instanceof ConnectException || e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            //网络错误
            ex = new ApiException(NETWORK_ERROR, e.getMessage());
            return ex;
        } else {
            //未知错误
            ex = new ApiException(UNKNOWN, e.getMessage());
            return ex;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
