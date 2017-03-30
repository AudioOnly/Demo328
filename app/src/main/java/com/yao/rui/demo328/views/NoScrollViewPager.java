package com.yao.rui.demo328.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 重写ViewPager，覆盖 onTouchEvent 和 onInterceptTouchEvent 方法，使其返回false，这样就等于禁止了ViewPager上的滑动事件。
 * Created by Rny on 2017/3/30.
 */

public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
