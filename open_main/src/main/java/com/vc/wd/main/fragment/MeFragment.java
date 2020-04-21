package com.vc.wd.main.fragment;

import android.net.Uri;
import android.os.Bundle;
import com.vc.wd.main.R;
import com.vc.wd.common.core.WDFragment;
import com.vc.wd.main.databinding.FragMeBinding;
import com.vc.wd.main.vm.MainViewModel;

/**
 * @author dingtao
 * @date 2019/1/2 10:28
 * qq:1940870847
 */
public class MeFragment extends WDFragment<MainViewModel, FragMeBinding> {

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
