package com.vc.wd.main.vm;

import androidx.lifecycle.MutableLiveData;

import com.vc.wd.common.bean.Circle;
import com.vc.wd.common.core.DataCall;
import com.vc.wd.common.core.WDFragViewModel;
import com.vc.wd.common.core.exception.ApiException;
import com.vc.wd.common.util.Constant;
import com.vc.wd.main.request.IMainRequest;

import java.util.List;


public class CircleViewModel extends WDFragViewModel<IMainRequest> {
    public MutableLiveData<List<Circle>> circleData = new MutableLiveData<>();
    public MutableLiveData<Void> addCircle = new MutableLiveData<>();

    public MutableLiveData<Object[]> circleGreat = new MutableLiveData<>();

    public void addCircle() {
        addCircle.setValue(null);
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
