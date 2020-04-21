package com.vc.wd.main.vm;

import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.vc.wd.common.core.WDViewModel;
import com.vc.wd.common.core.http.IAppRequest;
import com.vc.wd.common.util.Constant;

public class SetViewModel extends WDViewModel<IAppRequest> {

    public void logout(){
        userInfoBox.remove(LOGIN_USER);
        //Intent清除栈FLAG_ACTIVITY_CLEAR_TASK会把当前栈内所有Activity清空；
        //FLAG_ACTIVITY_NEW_TASK配合使用，才能完成跳转
        ARouter.getInstance().build(Constant.ACTIVITY_URL_LOGIN)
                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
                .navigation();
    }
}
