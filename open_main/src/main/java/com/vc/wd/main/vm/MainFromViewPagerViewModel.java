package com.vc.wd.main.vm;

import android.view.View;
import android.widget.RadioGroup;

import androidx.lifecycle.MutableLiveData;

import com.vc.wd.common.core.WDViewModel;
import com.vc.wd.main.request.IMainRequest;

public class MainFromViewPagerViewModel extends WDViewModel<IMainRequest> {
    public MutableLiveData<Integer> cId = new MutableLiveData<>();

    public void click(View view){
        cId.setValue(view.getId());
    }
}
