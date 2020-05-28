package com.vc.wd.login.vm;

import android.text.TextUtils;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.vc.wd.common.core.DataCall;
import com.vc.wd.common.core.WDViewModel;
import com.vc.wd.common.core.exception.ApiException;
import com.vc.wd.common.util.UIUtils;
import com.vc.wd.login.request.ILoginRequest;

public class RegisterViewModel extends WDViewModel<ILoginRequest> {
    public ObservableField<String> mobile = new ObservableField<>();
    public ObservableField<String> pas = new ObservableField<>();
    public MutableLiveData<Boolean> pasVis = new MutableLiveData<>();

    public void pasVisibility(){
        pasVis.setValue(pasVis.getValue()==null?false:!pasVis.getValue());
    }

    public void register() {
        String m = mobile.get();
        String p = pas.get();
        if (TextUtils.isEmpty(m)) {
            UIUtils.showToastSafe("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(p)) {
            UIUtils.showToastSafe("请输入密码");
            return;
        }
        dialog.setValue(true);

        request(iRequest.register(m, p), new DataCall<Void>() {

            @Override
            public void success(Void data) {
                dialog.setValue(false);
                UIUtils.showToastSafe("注册成功，请登录");
                finish();
            }

            @Override
            public void fail(ApiException e) {
                dialog.setValue(false);
                UIUtils.showToastSafe(e.getCode() + " " + e.getDisplayMessage());
            }
        });
    }
}
