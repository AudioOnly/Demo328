package com.yao.rui.demo328.utils;

import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 魅族SmartBar工具类
 * Created by Rny on 2017/3/21.
 */

public class SmartBarUtils {

    /**
     * 此方法需要配合requestWindowFeature(Window.FEATURE_NO_TITLE)使用,缺点是程序无法使用系统actionbar
     *
     * @param decorView window.getDecorView
     */
    public static void hide(View decorView) {
        if (!hasSmartBar())
            return;
        try {
            @SuppressWarnings("rawtypes")
            Class[] arrayOfClass = new Class[1];
            arrayOfClass[0] = Integer.TYPE;
            Method localMethod = View.class.getMethod("setSystemUiVisibility",
                    arrayOfClass);
            Field localField = View.class
                    .getField("SYSTEM_UI_FLAG_HIDE_NAVIGATION");
            Object[] arrayOfObject = new Object[1];
            try {
                arrayOfObject[0] = localField.get(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            localMethod.invoke(decorView, arrayOfObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 新型号可用反射调用Build.hasSmartBar()来判断有无SmartBar
     *
     * @return true 有
     */
    public static boolean hasSmartBar() {
        try {
            Method method = Class.forName("android.os.Build").getMethod(
                    "hasSmartBar");
            return (Boolean) method.invoke(null);
        } catch (Exception e) {
        }
        if (Build.DEVICE.equals("mx2")) {
            return true;
        } else if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) {
            return false;
        }
        return false;
    }
}
