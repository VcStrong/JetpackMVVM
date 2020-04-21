package com.vc.wd.common.core;


import com.vc.wd.common.core.exception.ApiException;

/**
 * @author dingtao
 * @date 2018/12/30 10:30
 * qq:1940870847
 */
public interface DataCall<T> {

    void success(T data);
    void fail(ApiException data);

}
