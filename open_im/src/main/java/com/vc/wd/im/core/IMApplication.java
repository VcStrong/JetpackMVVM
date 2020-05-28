package com.vc.wd.im.core;

import android.app.Application;

import com.vc.wd.common.core.IWDApplication;
import com.vc.wd.common.util.LogUtils;

/**
 * desc Module的Application不要去清单文件中配置，请打开common包的IWDAppcation接口把路径(包名+类名)配置上，
 *      WDApplication会自动初始化这些子模块App
 * author VcStrong
 * github VcStrong
 * date 2020/5/28 1:42 PM
 */
public class IMApplication implements IWDApplication {
    @Override
    public void onCreate(Application application) {
        //IM的初始化等等
        LogUtils.i("IMApplication 初始化");
    }
}
