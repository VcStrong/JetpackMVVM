package com.vc.wd.main.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vc.wd.main.R;
import com.vc.wd.common.bean.shop.Commodity;
import com.vc.wd.common.core.WDRecyclerAdapter;
import com.vc.wd.main.databinding.FashionItemBinding;
import com.vc.wd.main.databinding.HotItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CommodityAdpater extends WDRecyclerAdapter<Commodity> {

    public static final int HOT_TYPE = 0;
    public static final int FASHION_TYPE = 1;
    private int type;

    public CommodityAdpater( int type){
        this.type = type;
    }

    @Override
    protected int getLayoutId() {
        if (type == HOT_TYPE) {
            return R.layout.hot_item;
        }else {
            return R.layout.fashion_item;
        }
    }

    @Override
    protected void bindView(ViewDataBinding binding,Commodity commodity, int position) {
        if (type == HOT_TYPE) {
            HotItemBinding h = (HotItemBinding) binding;
            setValue(commodity,h.image,h.price,h.text,h.getRoot());
        }else {
            FashionItemBinding f = (FashionItemBinding) binding;
            setValue(commodity,f.image,f.price,f.text,f.getRoot());
        }
    }

    private void setValue(Commodity commodity,SimpleDraweeView image,TextView price,TextView text,View itemView){
        image.setImageURI(Uri.parse(commodity.getMasterPic()));
        price.setText(commodity.getPrice()+"");
        text.setText(commodity.getCommodityName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取分类id
//                intent.putExtras("id",)

            }
        });
    }
}
