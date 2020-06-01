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

    public void onCheckedChanged(RadioGroup group, int checkId) {
        cId.setValue(checkId);
    }

}
