package com.vc.wd.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vc.wd.main.R;
import com.vc.wd.common.core.WDActivity;
import com.vc.wd.main.databinding.ActivityMainBinding;
import com.vc.wd.main.fragment.CircleFragment;
import com.vc.wd.main.fragment.HomeFragment;
import com.vc.wd.main.fragment.MeFragment;
import com.vc.wd.common.util.Constant;
import com.vc.wd.main.vm.MainViewModel;

@Route(path = Constant.ACTIVITY_URL_MAIN)
public class MainActivity extends WDActivity<MainViewModel, ActivityMainBinding> {

    private HomeFragment homeFragment;
    private CircleFragment circleFragment;
    private MeFragment meFragment;
    private Fragment currentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        homeFragment = new HomeFragment();
        circleFragment = new CircleFragment();
        meFragment = new MeFragment();

        currentFragment = homeFragment;
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.add(R.id.container, homeFragment)
                .show(homeFragment).commit();
        viewModel.cId.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer checkedId) {
                if (checkedId == R.id.home_btn) {
                    showFragment(homeFragment);
                } else if (checkedId == R.id.circle_btn) {
                    showFragment(circleFragment);
                }else if (checkedId == R.id.me_btn){
                    showFragment(meFragment);
                }
            }
        });
    }


    /**
     * 展示Fragment
     */
    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.container, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        circleFragment.onActivityResult(requestCode,resultCode,data);
    }
}
