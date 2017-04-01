package com.yao.rui.demo328.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yao.rui.demo328.R;
import com.yao.rui.demo328.adapters.BaseRecyclerViewAdapter;
import com.yao.rui.demo328.viewholders.BaseViewHolder;

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
    SwipeRefreshLayout srl;
    @BindView(R.id.rv)
    RecyclerView rv;
    //测试数据
    List<String> datas;

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
        srl.setRefreshing(false);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);

        datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            datas.add("测试：：：：" + i);
        }
        adapter.addAll(datas);
        rv.setAdapter(adapter);
        rv.setItemAnimator(null);
        isLoad = true;
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
