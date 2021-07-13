package com.vc.wd.main.vm;

import android.view.View;
import android.widget.RadioGroup;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.vc.wd.common.core.WDViewModel;
import com.vc.wd.common.util.Constant;
import com.vc.wd.main.BuildConfig;
import com.vc.wd.main.request.IMainRequest;

public class MainFromViewPagerViewModel extends WDViewModel<IMainRequest> {
    public MutableLiveData<Integer> cId = new MutableLiveData<>();
    public ObservableField<Boolean> debug = new ObservableField<>();

    @Override
    protected void create() {
        super.create();
        debug.set(BuildConfig.DEBUG);
    }

    public void click(View view) {
        cId.setValue(view.getId());
    }

    public void debug() {
        intentByRouter(Constant.ACTIVITY_URL_DEBUG);
    }
}
