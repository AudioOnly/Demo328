package com.yao.rui.demo328;

import android.app.Application;
import android.content.Context;

import com.yao.rui.demo328.utils.Toast;

import static com.yao.rui.demo328.utils.Toast.*;

/**
 * Created by Rny on 2017/3/29.
 */

public class DemoApplication extends Application {

    /**
     * App上下文实例缓存
     */
    public static Context AppCtx = null;
    /**
     * 是否上线模式
     */
    public static final boolean ONLINE=true;
    /**
     * Debug模式
     */
    public static final boolean DEBUG=true;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCtx=getApplicationContext();
        if (ONLINE) {
              show("测试模式");
        }
        if (DEBUG) {
            show("Debug模式");
        }

    }
}
