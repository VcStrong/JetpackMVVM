package com.vc.wd.login.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vc.wd.login.R;
import com.vc.wd.common.core.WDActivity;
import com.vc.wd.common.core.WDApplication;
import com.vc.wd.login.databinding.ActivityLoginBinding;
import com.vc.wd.common.util.Constant;
import com.vc.wd.login.vm.LoginViewModel;

@Route(path = Constant.ACTIVITY_URL_LOGIN)
public class LoginActivity extends WDActivity<LoginViewModel, ActivityLoginBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewModel.pasVis.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {//密码显示，则隐藏
                    binding.loginPas.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {//密码隐藏则显示
                    binding.loginPas.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
}
