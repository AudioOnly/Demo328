package com.yao.rui.demo328.utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.yao.rui.demo328.DemoApplication.DEBUG;

/**
 * Log 日志类
 * Created by Rny on 2017/3/29.
 */

public class L {
    private String tag;

    public L(Class<?> cls) {
        this(cls.getSimpleName());
    }

    public L(String tag) {
        this.tag = tag;
    }

    public void d(String msg) {
        if (DEBUG)
            Log.d(tag,msg);
    }

    public void e(String msg) {
        if (DEBUG)
            Log.e(tag,msg);
    }
    public void e(String msg,Throwable throwable) {
        if (DEBUG)
            Log.e(tag, msg+"\n"+getExceptiontoString(throwable));
    }

    /**
     * 获取异常字符串
     * @param throwable
     * @return
     */
    private String getExceptiontoString(Throwable throwable){
        try {
            StringWriter sw=new StringWriter();
            PrintWriter pw=new PrintWriter(sw);
            throwable.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e) {
            return "获取以信息内容异常";
        }
    }
}
