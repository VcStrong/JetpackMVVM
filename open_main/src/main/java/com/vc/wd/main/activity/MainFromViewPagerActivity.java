package com.vc.wd.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.vc.wd.common.core.WDActivity;
import com.vc.wd.common.core.WDFragment;
import com.vc.wd.common.util.Constant;
import com.vc.wd.main.R;
import com.vc.wd.main.databinding.ActivityMainViewPagerBinding;
import com.vc.wd.main.fragment.CircleFragment;
import com.vc.wd.main.fragment.HomeFragment;
import com.vc.wd.main.fragment.TestFragment;
import com.vc.wd.main.vm.MainFromViewPagerViewModel;

import java.util.LinkedHashMap;
import java.util.Map;

@Route(path = Constant.ACTIVITY_URL_MAIN)
public class MainFromViewPagerActivity extends WDActivity<MainFromViewPagerViewModel, ActivityMainViewPagerBinding> {

    private HomeFragment homeFragment;
    private CircleFragment circleFragment;
    private TestFragment carFragment;
    private TestFragment orderFragment;
    private WDFragment meFragment;
    private Fragment currentFragment;
    private Map<Integer, Integer> idsMap;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_view_pager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        homeFragment = new HomeFragment();
        circleFragment = new CircleFragment();
        carFragment = new TestFragment();
        orderFragment = new TestFragment();
        meFragment = (WDFragment) ARouter.getInstance().build(Constant.FRAGMENT_URL_ME).navigation();
        if (meFragment != null) {//加载组件之后再赋值
            viewModel.addFragViewModel(meFragment.getFragViewModel());
        }

        idsMap = new LinkedHashMap<>();
        idsMap.put(0, R.id.home_btn);
        idsMap.put(1, R.id.circle_btn);
        idsMap.put(2, R.id.cart_btn);
        idsMap.put(3, R.id.list_btn);
        idsMap.put(4, R.id.me_btn);

        idsMap.put(R.id.home_btn, 0);
        idsMap.put(R.id.circle_btn, 1);
        idsMap.put(R.id.cart_btn, 2);
        idsMap.put(R.id.list_btn, 3);
        idsMap.put(R.id.me_btn, 4);

        viewModel.addFragViewModel(homeFragment.getFragViewModel());
        viewModel.addFragViewModel(circleFragment.getFragViewModel());

        currentFragment = homeFragment;

        binding.pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        currentFragment = homeFragment;
                        break;
                    case 1:
                        currentFragment = circleFragment;
                        break;
                    case 2:
                        currentFragment = carFragment;
                        break;
                    case 3:
                        currentFragment = orderFragment;
                        break;
                    case 4:
                        currentFragment = meFragment;
                        break;
                }
                return currentFragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.bottomMenu.check(idsMap.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewModel.cId.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer checkedId) {
                binding.pager.setCurrentItem(idsMap.get(checkedId));
            }
        });

        viewModel.fragDataShare.observe(this, new Observer<Message>() {
            @Override
            public void onChanged(Message message) {
                if (message.what == 100) {
                    binding.bottomMenu.check(R.id.home_btn);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        circleFragment.onActivityResult(requestCode, resultCode, data);
    }
}
