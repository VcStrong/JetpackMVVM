package com.vc.wd.debug.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.vc.wd.common.core.IWDApplication;
import com.vc.wd.common.core.WDApplication;
import com.vc.wd.common.util.logger.Logger;
import com.vc.wd.common.util.shared.SharedUtils;
import com.vc.wd.debug.BuildConfig;
import com.vc.wd.debug.vm.DebugViewModel;

import io.objectbox.android.AndroidObjectBrowser;
import me.ele.uetool.UETool;

/**
 * desc Module的Application不要去清单文件中配置，请打开common包的IWDAppcation接口把路径(包名+类名)配置上，
 * WDApplication会自动初始化这些子模块App
 * author VcStrong
 * github VcStrong
 * date 2021/1/14 1:42 PM
 */
public class DebugApplication implements IWDApplication {
    private final Logger logger = Logger.createLogger(getClass());

    @Override
    public void onCreate(Application application) {
        logger.i("Debug模块初始化");
        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(WDApplication.getBoxStore()).start(application);
            logger.i("ObjectBrowser Started: " + started + "   " + WDApplication.getBoxStore().getObjectBrowserPort());
        }

        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

            private int visibleActivityCount;

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                visibleActivityCount++;
                boolean ue = SharedUtils.getBoolen(DebugViewModel.SWITCH_UETOOL);
                if (ue) {
                    UETool.showUETMenu();
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                visibleActivityCount--;
                if (visibleActivityCount == 0) {
                    UETool.dismissUETMenu();
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
