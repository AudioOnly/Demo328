package com.yao.rui.demo328.activitys;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Rny on 2017/3/28.
 */

public abstract class BaseActivity extends RxAppCompatActivity{

    private final static List<Activity> ACTIVITIES=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACTIVITIES.add(this);
        //设置屏幕方向 竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //配置布局
        setContentView(getLayoutID());
        //注册 ButterKnife
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ACTIVITIES.remove(this);
    }
    //=======================================

    /**
     * 结束指定class的Activity。
     * 因为在遍历一个列表的时候不能执行删除操作，所有我们先记住要删除的对象，遍历之后才去删除。
     * @param cla
     */
    protected void finishSingleActivityByClass(Class<?> cla){
        Activity tempActivity=null;
        for (Activity act : ACTIVITIES) {
            if (act.getClass().equals(cla)){
                tempActivity=act;
            }
        }
        finishSingleActivity(tempActivity);
    }

    /**
     * 删除指定的Activity
     * @param activity
     */
    protected void finishSingleActivity(Activity activity){
        if (activity!=null) {
            if (ACTIVITIES.contains(activity)) {
                ACTIVITIES.remove(activity);
            }
            activity.finish();
            activity=null;
        }
    }

    /**
     * 退出其它页面除了当前页面
     */
    protected void exitWithoutThis() {
        for (Activity act : ACTIVITIES) {
            if (act == this) { //当前页面跳过
                continue;
            }
            act.finish();
        }
    }

    /**
     * 当前页是否是最后一个页面
     */
    protected boolean isLastAct() {
        return ACTIVITIES.size() == 1 && ACTIVITIES.contains(this);
    }

    //退出
    protected void exit(){
        for (Activity act:ACTIVITIES)
            act.finish();
    }
    //=======================================

    /**
     * Rx生命周期，当Activity触发Destory时候，结束Observable
     * @param observable
     * @param <T>
     * @return
     */
    public <T> Observable<T> rxDestory(Observable<T> observable){
        return observable.compose(bindUntilEvent(ActivityEvent.DESTROY));
    }

    //=======================================

    /**
     * 初始化布局
     * @return layoutID
     */
    protected abstract int getLayoutID();

    /**
     * 初始化完布局后，替代 {@link #onCreate(Bundle)}
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    //=======================================



}
