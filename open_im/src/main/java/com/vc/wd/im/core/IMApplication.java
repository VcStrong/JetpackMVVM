package com.vc.wd.im.core;

import android.app.Application;
import android.util.Log;

import com.vc.wd.common.core.http.IWDApplication;
import com.vc.wd.common.util.LogUtils;

/**
 * @Package:        com.vc.wd.im.core
 * @ClassName:      IMApplication
 * @Description:    Module的Application不要去清单文件中配置，请打开common包的IWDAppcation接口
 *                  把路径(包名+类名)配置上，WDApplication会自动初始化这些子模块App
 * @Author:         VcStrong
 */
public class IMApplication implements IWDApplication {
    @Override
    public void onCreate(Application application) {
        //IM的初始化等等
        LogUtils.i("IMApplication 初始化");
    }
}
