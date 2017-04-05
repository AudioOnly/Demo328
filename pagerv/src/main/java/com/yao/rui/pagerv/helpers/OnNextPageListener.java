package com.yao.rui.pagerv.helpers;

/**
 * 分页加载，监听
 * Created by Rny on 2017/4/5.
 */

public interface OnNextPageListener<K> {

    /**
     * 分页加载
     * @param key 分页标识关键字
     */
    void loadPage(K key);
}
