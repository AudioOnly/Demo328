package com.yao.rui.pagerv.refresh;

/**
 * 下拉刷新 弄能接口
 * Created by Rny on 2017/4/5.
 */

public interface PageRefreshView {

    /**
     * 设置监听
     * @param listener 下拉刷新监听
     */
    void setListener(OnPageRefreshListener listener);

    /**
     * 显示刷新控件
     * @param show  true 显示；false 隐藏
     */
    void showRefreshView(boolean show);

}
