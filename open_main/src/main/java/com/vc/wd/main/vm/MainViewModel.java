package com.vc.wd.main.vm;

import android.content.Intent;
import android.widget.RadioGroup;

import androidx.lifecycle.MutableLiveData;

import com.vc.wd.common.bean.Banner;
import com.vc.wd.common.bean.Circle;
import com.vc.wd.common.bean.shop.HomeList;
import com.vc.wd.common.core.DataCall;
import com.vc.wd.common.core.WDViewModel;
import com.vc.wd.common.core.exception.ApiException;
import com.vc.wd.common.util.Constant;
import com.vc.wd.main.request.IMainRequest;

import java.util.List;

public class MainViewModel extends WDViewModel<IMainRequest> {
    public MutableLiveData<Integer> cId = new MutableLiveData<>();
    public MutableLiveData<List<Banner>> bannerData = new MutableLiveData<>();
    public MutableLiveData<HomeList> homeListData = new MutableLiveData<>();

    public MutableLiveData<List<Circle>> circleData = new MutableLiveData<>();
    public MutableLiveData<Object[]> circleGreat = new MutableLiveData<>();
    public MutableLiveData<Void> addCircle = new MutableLiveData<>();


    public void onCheckedChanged(RadioGroup group, int checkId) {
        cId.setValue(checkId);
    }

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

    public void addCircle() {
        addCircle.setValue(null);
    }

    public void logout(){
        userInfoBox.remove(LOGIN_USER.getUserId());

        intentByRouter(Constant.ACTIVITY_URL_LOGIN);
        finish();//本页面关闭
    }

    public void setting(){
        intentByRouter(Constant.ACTIVITY_URL_SET);
    }


    private boolean circleRunning;
    private int circlePage;
    public void requestCircleData(boolean isRefresh){
        if (circleRunning)
            return;

        if (isRefresh){
            circlePage = 1;
        }else{
            circlePage++;
        }
        circleRunning = true;
        request(iRequest.findCircleList(LOGIN_USER.getUserId()
                ,LOGIN_USER.getSessionId(),circlePage,20),
                new DataCall<List<Circle>>(){

                    @Override
                    public void success(List<Circle> data) {
                        circleRunning = false;
                        circleData.setValue(data);
                    }

                    @Override
                    public void fail(ApiException data) {
                        circleRunning = false;
                        circleData.setValue(null);
                    }
                });
    }

    public boolean isCircleRunning() {
        return circleRunning;
    }

    public int getCirclePage() {
        return circlePage;
    }

    public void reqeustGreatCircle(final int position, final Circle circle) {
        request(iRequest.addCircleGreat(LOGIN_USER.getUserId(), LOGIN_USER.getSessionId(), circle.getId()),
                new DataCall() {
                    @Override
                    public void success(Object data) {
                        circleGreat.setValue(new Object[]{position,circle});
                    }

                    @Override
                    public void fail(ApiException data) {
                        circleGreat.setValue(null);
                    }
                });
    }
}
