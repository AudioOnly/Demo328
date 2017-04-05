package com.yao.rui.demo328.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.yao.rui.demo328.R;
import com.yao.rui.pagerv.status.PageStatusView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 内容状态view
 * Created by Rny on 2017/4/1.
 */

public class ContentStatusView extends PageStatusView {

    @BindView(R.id.v_progress)
    View v_progress;
    @BindView(R.id.tv_failed)
    TextView tv_failed;
    @BindView(R.id.tv_empty)
    TextView tv_empty;
    @BindView(R.id.tv_progress)
    TextView tv_progress;

    public ContentStatusView(Context context) {
        super(context);
    }

    public ContentStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ContentStatusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected View getContentView() {
        return inflater(R.layout.content_view);
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    public void setDefaultMsg(String progMsg, String emptyMsg) {
        tv_progress.setText(progMsg);
        tv_empty.setText(emptyMsg);
    }

    @Override
    public void setFailedMsg(String msg) {
        tv_failed.setText(msg);
    }

    @Override
    protected void showStatus(boolean progress, boolean failed, boolean empty) {
        v_progress.setVisibility(progress ? VISIBLE : GONE);
        tv_failed.setVisibility(failed ? VISIBLE : GONE);
        tv_empty.setVisibility(empty ? VISIBLE : GONE);
    }

    @Override
    public boolean isEmptyShow() {
        return tv_empty.getVisibility() == VISIBLE;
    }

    @Override
    public void onFailedClickListener(OnClickListener onClickListener) {
        tv_failed.setOnClickListener(onClickListener);
    }
}
