package com.yao.rui.demo328.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yao.rui.demo328.DemoApplication;

import static com.yao.rui.demo328.DemoApplication.AppCtx;

/**
 * Created by Rny on 2017/3/29.
 */

public class SP {
    private SharedPreferences mSp;

    public SP() {

    }

    public SP(String name) {
        mSp = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
    }

    /**
     * 清楚数据
     */
    public void clear() {
        mSp.edit().clear().commit();
    }

    /**
     * 保存String数据
     *
     * @param key
     * @param str
     */
    public void save(String key, String str) {
        mSp.edit().putString(key, str);
    }

    /**
     * 获取String 数据
     *
     * @param key
     * @param defalutString
     * @return
     */
    public String load(String key, String defalutString) {
        return mSp.getString(key, defalutString);
    }

    public void save(String key, boolean value) {
        mSp.edit().putBoolean(key, value);
    }

    public boolean load(String key, boolean defaultBoolean) {
        return mSp.getBoolean(key, defaultBoolean);
    }


}
