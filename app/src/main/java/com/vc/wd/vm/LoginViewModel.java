package com.vc.wd.vm;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.vc.wd.bean.UserInfo;
import com.vc.wd.core.DataCall;
import com.vc.wd.core.WDApplication;
import com.vc.wd.core.WDViewModel;
import com.vc.wd.core.exception.ApiException;
import com.vc.wd.core.http.IAppRequest;
import com.vc.wd.util.Constant;
import com.vc.wd.util.MD5Utils;
import com.vc.wd.util.UIUtils;

public class LoginViewModel extends WDViewModel<IAppRequest> {

    public ObservableField<Boolean> remPas = new ObservableField<>();
    public ObservableField<String> mobile = new ObservableField<>();
    public ObservableField<String> pas = new ObservableField<>();
    public MutableLiveData<Boolean> pasVis = new MutableLiveData<>();

    public void pasVisibility(){
        pasVis.setValue(pasVis.getValue()==null?false:!pasVis.getValue());
    }

    @Override
    protected void create() {
        super.create();
        boolean rem = WDApplication.getShare().getBoolean("remPas", true);
        if (rem) {
            remPas.set(rem);
            mobile.set(WDApplication.getShare().getString("mobile", ""));
            pas.set(WDApplication.getShare().getString("pas", ""));
        }
    }

    public void login() {
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

        request(iRequest.login(m, MD5Utils.md5(p)), new DataCall<UserInfo>() {
            @Override
            public void success(UserInfo result) {
                dialog.setValue(false);
                result.setStatus(1);//设置登录状态，保存到数据库
                userInfoBox.put(result);
                intentByRouter(Constant.ACTIVITY_URL_MAIN);
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
