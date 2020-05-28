package com.vc.wd.main.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.vc.wd.common.bean.Banner;
import com.vc.wd.main.R;
import com.vc.wd.main.databinding.BannerItemBinding;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class HomeBannerAdapter extends BannerAdapter<Banner, HomeBannerAdapter.ImageHolder> {

    public HomeBannerAdapter(List<Banner> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
    }

    //更新数据
    public void updateData(List<Banner> data) {
        //这里的代码自己发挥，比如如下的写法等等
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }


    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.banner_item, parent, false);
        return new ImageHolder(binding.getRoot());
    }

    @Override
    public void onBindView(ImageHolder holder, Banner data, int position, int size) {
        BannerItemBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.bannerImage.setImageURI(Uri.parse(data.getImageUrl()));
    }

    static class ImageHolder extends RecyclerView.ViewHolder {

        public ImageHolder(@NonNull View view) {
            super(view);
        }
    }
}
