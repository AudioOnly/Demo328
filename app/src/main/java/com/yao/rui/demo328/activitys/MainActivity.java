package com.yao.rui.demo328.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yao.rui.demo328.R;
import com.yao.rui.demo328.activitys.BaseActivity;
import com.yao.rui.demo328.utils.Toast;

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
//                Toast.show("使用ButterKnife成功");
                startActivity(new Intent(this,TestFragActivity.class));
                break;
        }
    }
}
