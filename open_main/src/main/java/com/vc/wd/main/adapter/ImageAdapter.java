package com.vc.wd.main.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.vc.wd.main.R;
import com.vc.wd.common.core.WDActivity;
import com.vc.wd.common.core.WDRecyclerAdapter;
import com.vc.wd.main.databinding.CircleImageItemBinding;
import com.vc.wd.common.util.UIUtils;

import java.util.List;

public class ImageAdapter extends WDRecyclerAdapter<Object> {

    private int sign;//0:普通点击，1自定义

    public void addStringListAll(List<String> list) {
        mList.addAll(list);
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.circle_image_item;
    }

    @Override
    protected void bindView(ViewDataBinding b,Object o, int position) {
        CircleImageItemBinding binding = (CircleImageItemBinding) b;
        if (o instanceof String) {
            String imageUrl = (String) o;
            if (imageUrl.contains("http:")) {//加载http
                binding.circleImage.setImageURI(Uri.parse(imageUrl));
            } else {//加载sd卡
                Uri uri = Uri.parse("file://" + imageUrl);
                binding.circleImage.setImageURI(uri);
            }
        } else {//加载资源文件
            int id = (int) o;
            Uri uri = Uri.parse("res://com.dingtao.rrmmp/" + id);
            binding.circleImage.setImageURI(uri);
        }

        binding.getRoot().setTag(position);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sign == 1) {//自定义点击
                    int p = (int)v.getTag();
                    if (p == 0) {

                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        WDActivity.getForegroundActivity().startActivityForResult(intent, WDActivity.PHOTO);
                    } else {
                        UIUtils.showToastSafe("点击了图片");
                    }
                } else {
                    UIUtils.showToastSafe("点击了图片");
                }
            }
        });
    }
}
