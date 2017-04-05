package com.yao.rui.pagerv.refresh;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.yao.rui.pagerv.R;

/**
 * Created by Rny on 2017/4/5.
 */

public class PageSwipeRefreshLayout extends SwipeRefreshLayout implements PageRefreshView{
    public PageSwipeRefreshLayout(Context context) {
        super(context);
    }

    public PageSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setListener(final OnPageRefreshListener listener) {
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                listener.onRefresh();
            }
        });
    }

    @Override
    public void showRefreshView(boolean show) {
        setRefreshing(show);
    }


    //这是 下拉刷新样式
//    setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.progress));


}
