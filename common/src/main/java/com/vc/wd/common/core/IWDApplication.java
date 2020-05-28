package com.vc.wd.common.core;

import android.app.Application;

/**
  * desc 支持多个Module—Application共存，方便处理推送，IM等组件初始化问题；
  *      用法：module模块自定义的Application实现这个接口，然后把实现类的包名+类名写在下方常量中。
  *      加载过程：打开WDApplication代码，使用反射进行各个*Application的初始化
  * author VcStrong
  * github VcStrong
  * date 2020/5/28 2:27 PM
  */
public interface IWDApplication {
    String[] MODULE_APP= new String[]{
            "com.vc.wd.push.core.PushApplication",
            "com.vc.wd.im.core.IMApplication"
    };
    void onCreate(Application application);
}
