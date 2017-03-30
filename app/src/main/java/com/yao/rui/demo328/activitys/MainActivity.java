package com.yao.rui.demo328.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.yao.rui.demo328.R;
import com.yao.rui.demo328.fragments.FirstFragment;
import com.yao.rui.demo328.utils.FinishLogic;
import com.yao.rui.demo328.views.NoScrollViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rg_tabs)
    RadioGroup rg_tabs;
    @BindView(R.id.vp)
    NoScrollViewPager vp;
    private FragmentPagerItemAdapter fragmentPagerItemAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Bundle b1 = new Bundle();
        b1.putString("title", "首页");
        Bundle b2 = new Bundle();
        b2.putString("title", "贰页");
        Bundle b3 = new Bundle();
        b3.putString("title", "叁页");
        Bundle b4 = new Bundle();
        b4.putString("title", "肆页");
        Bundle b5 = new Bundle();
        b5.putString("title", "伍页");
        fragmentPagerItemAdapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("", FirstFragment.class, b1)
                        .add("", FirstFragment.class, b2)
                        .add("", FirstFragment.class, b3)
                        .add("", FirstFragment.class, b4)
                        .add("", FirstFragment.class, b5)
                        .create());
        vp.setAdapter(fragmentPagerItemAdapter);
        vp.setOffscreenPageLimit(5);//onInterceptTouchEvent
        rg_tabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                vp.setCurrentItem(Integer.valueOf(findViewById(checkedId).getTag().toString()), false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        FL.onKeyBack();
    }


    //退出应用逻辑
    private FinishLogic FL = new FinishLogic() {
        @Override
        protected void touchAgain() {
            toast("再次点击退出应用程序");
        }

        @Override
        protected void onFinsih() {
            exit();
        }
    };
}
