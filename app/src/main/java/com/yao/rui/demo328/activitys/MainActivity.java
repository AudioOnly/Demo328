package com.yao.rui.demo328.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yao.rui.demo328.R;
import com.yao.rui.demo328.activitys.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    //使用ButterKnife的@onClick，方法不可以为private或static
    @OnClick(R.id.main_tv)
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.main_tv:
                Toast.makeText(this, "使用ButterKnife成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
