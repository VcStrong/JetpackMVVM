package com.vc.wd.main.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vc.wd.main.R;
import com.vc.wd.common.core.WDActivity;
import com.vc.wd.common.util.Constant;
import com.vc.wd.main.databinding.ActivitySetBinding;
import com.vc.wd.main.vm.SetViewModel;

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
