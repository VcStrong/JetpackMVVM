package com.vc.wd.push.core;

import android.app.Application;

import com.vc.wd.common.core.http.IWDApplication;
import com.vc.wd.common.util.LogUtils;

/**
  * @Package:        com.vc.wd.push.core
  * @ClassName:      PushApplication
  * @Description:    Module的Application不要去清单文件中配置，请打开common包的IWDAppcation接口
  *                  把路径(包名+类名)配置上，WDApplication会自动初始化这些子模块App
  * @Author:         VcStrong
  */
public class PushApplication implements IWDApplication {
    @Override
    public void onCreate(Application application) {
        //推送初始化等等工作
        LogUtils.i("PushApplication 初始化");
    }
}
