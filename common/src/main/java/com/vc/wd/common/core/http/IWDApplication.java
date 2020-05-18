package com.vc.wd.common.core.http;

import android.app.Application;

public interface IWDApplication {
    public static final String[] MODULE_APP= new String[]{
            "com.vc.wd.push.core.PushApplication",
            "com.vc.wd.im.core.IMApplication"
    };
    void onCreate(Application application);
}
