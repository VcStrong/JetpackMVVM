package com.vc.wd.main.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.vc.wd.main.R;
import com.vc.wd.common.core.WDActivity;
import com.vc.wd.common.util.Constant;
import com.vc.wd.main.databinding.ActivitySetBinding;
import com.vc.wd.main.vm.SetViewModel;

/**
 * @author dingtao
 * @date 2019/1/8 14:22
 * qq:1940870847
 */
@Route(path = Constant.ACTIVITY_URL_SET)
public class SetActivity extends WDActivity<SetViewModel, ActivitySetBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView(Bundle bundle) {
    }
}
