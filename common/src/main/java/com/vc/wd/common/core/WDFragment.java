package com.vc.wd.common.core;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.vc.wd.common.BR;
import com.vc.wd.common.bean.UserInfo;
import com.vc.wd.common.bean.UserInfo_;
import com.vc.wd.common.util.LogUtils;

import java.lang.reflect.ParameterizedType;

import io.objectbox.Box;


public abstract class WDFragment<VM extends WDViewModel,VDB extends ViewDataBinding> extends Fragment {

	protected VM viewModel;
	protected VDB binding;

	protected Box<UserInfo> userInfoBox;
	protected UserInfo LOGIN_USER;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		// 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
		long time = System.currentTimeMillis();
		userInfoBox = WDApplication.getBoxStore().boxFor(UserInfo.class);
		LOGIN_USER = userInfoBox.query()
				.equal(UserInfo_.status,1)
				.build().findUnique();
		ARouter.getInstance().inject(this);
		binding = DataBindingUtil.inflate(inflater, getLayoutId(),container,false);
		//所有布局中dababinding对象变量名称都是vm
		viewModel = new ViewModelProvider(getActivity()).get(getTClass());
		binding.setVariable(BR.vm,viewModel);
		initView(savedInstanceState);
		LogUtils.e(this.toString()+"页面加载使用："+(System.currentTimeMillis()-time));
		return binding.getRoot();
	}

	/**
	 * 获取泛型对相应的Class对象
	 * @return
	 */
	private Class<VM>  getTClass(){
		//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		//返回表示此类型实际类型参数的 Type 对象的数组()，想要获取第二个泛型的Class，所以索引写1
		return (Class)type.getActualTypeArguments()[0];//<T>
	}

	/**
	 * 设置layoutId
	 * @return
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化视图
	 */
	protected abstract void initView(Bundle savedInstanceState);

	/**
	 * @param path 传送Activity的
	 */
	public void intentByRouter(String path) {
		ARouter.getInstance().build(path)
				.navigation(getContext());
	}

	/**
	 * @param path 传送Activity的
	 * @param bundle
	 */
	public void intentByRouter(String path, Bundle bundle) {
		ARouter.getInstance().build(path)
				.with(bundle)
				.navigation(getContext());
	}

	/**
	 * @param path 传送Activity的
	 * @param bundle
	 */
	public void intentForResultByRouter(String path, Bundle bundle,int requestCode) {
		ARouter.getInstance().build(path)
				.with(bundle)
				.navigation(getActivity(),requestCode);
	}

	/**
	 * @param path 传送Activity的
	 */
	public void intentForResultByRouter(String path,int requestCode) {
		ARouter.getInstance().build(path)
				.navigation(getActivity(),requestCode);
	}

}
