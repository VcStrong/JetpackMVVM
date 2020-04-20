package com.vc.wd.core;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingtao
 * @date 2019/1/10 16:39
 * qq:1940870847
 */
public abstract class WDListAdpater<T,MH extends WDListAdpater.Hodler> extends BaseAdapter {

    List<T> mList = new ArrayList<>();

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MH mh;
        if (convertView==null){
            convertView = View.inflate(parent.getContext(),getLayoutId(),null);
            mh = creatHolder(convertView);
            convertView.setTag(mh);
        }else{
            mh = (MH) convertView.getTag();
        }
        bindItemView(mh,position);
        return convertView;
    }

    protected abstract void bindItemView(MH mh,int position);

    public abstract int getLayoutId();

    public abstract class Hodler{
        public View itemView;
        public Hodler(View itemView){
            this.itemView = itemView;
        }
    }

    public abstract MH creatHolder(View view);

}
