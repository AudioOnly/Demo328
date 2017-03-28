package com.yao.rui.demo328;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_tv).setOnClickListener(v -> {
            Toast.makeText(this,"Lambda使用成功",Toast.LENGTH_SHORT).show();
        });
    }
}
