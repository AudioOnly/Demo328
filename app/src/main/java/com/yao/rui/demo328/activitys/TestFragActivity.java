package com.yao.rui.demo328.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yao.rui.demo328.R;
import com.yao.rui.demo328.fragments.FirstFragment;

/**
 * 测试Activity
 * Created by Rny on 2017/3/29.
 */

public class TestFragActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        FirstFragment fragment=FirstFragment.inStance("按钮设置");
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction ft=manager.beginTransaction();
        ft.add(R.id.frg_content,fragment,"first");
        ft.commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.frg_content,FirstFragment.inStance("按钮设置")).commit();
    }
}
