package com.vc.wd.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class WDRecyclerAdapter<T> extends RecyclerView.Adapter<WDRecyclerAdapter.MyHodler> {

    protected List<T> mList = new ArrayList<>();

    public void addAll(List<T> list) {
        if (list != null)
            mList.addAll(list);
    }

    public void clear() {
        mList.clear();
    }

    public void add(T item) {
        if (item != null) {
            mList.add(item);
        }
    }

    public T getItem(int position){
        return mList.get(position);
    }

    public void remove(T item){
        mList.remove(item);
    }

    @NonNull
    @Override
    public MyHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(),
                parent, false);
        initBindingField(parent,binding);
        return new MyHodler(binding.getRoot());
    }

    protected void initBindingField(ViewGroup parent,ViewDataBinding binding){}

    protected abstract int getLayoutId();

    @Override
    public void onBindViewHolder(@NonNull MyHodler holder, int position) {
        bindView(DataBindingUtil.bind(holder.itemView),getItem(position), position);
    }

    protected abstract void bindView(ViewDataBinding binding,T item, int position);


    public List<T> getList() {
        return mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyHodler extends RecyclerView.ViewHolder {
        public MyHodler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
