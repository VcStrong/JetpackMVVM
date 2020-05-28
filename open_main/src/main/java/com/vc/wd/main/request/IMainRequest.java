package com.vc.wd.main.request;

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
public interface IMainRequest {

    /**
     * banner
     */
    @GET("commodity/v1/bannerShow")
    Observable<Result<List<Banner>>> bannerShow();

    /**
     * 首页商品列表
     */
    @GET("commodity/v1/commodityList")
    Observable<Result<HomeList>> commodityList();

    /**
     * 圈子
     */
    @GET("circle/v1/findCircleList")
    Observable<Result<List<Circle>>> findCircleList(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count);

    /**
     * 圈子点赞
     */
    @FormUrlEncoded
    @POST("circle/verify/v1/addCircleGreat")
    Observable<Result> addCircleGreat(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId,
            @Field("circleId") long circleId);

    /**
     * 发布圈子
     */
    @POST("circle/verify/v1/releaseCircle")
    Observable<Result> releaseCircle(@Header("userId") long userId,
                                     @Header("sessionId") String sessionId,
                                     @Body MultipartBody body);
}
