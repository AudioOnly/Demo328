package com.yao.rui.demo328.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 基础ViewHolder，为RecyclerViewAdapter使用
 * Created by Rny on 2017/3/31.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    //缓存点View
    public View clickView;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }
    public <V> V findViewById(int resId){
        return (V)itemView.findViewById(resId);
    }
}
