package com.yao.rui.pagerv.rv;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 *基础 RecyclerView
 * 在init中完成RV初始化、绑定适配器
 * Created by Rny on 2017/4/5.
 */

public class PRecyclerView extends RecyclerView {
    public PRecyclerView(Context context) {
        super(context);
    }

    public PRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 初始化 RecyclerView
     * @param layoutManager 布局
     * @param isHasFixedSize 是否设置固定布局，true 固定，false 不固定
     * @param adapter 重写的 BaseRecyclerViewAdapter 适配器
     */
    public void init(LayoutManager layoutManager,boolean isHasFixedSize,BaseRecyclerViewAdapter adapter){
        setLayoutManager(layoutManager);
        setHasFixedSize(isHasFixedSize);
        setAdapter(adapter);
        //默认去除item动画
        setItemAnimator(null);
    }
}
