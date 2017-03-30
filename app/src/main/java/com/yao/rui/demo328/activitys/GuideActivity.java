package com.yao.rui.demo328.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.yao.rui.demo328.R;
import com.yao.rui.demo328.adapters.ViewPageAdapter;
import com.yao.rui.demo328.utils.FinishLogic;
import com.yao.rui.demo328.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 指南页
 * Created by Rny on 2017/3/30.
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.stl)
    SmartTabLayout stl;

    List<Object> datas;
    L l = new L(GuideActivity.class);

    private final FinishLogic FL=new FinishLogic() {
        @Override
        protected void touchAgain() {
            toast("再次点击退出应用");
        }

        @Override
        protected void onFinsih() {
            exit();
        }
    };

    @Override
    protected int getLayoutID() {
        return R.layout.activity_guide;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        datas = new ArrayList<>();
        datas.add(new GuideHolder(createItemView(R.drawable.lead_01_bg)));
        datas.add(new GuideHolder(createItemView(R.drawable.lead_03_bg)));
        datas.add(new GuideHolder(createItemView(R.drawable.lead_03_bg)));
        vp.setAdapter(new ViewPageAdapter(datas, true));
        stl.setViewPager(vp);
        stl.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                l.e("onPageSelected::" + position);
                TextView tv = (TextView) ((ViewPageAdapter) vp.getAdapter()).getHolder(position).findViewId(R.id.next_tv);
                if (position == vp.getAdapter().getCount() - 1) {
                    tv.setVisibility(View.VISIBLE);
                    tv.setOnClickListener(v -> {
                        startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        finish();
                    });
                } else
                    tv.setVisibility(View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        FL.onKeyBack();
    }

    private View createItemView(int iv_res_id) {
        View view = LayoutInflater.from(this).inflate(R.layout.guide_item_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.guide_iv);
        iv.setImageResource(iv_res_id);
        return view;
    }

    private class GuideHolder extends ViewPageAdapter.BaseHolder {

        public GuideHolder(View itemView) {
            super(itemView);
        }
    }
}
