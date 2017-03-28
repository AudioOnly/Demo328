package com.yao.rui.demo328;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化ButterKnife
        ButterKnife.bind(this);
    }
    //使用ButterKnife的@onClick，方法不可以为private或static
    @OnClick(R.id.main_tv)
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.main_tv:
                Toast.makeText(this,"使用ButterKnife成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
