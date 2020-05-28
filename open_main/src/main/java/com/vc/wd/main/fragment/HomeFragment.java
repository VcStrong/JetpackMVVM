package com.vc.wd.main.fragment;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import com.vc.wd.main.R;
import com.vc.wd.main.adapter.CommodityAdpater;
import com.vc.wd.common.bean.Banner;
import com.vc.wd.common.bean.shop.HomeList;
import com.vc.wd.common.core.WDFragment;
import com.vc.wd.main.adapter.HomeBannerAdapter;
import com.vc.wd.main.databinding.FragMainBinding;
import com.vc.wd.common.util.recycleview.SpacingItemDecoration;
import com.vc.wd.main.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends WDFragment<MainViewModel, FragMainBinding> {

    private CommodityAdpater mHotAdapter, mFashionAdapter, mLifeAdapter;
    private HomeBannerAdapter mBannerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_main;
    }

    @Override
    protected void initView(Bundle bundle) {
        mHotAdapter = new CommodityAdpater(CommodityAdpater.HOT_TYPE);
        mFashionAdapter = new CommodityAdpater(CommodityAdpater.FASHION_TYPE);
        mLifeAdapter = new CommodityAdpater(CommodityAdpater.HOT_TYPE);

        binding.hotList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.fashionList.setLayoutManager(new GridLayoutManager(getContext(), 1));
        binding.lifeList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        int space = getResources().getDimensionPixelSize(R.dimen.dip_10);

        binding.hotList.addItemDecoration(new SpacingItemDecoration(space));
        binding.fashionList.addItemDecoration(new SpacingItemDecoration(space));
        binding.lifeList.addItemDecoration(new SpacingItemDecoration(space));

        binding.hotList.setAdapter(mHotAdapter);
        binding.fashionList.setAdapter(mFashionAdapter);
        binding.lifeList.setAdapter(mLifeAdapter);

        mBannerAdapter = new HomeBannerAdapter(new ArrayList<Banner>());
        binding.banner.setAdapter(mBannerAdapter)
                .addBannerLifecycleObserver(this)
                .setDelayTime(3000)
                .setBannerGalleryMZ(20);

        viewModel.bannerData.observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                mBannerAdapter.updateData(banners);
            }
        });

        viewModel.homeListData.observe(this, new Observer<HomeList>() {
            @Override
            public void onChanged(HomeList data) {
                mHotAdapter.addAll(data.getRxxp().getCommodityList());
                mFashionAdapter.addAll(data.getMlss().getCommodityList());
                mLifeAdapter.addAll(data.getPzsh().getCommodityList());
                mHotAdapter.notifyDataSetChanged();
                mFashionAdapter.notifyDataSetChanged();
                mLifeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.banner.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.banner.stop();
    }
}
