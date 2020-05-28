package com.vc.wd.common.core;


import com.vc.wd.common.core.exception.ApiException;

/**
 * desc
 * author VcStrong
 * github VcStrong
 * date 2020/5/28 1:42 PM
 */
public interface DataCall<T> {

    void success(T data);
    void fail(ApiException data);

}
