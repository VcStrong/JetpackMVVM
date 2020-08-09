package com.vc.wd.main.fragment;

import android.os.Bundle;

import com.vc.wd.common.core.WDFragment;
import com.vc.wd.main.R;
import com.vc.wd.main.databinding.FragTestBinding;
import com.vc.wd.main.vm.TestViewModel;

public class TestFragment extends WDFragment<TestViewModel, FragTestBinding> {
    @Override
    protected TestViewModel initFragViewModel() {
        return new TestViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
