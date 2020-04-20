package com.vc.wd.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vc.wd.R;
import com.vc.wd.adapter.CommodityAdpater;
import com.vc.wd.bean.Banner;
import com.vc.wd.bean.shop.HomeList;
import com.vc.wd.core.WDFragment;
import com.vc.wd.databinding.BannerItemBinding;
import com.vc.wd.databinding.FragMainBinding;
import com.vc.wd.util.recycleview.SpacingItemDecoration;
import com.vc.wd.vm.MainViewModel;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

/**
 * @author dingtao
 * @date 2019/1/2 10:28
 * qq:1940870847
 */
public class HomeFragment extends WDFragment<MainViewModel, FragMainBinding> {

    private CommodityAdpater mHotAdapter, mFashionAdapter, mLifeAdapter;

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

        viewModel.bannerData.observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                binding.banner.setIndicatorVisible(false);
                binding.banner.setDuration(3000);
                binding.banner.setPages(banners, new MZHolderCreator<HomeFragment.BannerViewHolder>() {
                    @Override
                    public HomeFragment.BannerViewHolder createViewHolder() {
                        return new HomeFragment.BannerViewHolder();
                    }
                });
                binding.banner.start();
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
    public void onPause() {
        super.onPause();
        binding.banner.pause();
    }

    /**
     * @author dingtao
     * @date 2019/1/3 9:21 AM
     * banner
     */
    class BannerViewHolder implements MZViewHolder<Banner> {
        BannerItemBinding bannerItemBinding;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            bannerItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.banner_item,null,false);
            return bannerItemBinding.getRoot();
        }

        @Override
        public void onBind(Context context, int position, Banner data) {
            // 数据绑定
            bannerItemBinding.bannerImage.setImageURI(Uri.parse(data.getImageUrl()));
        }
    }
}
