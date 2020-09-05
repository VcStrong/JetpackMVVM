package com.vc.wd.common.core.http;


import com.google.gson.Gson;
import com.vc.wd.common.BuildConfig;
import com.vc.wd.common.core.WDViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * desc
 * author VcStrong
 * github VcStrong
 * date 2020/5/28 1:42 PM
 */
public class NetworkManager {

    private static Gson gson = new Gson();
    private static NetworkManager instance;

    //这个是模仿应用多模块采用不同的域名，域名配置参见config.gradle
    private Retrofit app_retrofit, baidu_retrofit;

    private NetworkManager() {
        init();
    }

    public static NetworkManager instance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//打印请求参数，请求结果

        //OKHttp最大同时请求5个不同域名
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        //这个是模仿应用多模块采用不同的域名，域名配置参见config.gradle
        app_retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.SERVER_URL)//base_url:http+域名
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用Rxjava对回调数据进行处理
                .addConverterFactory(GsonConverterFactory.create())//响应结果的解析器，包含gson，xml，protobuf
                .build();

        //这个是模仿应用多模块采用不同的域名，域名配置参见config.gradle
        baidu_retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BAIDU_URL)//base_url:http+域名
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用Rxjava对回调数据进行处理
                .addConverterFactory(GsonConverterFactory.create())//响应结果的解析器，包含gson，xml，protobuf
                .build();
    }

    public <T> T create(int requestType, final Class<T> service) {
        if (requestType == WDViewModel.REQUEST_TYPE_SDK_BD) {//如果请求百度SDK的接口
            return baidu_retrofit.create(service);
        }
        return app_retrofit.create(service);
    }

    /**
     * 把传递数据转为json格式，返回RequestBody
     *
     * @param keys   请求参数
     * @param values 数据
     * @return
     */
    public static RequestBody convertJsonBody(String[] keys, Object[] values) {
        JSONObject json = new JSONObject();

        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            Object value = values[i];
            try {//减小数据中某一项对于所有数据的影响
                json.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json.toString());
        return  body;
    }

    /**
     * 把传递数据转为json格式，返回RequestBody
     *
     * @param obj 数据
     * @return
     */
    public static RequestBody convertJsonBody(Object obj) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), gson.toJson(obj));
        return  body;
    }
}
