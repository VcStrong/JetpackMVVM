package com.vc.wd.main.vm;

import androidx.lifecycle.MutableLiveData;

import com.vc.wd.common.bean.Banner;
import com.vc.wd.common.bean.shop.HomeList;
import com.vc.wd.common.core.DataCall;
import com.vc.wd.common.core.WDFragViewModel;
import com.vc.wd.common.core.exception.ApiException;
import com.vc.wd.common.util.Constant;
import com.vc.wd.main.request.IMainRequest;

import java.util.List;


public class HomeViewModel extends WDFragViewModel<IMainRequest> {

    public MutableLiveData<List<Banner>> bannerData = new MutableLiveData<>();
    public MutableLiveData<HomeList> homeListData = new MutableLiveData<>();

    @Override
    protected void create() {
        super.create();
        request(iRequest.bannerShow(), new DataCall<List<Banner>>() {
            @Override
            public void success(List<Banner> data) {
                bannerData.setValue(data);
            }

            @Override
            public void fail(ApiException e) {

            }
        });

        request(iRequest.commodityList(),new DataCall<HomeList>(){

            @Override
            public void success(HomeList data) {
                homeListData.setValue(data);
            }

            @Override
            public void fail(ApiException data) {

            }
        });
    }
}
