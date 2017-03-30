package com.yao.rui.demo328.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

/**
 * ViewPageAdapter
 * Created by Rny on 2017/3/30.
 */

public class ViewPageAdapter extends PagerAdapter {
    //数据
    private List<Object> objects;
    //是否是Holder
    private boolean isHolder;

    public ViewPageAdapter(List<Object> objects) {
        this(objects, false);
    }

    public ViewPageAdapter(List<Object> views, boolean isHolder) {
        this.objects = views;
        this.isHolder = isHolder;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    /**
     * 用于判断是否由对象生成界面
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 实例化item
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getItemView(position);
        container.addView(view);
        return view;
    }

    /**
     * 删除Item
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(getItemView(position));
    }

    /**
     * 获取Item
     *
     * @param position
     * @return
     */
    private View getItemView(int position) {
        Object object = objects.get(position);
        return isHolder ? ((BaseHolder) object).itemView : (View) object;

    }

    /**
     * 获取holder对象，
     * 方便在UI层 添加Item子控件监听器之类的动作，findViewId
     *
     * @param position
     * @param <T>
     * @return
     */
    public <T extends BaseHolder> T getHolder(int position) {
        return (T) objects.get(position);
    }

    public static class BaseHolder {
        View itemView;

        public BaseHolder(View itemView) {
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public <V> V findViewId(int vid) {
            return (V) itemView.findViewById(vid);
        }
    }


}
