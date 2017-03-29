package com.yao.rui.demo328.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.yao.rui.demo328.activitys.BaseActivity;
import com.yao.rui.demo328.utils.L;

import butterknife.ButterKnife;
import rx.Observable;

/**
 * Fragment基础类
 * Created by Rny on 2017/3/29.
 */

public abstract class BaseFragment extends RxFragment {
    //懒加载 界面可见
    protected boolean isVisible;
    //判断数据是否加载过，防止重复加载数据
    protected boolean isLoad;
    L l=new L(BaseFragment.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        l.e("onCreateView");
        return inflater.inflate(getLayoutID(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        l.e("onViewCreated");
        //注册ButterKnife
        ButterKnife.bind(this,view);
        init(view,savedInstanceState);
    }
    //懒加载===================================
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        l.e(getUserVisibleHint()+""+isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            onVisible();
        } else {
            isVisible=false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {

    }

    //RxLifecycle===================================
    public <T> Observable<T> rxDestory(Observable<T> observable) {
        return observable.compose(bindUntilEvent(FragmentEvent.DESTROY));
    }

    //abstract===================================

    /**
     * 获取Fragment布局ID
     * @return
     */
    protected abstract int getLayoutID();

    /**
     * 初始化完成，替代{@link #onViewCreated(View, Bundle)}
     * @param view
     * @param savedInsanceState
     */
    protected abstract void init(View view,Bundle savedInsanceState);

    /**
     * 懒加载
     */
    protected abstract void lazyLoad();

    //Toast=================================
    public void toast(String str){
        ((BaseActivity)getActivity()).toast(str);
    }
    public void toast(int resId){
        ((BaseActivity)getActivity()).toast(resId);
    }


}
