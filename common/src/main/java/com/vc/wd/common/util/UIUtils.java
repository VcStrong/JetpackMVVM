package com.vc.wd.common.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.vc.wd.common.core.WDActivity;
import com.vc.wd.common.core.WDApplication;

import java.io.File;
import java.io.FileInputStream;


public class UIUtils {

	public static Context getContext() {
		return WDApplication.getContext();
	}

	public static Thread getMainThread() {
		return WDApplication.getMainThread();
	}

	public static long getMainThreadId() {
		return WDApplication.getMainThreadId();
	}

	/** dip转换px */
	public static int dip2px(int dip) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

	/** pxz转换dip */
	public static int px2dip(int px) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	/** 获取主线程的handler */
	public static Handler getHandler() {
		return WDApplication.getMainThreadHandler();
	}

	public static Bitmap decodeFile(File f, int imageSize){
		try {
			//解码图像大小,对图片进行缩放...防止图片过大导致内存溢出...
			BitmapFactory.Options o = new BitmapFactory.Options();//实例化一个对象...

			o.inJustDecodeBounds = true;//这个就是Options的第一个属性,设置为true的时候，不会完全的对图片进行解码操作,不会为其分配内存，只是获取图片的基本信息...

			BitmapFactory.decodeStream(new FileInputStream(f),null,o); //以码流的形式进行解码....

			/*
			 * 下面也就是对图片进行的一个压缩的操作...如果图片过大，最后会根据指定的数值进行缩放...
			 * 找到正确的刻度值，它应该是2的幂.
			 * 这里我指定了图片的长度和宽度为200个像素...
			 *
			 * */

			int width_tmp=o.outWidth, height_tmp=o.outHeight;
			int scale=1;
			while(true){
				if(width_tmp/2<imageSize || height_tmp/2<imageSize)
					break;
				width_tmp/=2;
				height_tmp/=2;
				scale*=2;
			}

			BitmapFactory.Options o2 = new BitmapFactory.Options(); //这里定义了一个新的对象...获取的还是同一张图片...
			o2.inPreferredConfig = Config.RGB_565;
			o2.inSampleSize=scale;   //对这张图片设置一个缩放值...inJustDecodeBounds不需要进行设置...
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2); //这样通过这个方法就可以产生一个小的图片资源了...
		} catch (Exception e) {
		    e.printStackTrace();
        }
		return null;
	}

	/** 延时在主线程执行runnable */
	public static boolean postDelayed(Runnable runnable, long delayMillis) {
		return getHandler().postDelayed(runnable, delayMillis);
	}

	/** 在主线程执行runnable */
	public static boolean post(Runnable runnable) {
		return getHandler().post(runnable);
	}

	/** 从主线程looper里面移除runnable */
	public static void removeCallbacks(Runnable runnable) {
		getHandler().removeCallbacks(runnable);
	}

	public static View inflate(int resId) {
		return LayoutInflater.from(getContext()).inflate(resId, null);
	}

	/** 获取资源 */
	public static Resources getResources() {
		return getContext().getResources();
	}

	/** 获取文字 */
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/** 获取文字 */
	public static String getString(int resId, Object... params) {
		return getResources().getString(resId, params);
	}

	/** 获取文字数组 */
	public static String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	/** 获取dimen */
	public static int getDimens(int resId) {
		return getResources().getDimensionPixelSize(resId);
	}

	/** 获取drawable */
	public static Drawable getDrawable(int resId) {
		return getResources().getDrawable(resId);
	}

	/** 获取颜色 */
	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	/** 获取颜色选择器 */
	public static ColorStateList getColorStateList(int resId) {
		return getResources().getColorStateList(resId);
	}

	/**
	 * 得到屏幕宽度
	 * 
	 * @return
	 */
	public static int getWindowWidth() {
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}

	/**
	 * 得到16：9的高度
	 * 
	 * @return
	 */
	public static int get16And9Height() {
		double width = getWindowWidth();
		return (int) (width / 16.0 * 9);
	}

	// 判断当前的线程是不是在主线程
	public static boolean isRunInMainThread() {
		return android.os.Process.myTid() == getMainThreadId();
	}

	public static void runInMainThread(Runnable runnable) {
		if (isRunInMainThread()) {
			runnable.run();
		} else {
			post(runnable);
		}
	}

	/** 对toast的简易封装。线程安全，可以在非UI线程调用。 */
	public static void showToastSafe(final int resId) {
		showToastSafe(getString(resId));
	}

	/** 对toast的简易封装。线程安全，可以在非UI线程调用。 */
	public static void showToastSafe(final String str) {
		if (isRunInMainThread()) {
			showToast(str);
		} else {
			post(new Runnable() {
				@Override
				public void run() {
					showToast(str);
				}
			});
		}
	}

	private static void showToast(String str) {
		WDActivity frontActivity = WDActivity.getForegroundActivity();
		if (frontActivity != null) {
			Toast.makeText(frontActivity, str, Toast.LENGTH_LONG).show();
		}
	}
}
