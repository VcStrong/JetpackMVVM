package com.vc.wd.common.core.http;

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
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {

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
     * 圈子
     */
    @FormUrlEncoded
    @POST("user/findCircle/{uid}")
    Observable<Result<List<Circle>>> findCircle(
            @Path("uid") int uid,
            @Field("page") int page,
            @Field("count") int count);

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
     * 我的足迹
     */
    @GET("commodity/verify/v1/browseList")
    Observable<Result<List<Banner>>> browseList(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count);

    /**
     * 同步购物车数据
     */
    @PUT("order/verify/v1/syncShoppingCart")
    Observable<Result> syncShoppingCart(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Body String data);

    /**
     * 发布圈子
     */
    @POST("circle/verify/v1/releaseCircle")
    Observable<Result> releaseCircle(@Header("userId") long userId,
                                     @Header("sessionId") String sessionId,
                                     @Body MultipartBody body);
}
