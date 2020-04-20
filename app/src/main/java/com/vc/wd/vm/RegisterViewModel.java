package com.vc.wd.vm;

import android.text.TextUtils;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.vc.wd.core.DataCall;
import com.vc.wd.core.WDApplication;
import com.vc.wd.core.WDViewModel;
import com.vc.wd.core.exception.ApiException;
import com.vc.wd.core.http.IAppRequest;
import com.vc.wd.util.MD5Utils;
import com.vc.wd.util.UIUtils;

public class RegisterViewModel extends WDViewModel<IAppRequest> {
    public ObservableField<Boolean> remPas = new ObservableField<>();
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
        if (remPas.get()) {
            WDApplication.getShare().edit().putString("mobile", m)
                    .putString("pas", p).commit();
        }
        dialog.setValue(true);

        request(iRequest.register(m, MD5Utils.md5(p)), new DataCall<Void>() {

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
