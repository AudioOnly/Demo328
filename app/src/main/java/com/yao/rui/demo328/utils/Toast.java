package com.yao.rui.demo328.utils;

import android.text.TextUtils;

import com.yao.rui.demo328.DemoApplication;

import static com.yao.rui.demo328.DemoApplication.AppCtx;

/**
 * Toast工具类
 * Created by Rny on 2017/3/29.
 */

public class Toast {

    /**
     * Toast
     * @param resId 资源id
     */
    public static void show(int resId){
        show(AppCtx.getResources().getString(resId));
    }

    /**
     * Toast
     * @param text 文本
     */
    public static void show(String text){
        if (TextUtils.isEmpty(text)){
            return;
        }
        android.widget.Toast.makeText(AppCtx,text, android.widget.Toast.LENGTH_SHORT).show();
    }
}
