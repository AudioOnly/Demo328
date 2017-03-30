package com.yao.rui.demo328.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * 状态栏工具类
 * Created by Rny on 2017/3/21.
 */

public class StatusBarUils {

    /**
     * 获取状态栏高度
     *
     * @param ctx 上下文
     * @return 0 没有获取到高度(px)
     */
    public static int getStatusBarHeight(Context ctx) {
        Resources res = ctx.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return res.getDimensionPixelOffset(resourceId);
        }
        return 0;
    }

    /**
     * 是否支持透明状态栏
     *
     * @param ctx 上下文
     * @return true支持
     */
    public static boolean canTranslucentStatusBar(Context ctx) {
        return getStatusBarHeight(ctx) != 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT; //可以获取状态栏高度、版本大于4.4
    }


    /**
     * 透明状态栏
     *
     * @param act                   Activity
     * @param fitsSystemWindowsView 填充状态栏颜色的View
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void translucentStatusBar(Activity act, View fitsSystemWindowsView) {
        if (canTranslucentStatusBar(act)) { //可以透明状态栏
            act.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            setStatusBarColor(fitsSystemWindowsView);
        }
    }


    /**
     * 只填充状态栏颜色，不设置Activity状态栏透明{@link #translucentStatusBar(Activity, View)}
     *
     * @param fragment              fragment
     * @param fitsSystemWindowsView 填充状态栏颜色的View
     */
    public static void translucentStatusBar(Fragment fragment, View fitsSystemWindowsView) {
        if (canTranslucentStatusBar(fragment.getContext())) { //可以透明状态栏
            translucentStatusBar(fragment.getActivity(), null);
            setStatusBarColor(fitsSystemWindowsView);
        }
    }
    /**
     * 图片作背景时，状态栏透明（沉浸式使用方法）
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void setTranslucentImageStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }


    /**
     * 设置状态栏颜色通过View
     *
     * @param view view
     */
    private static void setStatusBarColor(View view) {
        if (view != null) { //存在填充View,填充状态栏颜色
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(view.getContext()),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }
}
