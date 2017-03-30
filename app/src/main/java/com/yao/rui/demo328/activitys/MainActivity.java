package com.yao.rui.demo328.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yao.rui.demo328.R;
import com.yao.rui.demo328.activitys.BaseActivity;
import com.yao.rui.demo328.utils.FinishLogic;
import com.yao.rui.demo328.utils.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
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

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
        FL.onKeyBack();
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
