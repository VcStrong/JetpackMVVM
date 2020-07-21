package com.vc.wd.login.request;

import com.vc.wd.common.bean.Result;
import com.vc.wd.common.bean.UserInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
  * desc
  * author VcStrong
  * github VcStrong
  * date 2020/5/28 1:42 PM
  */
public interface ILoginRequest {

    /**
     * 密码规则是数字加字母超过8位即可
     * @return
     */
    @POST("user/v1/register")
    Observable<Result> register(@Body RequestBody json);

    /**
     * 密码规则是数字加字母超过8位即可
     * @return
     */
    @POST("user/v1/login")
    Observable<Result<UserInfo>> login(@Body RequestBody json);
}
