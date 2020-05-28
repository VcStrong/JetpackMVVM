package com.vc.wd.login.request;

import com.vc.wd.common.bean.Banner;
import com.vc.wd.common.bean.Circle;
import com.vc.wd.common.bean.Result;
import com.vc.wd.common.bean.UserInfo;
import com.vc.wd.common.bean.shop.HomeList;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
  * desc
  * author VcStrong
  * github VcStrong
  * date 2020/5/28 1:42 PM
  */
public interface ILoginRequest {

    /**
     *
     * @param m
     * @param p 密码规则是数字加字母超过8位即可
     * @return
     */
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> register(@Field("phone") String m, @Field("pwd") String p);

    /**
     * @param m
     * @param p 密码规则是数字加字母超过8位即可
     * @return
     */
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<UserInfo>> login(@Field("phone") String m, @Field("pwd") String p);

}
