package com.vc.wd.user.fragment;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vc.wd.common.core.WDFragment;
import com.vc.wd.common.util.Constant;
import com.vc.wd.user.R;
import com.vc.wd.user.databinding.FragMeBinding;
import com.vc.wd.user.vm.UserViewModel;

@Route(path = Constant.FRAGMENT_URL_ME)
public class MeFragment extends WDFragment<UserViewModel,FragMeBinding> {

    @Override
    protected UserViewModel initFragViewModel() {
        return new UserViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_me;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        binding.meBg.setImageURI(Uri.parse(LOGIN_USER.getHeadPic()));
        binding.meAvatar.setImageURI(Uri.parse(LOGIN_USER.getHeadPic()));
        binding.meNickname.setText(LOGIN_USER.getNickName());
    }


}
