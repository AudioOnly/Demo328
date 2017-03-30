package com.yao.rui.demo328.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yao.rui.demo328.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Rny on 2017/3/29.
 */

public class FirstFragment extends BaseFragment {

    private boolean isPrepared = false;

    @BindView(R.id.fg_tv)
    TextView tv;

    public static FirstFragment inStance(String title) {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", "标题设置");
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
        tv.setText(getArguments().getString("title", "没有找到数据"));
        tv.setOnClickListener(v -> {
            toast("测试BaseFragment");
        });
        //数据操作完成
        isLoad = true;//防止数据重复加载
    }
}
