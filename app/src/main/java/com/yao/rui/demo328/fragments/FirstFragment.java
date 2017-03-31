package com.yao.rui.demo328.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yao.rui.demo328.R;
import com.yao.rui.demo328.views.TitleView;

import butterknife.BindView;

/**
 * Created by Rny on 2017/3/29.
 */

public class FirstFragment extends BaseFragment {

    private boolean isPrepared = false;

    @BindView(R.id.fg_tv)
    TextView tv;
    @BindView(R.id.v_fitsSystemWindows)
    TitleView title_tv;

    public static FirstFragment inStance(String title) {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_first;
    }

    @Override
    protected void init(View view, Bundle savedInsanceState) {
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible|| isLoad){
            return;
        }
        //做耗时的数据操作
        title_tv.setTitle(getArguments().getString("title", "没有找到数据"));
        //数据操作完成
        isLoad = true;//防止数据重复加载
    }
}
