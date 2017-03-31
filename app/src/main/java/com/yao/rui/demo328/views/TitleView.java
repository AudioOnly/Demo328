package com.yao.rui.demo328.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yao.rui.demo328.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义标题栏
 * Created by Rny on 2017/3/30.
 */

public class TitleView extends FrameLayout {
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.title_tv)
    TextView tv_title;

    public TitleView(@NonNull Context context) {
        super(context);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        boolean isAlpha = getTag() != null && Boolean.parseBoolean(getTag().toString());
        if (isAlpha) { //如果是透明渐变效果,背景色不能和不渐变时引用一个资源对象,alpha时好像会改变这个色值导致其它标题栏一起变色
            setBackgroundColor(Color.parseColor("#fbfbfb"));
        } else {//父容器设置透明状态栏填充的背景色
            setBackgroundResource(R.color.title_bg);
        }
        //添加标题布局，并去除标题背景色,注意参数配置
        View view = LayoutInflater.from(getContext()).inflate(R.layout.title_view, this,false);
        view.setBackgroundResource(android.R.color.transparent);
        addView(view);
        ButterKnife.bind(this);
    }

    //左侧按钮
    public TitleView setLeftNone() {
        iv_left.setVisibility(INVISIBLE);
        return this;
    }
    public TitleView setLeftBack(int resId,Activity act){
        iv_left.setImageResource(resId);
        iv_left.setOnClickListener(v -> {
            act.onBackPressed();
        });
        return this;
    }
    public TitleView setLeft(int resId, OnClickListener listener) {
        iv_left.setImageResource(resId);
        iv_left.setOnClickListener(listener);
        return this;
    }

    //标题
    public TitleView setTitleCol(int colorId){
        tv_title.setTextColor(colorId);
        return this;
    }
    public TitleView setTitleCol(String colorStr){
        tv_title.setTextColor(Color.parseColor(colorStr));
        return this;
    }
    public TitleView setTitle(int resId) {
        tv_title.setText(resId);
        return this;
    }

    public TitleView setTitle(String res) {
        tv_title.setText(res);
        return this;
    }

    //右侧按钮iv、tv
    public TitleView setRightNone() {
        iv_right.setVisibility(INVISIBLE);
        tv_right.setVisibility(INVISIBLE);
        return this;
    }

    public TitleView setRightIv(int resId, OnClickListener listener) {
        tv_right.setVisibility(GONE);
        iv_right.setImageResource(resId);
        iv_right.setOnClickListener(listener);
        return this;
    }

    public TitleView setRightTv(int resId, OnClickListener listener) {
        iv_right.setVisibility(GONE);
        tv_title.setText(resId);
        tv_right.setOnClickListener(listener);
        return this;
    }

    public TitleView setRightTv(String res, OnClickListener listener) {
        iv_right.setVisibility(GONE);
        tv_title.setText(res);
        tv_right.setOnClickListener(listener);
        return this;
    }


}
