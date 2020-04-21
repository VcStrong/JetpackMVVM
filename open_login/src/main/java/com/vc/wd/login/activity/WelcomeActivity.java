package com.vc.wd.login.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vc.wd.login.R;
import com.vc.wd.common.core.WDActivity;
import com.vc.wd.login.databinding.ActivityWelcomeBinding;
import com.vc.wd.common.util.Constant;
import com.vc.wd.login.vm.WelcomeViewModel;


/**
 * @author dingtao
 * @date 2018/12/29 16:29
 * qq:1940870847
 */
@Route(path = Constant.ACTIVITY_URL_WELCOME)
public class WelcomeActivity extends WDActivity<WelcomeViewModel, ActivityWelcomeBinding> {

    @Override
    protected void initView(Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

}
