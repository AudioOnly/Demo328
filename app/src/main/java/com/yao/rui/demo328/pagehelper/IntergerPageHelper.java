package com.yao.rui.demo328.pagehelper;

import com.yao.rui.pagerv.helpers.PageHelper;

import java.util.List;

/**
 * 测试PageHelper
 * Created by Rny on 2017/4/5.
 */

public class IntergerPageHelper<T,H> extends PageHelper<Integer,T,H> {
    @Override
    protected boolean isFirstPage(Integer key, Integer mDefaultKey) {
        return key.equals(mDefaultKey);
    }

    @Override
    protected Integer getNextPageKey(Integer key, List<T> data) {
        return key+1;
    }
}
