package com.yao.rui.pagerv.status;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 基础状态view
 * Created by Rny on 2017/4/1.
 */

public abstract class PageStatusView extends RelativeLayout {
    public PageStatusView(Context context) {
        super(context);
        init();
    }

    public PageStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PageStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PageStatusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //初始化
    private void init() {
        addView(getContentView());
        initViews();
    }

    /**
     * 加载布局
     *
     * @param layoutID 布局id
     * @return
     */
    protected View inflater(int layoutID) {
        return LayoutInflater.from(getContext()).inflate(layoutID, this, false);
    }

    /**
     * 正在进行
     */
    public void progress() {
        showStatus(true, false, false);
    }

    /**
     * 失败
     *
     * @param msg
     */
    public void failed(String msg) {
        setFailedMsg(msg);
        showStatus(false, true, false);
    }

    /**
     * 空
     */
    public void isEmpty(){
        showStatus(false,false,true);
    }

    /**
     * 成功，隐藏空间
     */
    public void success() {
        setVisibility(GONE);
    }

    /**
     * 重置UI默认样式
     */
    public void resetUI() {
        progress();
        setVisibility(VISIBLE);
    }

    /**
     * 获取添加view， {@link #inflater(int)}
     *
     * @return
     */
    protected abstract View getContentView();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 默认文本信息
     *
     * @param progMsg  ProgressBar 文字
     * @param emptyMsg 数据空
     */
    public abstract void setDefaultMsg(String progMsg, String emptyMsg);

    /**
     * 失败信息文本
     *
     * @param msg
     */
    public abstract void setFailedMsg(String msg);

    /**
     * 显示状态
     *
     * @param progress 正在进行
     * @param failed   失败状态
     * @param empty    数据为空
     */
    protected abstract void showStatus(boolean progress, boolean failed, boolean empty);

    /**
     * 是否为空
     */
    public abstract boolean isEmptyShow();

    /**
     * 添加 失败点击监听
     * @param onClickListener
     */
    public abstract void onFailedClickListener(OnClickListener onClickListener);

}
