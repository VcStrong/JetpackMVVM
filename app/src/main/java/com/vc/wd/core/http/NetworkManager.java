package com.vc.wd.core.http;


import com.vc.wd.core.WDViewModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author dingtao
 * @date 2018/12/28 10:07
 * qq:1940870847
 */
public class NetworkManager {

    private static NetworkManager instance;
    private Retrofit app_retrofit,baidu_retrofit;

    private NetworkManager(){
        init();
    }

    public static NetworkManager instance() {
        if (instance == null){
            instance = new NetworkManager();
        }
        return instance;
    }

    private void init(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//打印请求参数，请求结果

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        UserInfoDao userInfoDao = DaoMaster.newDevSession(WDApplication.getContext(),UserInfoDao.TABLENAME).getUserInfoDao();
//                        List<UserInfo> userInfos = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Status.eq(1)).list();
//                        UserInfo userInfo = userInfos.get(0);//读取第一项
//                        Request request = chain.request().newBuilder()
//                                .addHeader("userId",userInfo.getUserId()+"")
//                                .addHeader("sessionId",userInfo.getSessionId())
//                                .build();
//                        return chain.proceed(request);
//                    }
//                })
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        app_retrofit = new Retrofit.Builder()
                .client(okHttpClient)
//                .baseUrl("http://169.254.101.220:8080/")//base_url:http+域名
//                .baseUrl("http://172.17.8.100/small/")//base_url:http+域名
                .baseUrl("http://mobile.bwstudent.com/small/")//base_url:http+域名
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用Rxjava对回调数据进行处理
                .addConverterFactory(GsonConverterFactory.create())//响应结果的解析器，包含gson，xml，protobuf
                .build();

        baidu_retrofit = new Retrofit.Builder()
                .client(okHttpClient)
//                .baseUrl("http://169.254.101.220:8080/")//base_url:http+域名
//                .baseUrl("http://172.17.8.100/small/")//base_url:http+域名
                .baseUrl("http://mobile.bwstudent.com/small/")//base_url:http+域名
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用Rxjava对回调数据进行处理
                .addConverterFactory(GsonConverterFactory.create())//响应结果的解析器，包含gson，xml，protobuf
                .build();
    }

    public <T> T create(int requestType ,final Class<T> service){
        if (requestType == WDViewModel.REQUEST_TYPE_SDK_BD){//如果请求百度SDK的接口
            return baidu_retrofit.create(service);
        }
        return app_retrofit.create(service);
    }

}
