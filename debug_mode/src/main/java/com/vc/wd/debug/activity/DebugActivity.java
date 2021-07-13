package com.vc.wd.debug.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vc.wd.common.core.WDActivity;
import com.vc.wd.common.util.Constant;
import com.vc.wd.debug.R;
import com.vc.wd.debug.databinding.ActivityDebugBinding;
import com.vc.wd.debug.vm.DebugViewModel;

/**
 *  debug功能页
 */
@Route(path = Constant.ACTIVITY_URL_DEBUG)
public class DebugActivity extends WDActivity<DebugViewModel, ActivityDebugBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_debug;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }
}
