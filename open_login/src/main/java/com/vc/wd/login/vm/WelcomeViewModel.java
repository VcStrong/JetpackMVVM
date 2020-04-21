package com.vc.wd.login.vm;

import android.os.Handler;
import android.os.Message;

import androidx.databinding.ObservableField;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.vc.wd.common.core.WDViewModel;
import com.vc.wd.common.core.http.IAppRequest;
import com.vc.wd.common.util.Constant;

public class WelcomeViewModel extends WDViewModel<IAppRequest> {
    public ObservableField<String> seekText = new ObservableField<>();
    private int count = 3;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (count==0){
                if (LOGIN_USER!=null){
                    intentByRouter(Constant.ACTIVITY_URL_MAIN);//跳转到主页面
                }else{
                    intentByRouter(Constant.ACTIVITY_URL_LOGIN);//跳转到登录页
                }
                finish();
            }else{//消息不能复用，必须新建
                seekText.set("跳过"+count+"s");
                count--;
                handler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };

    @Override
    protected void create() {
        super.create();
        handler.sendEmptyMessage(1);
    }

    public void seek(){
        handler.removeMessages(1);
        if (LOGIN_USER!=null){
            intentByRouter(Constant.ACTIVITY_URL_MAIN);
        }else{
            intentByRouter(Constant.ACTIVITY_URL_LOGIN);
        }
        finish();
    }
}
