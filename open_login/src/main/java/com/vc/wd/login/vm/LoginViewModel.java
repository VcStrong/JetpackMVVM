package com.vc.wd.login.vm;

import android.text.TextUtils;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.vc.wd.common.bean.UserInfo;
import com.vc.wd.common.core.DataCall;
import com.vc.wd.common.core.WDApplication;
import com.vc.wd.common.core.WDViewModel;
import com.vc.wd.common.core.exception.ApiException;
import com.vc.wd.common.util.Constant;
import com.vc.wd.common.util.UIUtils;
import com.vc.wd.login.request.ILoginRequest;

public class LoginViewModel extends WDViewModel<ILoginRequest> {

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

        request(iRequest.login(m, p), new DataCall<UserInfo>() {
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

    public void register(){
        intentByRouter(Constant.ACTIVITY_URL_REGISTER);
    }
}
