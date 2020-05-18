package com.vc.wd.common.core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.vc.wd.common.bean.MyObjectBox;
import com.vc.wd.common.core.http.IWDApplication;

import io.objectbox.BoxStore;


/**
 * @name: MyApplication
 * @remark:
 */
public class WDApplication extends Application {
    /**
     * 主线程ID
     */
    private static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;

    private static int MAX_MEM = 30 * ByteConstants.MB;

    /**
     * context 全局唯一的上下文
     */
    private static Context context;

    private static SharedPreferences sharedPreferences;
    private static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        sharedPreferences = getSharedPreferences("share.xml", MODE_PRIVATE);

        //ObjectBox数据库
        boxStore = MyObjectBox.builder().androidContext(this).build();

        ARouter.openLog();     // Print log
        ARouter.openDebug();
        ARouter.init(this);//阿里路由初始化

        Fresco.initialize(this,getConfigureCaches(this));//图片加载框架初始化

        //IM或者推送等服务的appliation初始化
        loadModuleApp();

    }

    /**
     * 加载各个模块的Application，例如：推送和IM等模块都需要有Application，
     * 但组件化只能有一个Application，而且为了解耦各个模块不能互相引用，
     * 所以只能通过反射方式，把这些module_appliation进行初始化
     */
    private void loadModuleApp(){
        for (String moduleImpl : IWDApplication.MODULE_APP){
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IWDApplication){
                    ((IWDApplication) obj).onCreate(this);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }

    public static SharedPreferences getShare() {
        return sharedPreferences;
    }

    /**
     * @return 全局唯一的上下文
     * @author: 康海涛 QQ2541849981
     * @describe: 获取全局Application的上下文
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    private ImagePipelineConfig getConfigureCaches(Context context) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                MAX_MEM,// 内存缓存中总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,// 内存缓存中图片的最大数量。
                MAX_MEM,// 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,// 内存缓存中准备清除的总图片的最大数量。
                Integer.MAX_VALUE);// 内存缓存中单个图片的最大大小。

        Supplier<MemoryCacheParams> mSupplierMemoryCacheParams = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return bitmapCacheParams;
            }
        };
        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(context);
        builder.setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams);
        return builder.build();
    }

}