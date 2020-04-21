package com.vc.wd.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.vc.wd.main.R;
import com.vc.wd.main.adapter.CircleAdpater;
import com.vc.wd.common.bean.Circle;
import com.vc.wd.common.core.WDFragment;
import com.vc.wd.main.databinding.FragCircleBinding;
import com.vc.wd.common.util.Constant;
import com.vc.wd.common.util.UIUtils;
import com.vc.wd.common.util.recycleview.SpacingItemDecoration;
import com.vc.wd.main.vm.MainViewModel;

import java.util.List;

/**
 * @author dingtao
 * @date 2019/1/2 10:28
 * qq:1940870847
 */
public class CircleFragment extends WDFragment<MainViewModel, FragCircleBinding> implements XRecyclerView.LoadingListener, CircleAdpater.GreatListener {

    public static boolean addCircle;//如果添加成功，则为true

    private CircleAdpater mCircleAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_circle;
    }

    @Override
    protected void initView(Bundle bundle) {

        mCircleAdapter = new CircleAdpater();

        binding.circleList.setLayoutManager(new GridLayoutManager(getContext(), 1));

        int space = getResources().getDimensionPixelSize(R.dimen.dip_20);

        binding.circleList.addItemDecoration(new SpacingItemDecoration(space));

        mCircleAdapter.setGreatListener(this);

        binding.circleList.setAdapter(mCircleAdapter);
        binding.circleList.setLoadingListener(this);

        viewModel.circleData.observe(getActivity(), new Observer<List<Circle>>() {
            @Override
            public void onChanged(List<Circle> circles) {
                if (circles == null)
                    return;
                binding.circleList.refreshComplete();
                binding.circleList.loadMoreComplete();
                //添加列表并刷新
                if (viewModel.getCirclePage() == 1) {
                    mCircleAdapter.clear();
                }
                mCircleAdapter.addAll(circles);
                mCircleAdapter.notifyDataSetChanged();
            }
        });

        viewModel.circleGreat.observe(getActivity(), new Observer<Object[]>() {
            @Override
            public void onChanged(Object[] objects) {
                if (objects==null){
                    UIUtils.showToastSafe("点赞失败");
                }else{
                    int position = (int) objects[1];
                    UIUtils.showToastSafe("点击" + position + "    adapter条目：" + mCircleAdapter.getItemCount()
                            + "    recycler条目：" + binding.circleList.getChildCount());
                    Circle circle = (Circle) objects[2];
                    Circle nowCircle = mCircleAdapter.getItem(position);
                    if (nowCircle.getId() == circle.getId()) {
                        nowCircle.setGreatNum(nowCircle.getGreatNum() + 1);
                        nowCircle.setWhetherGreat(1);
                        mCircleAdapter.notifyItemChanged(position + 1, 0);
                    }
                }
            }
        });

        viewModel.addCircle.observe(getActivity(), new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                intentForResultByRouter(Constant.ACTIVITY_URL_ADD_CIRCLE,100);
            }
        });

        // 设置数据
        binding.circleList.refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (addCircle) {//publish new message,so you have to refresh
            addCircle = false;
            binding.circleList.refresh();
        }
    }

    @Override
    public void onRefresh() {
        if (viewModel.isCircleRunning()) {
            binding.circleList.refreshComplete();
            return;
        }
        viewModel.requestCircleData(true);
    }

    @Override
    public void onLoadMore() {
        if (viewModel.isCircleRunning()) {
            binding.circleList.loadMoreComplete();
            return;
        }
        viewModel.requestCircleData(false);
    }

    @Override
    public void great(int position, Circle circle) {
        viewModel.reqeustGreatCircle(position, circle);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode== Activity.RESULT_OK){
            binding.circleList.refresh();
        }
    }
}
