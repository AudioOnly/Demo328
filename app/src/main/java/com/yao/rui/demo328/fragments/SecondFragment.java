package com.yao.rui.demo328.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yao.rui.demo328.R;
import com.yao.rui.demo328.pagehelper.IntergerPageHelper;
import com.yao.rui.demo328.views.ContentStatusView;
import com.yao.rui.pagerv.helpers.OnNextPageListener;
import com.yao.rui.pagerv.refresh.PageRefreshView;
import com.yao.rui.pagerv.rv.BaseRecyclerViewAdapter;
import com.yao.rui.pagerv.rv.BaseViewHolder;
import com.yao.rui.demo328.views.FooterStatusView;
import com.yao.rui.pagerv.rv.PRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 加载RecycleView
 * Created by Rny on 2017/3/31.
 */

public class SecondFragment extends BaseFragment {
    private boolean isPrepared = false;

    @BindView(R.id.srl)
    PageRefreshView srl;
    @BindView(R.id.rv)
    PRecyclerView rv;
    @BindView(R.id.csv)
    ContentStatusView csv;

    FooterStatusView footerStatusView;

    private IntergerPageHelper<String ,TestViewHolder> mHelper=new IntergerPageHelper<>();

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_second;
    }

    @Override
    protected void init(View view, Bundle savedInsanceState) {
        isPrepared = true;
        lazyLoad();

    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || isLoad) {
            return;
        }
        //初始化PRecyclerView
        rv.init(new LinearLayoutManager(getContext()),true,adapter);
        //初始化分页
        footerStatusView=new FooterStatusView(getContext());
        mHelper.init(adapter, csv, srl, footerStatusView, new OnNextPageListener<Integer>() {
            @Override
            public void loadPage(Integer key) {
                SecondFragment.this.loadPage(key);
            }
        });
        //开始加载数据
        mHelper.start(1, "数据加载中...", "正在获取下一页", "暂无数据信息", "没有更多数据了");

        isLoad = true;
    }


    private void loadPage(int page){
        rv.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > 3) {
                    mHelper.loadEmpty(page);
//                    mHelper.loadFailed(page,"网络错误！","网络错误，点击重新加载下一页");

                }else{
                    List<String> datas = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        datas.add("测试：：：：" + i);
                    }
                    mHelper.loadSuccess(page,datas);
                }
            }
        },1500);
    }


    private BaseRecyclerViewAdapter<String, TestViewHolder> adapter = new BaseRecyclerViewAdapter<String, TestViewHolder>(R.layout.item_recycler_layout) {
        @Override
        protected void bindView(TestViewHolder holder, String data, int position) {
            holder.tv.setText(data);
        }

        @Override
        protected BaseViewHolder getLayoutHoler(View root, int viewType) {
            return new TestViewHolder(root);
        }

        @Override
        protected void OnItemClickListener(View view, String data, int position) {
            toast("" + data + position);
        }

        @Override
        public int getItemViewType(int position) {
            int viewType=super.getItemViewType(position);
            //根据具体情况处理 显示样式，根据layoutIDs取viewtype的值。
            return viewType;
        }
    };


    //定义BaseViewHolder
    class TestViewHolder extends BaseViewHolder {

        @BindView(R.id.rv_item_tv)
        TextView tv;

        public TestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
