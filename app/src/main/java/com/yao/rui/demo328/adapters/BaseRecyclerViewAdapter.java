package com.yao.rui.demo328.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yao.rui.demo328.R;
import com.yao.rui.demo328.interfaces.OnFooterShowListener;
import com.yao.rui.demo328.viewholders.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * RecyclerView基础Adapter
 * Created by Rny on 2017/3/31.
 *
 * @param <T>
 * @param <H>
 */
public abstract class BaseRecyclerViewAdapter<T, H> extends RecyclerView.Adapter<BaseViewHolder> {
    private final String TAG = BaseRecyclerViewAdapter.class.getSimpleName();
    //数据
    private List<T> objects = new ArrayList<T>();
    //xml布局
    private int[] layoutIDs;
    //需要点击的控件id
    private int[] clickViewIDs;

    /**
     * 单行样式构造器，整行点击
     *
     * @param layoutID
     */
    public BaseRecyclerViewAdapter(int layoutID) {
        this(layoutID, 0);
    }

    public BaseRecyclerViewAdapter(int layoutID, int clickViewID) {
        this(new int[]{layoutID}, new int[]{clickViewID});
    }

    /**
     * 多样式构造函数
     *
     * @param layoutIDs    布局id
     * @param clickViewIDs 点击ViewID，0表示整行，-1表示不设置
     */
    public BaseRecyclerViewAdapter(int[] layoutIDs, int[] clickViewIDs) {
        this.layoutIDs = layoutIDs;
        this.clickViewIDs = clickViewIDs;
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooter(position))
            return VIEW_FOOTER_TYPE;
        if (isHeader(position))
            return VIEW_HEADER_TYPE;
        //默认返回0
        return 0;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_FOOTER_TYPE)
            return new BaseViewHolder(vFooter);
        if (viewType == VIEW_HEADER_TYPE)
            return new BaseViewHolder(vHeader);
        if (viewType < 0 || viewType > layoutIDs.length)
            viewType = 0;
        View root = LayoutInflater.from(parent.getContext()).inflate(layoutIDs[viewType], parent, false);
        //由相应的View生成 ViewHolder
        BaseViewHolder bh = getLayoutHoler(root, viewType);
        if (clickViewIDs[viewType] == 0) {
            bh.clickView = root;
        } else {
            bh.clickView = root.findViewById(clickViewIDs[viewType]);
        }
        if (bh.clickView != null)
            bh.clickView.setOnClickListener(onClickListener);
        return bh;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (isFooter(position)) {
            onFooterShow();
            return;
        }
        if (isHeader(position))
            return;
        //获取数据的具体位置
        int dataPosition = position - (vHeader == null ? 0 : 1);
        if (holder.clickView != null)
            holder.clickView.setTag(dataPosition);
        //绑定数据
        bindView((H) holder, objects.get(dataPosition), position);
    }

    @Override
    public int getItemCount() {
        return objects.size() + (vHeader == null ? 0 : 1) + (vFooter == null ? 0 : 1);
    }

    //移除数据
    public Boolean removeData(T data) {
        return objects.remove(data);
    }

    //清除数据
    public void clearData() {
        objects.clear();
    }

    //添加数据
    public Boolean addData(T data) {
        return objects.add(data);
    }

    //添加数据
    public Boolean addAll(List<T> datas) {
        return objects.addAll(datas);
    }

    //获取数据
    public T getData(int position) {
        return objects.get(position);
    }

    //添加头、尾=====================================
    //标记头、尾
    private final int VIEW_FOOTER_TYPE = -1000;
    private final int VIEW_HEADER_TYPE = -1001;
    //头、尾 ViewHolder
    private View vFooter;
    private View vHeader;
    //尾布局监听
    private OnFooterShowListener onFooterShowListener;

    public void setvFooter(View vFooter) {
        this.vFooter = vFooter;
    }

    public void setvHeader(View vHeader) {
        this.vHeader = vHeader;
    }

    public void setOnFooterShowListener(OnFooterShowListener onFooterShowListener) {
        this.onFooterShowListener = onFooterShowListener;
    }

    //是否是尾
    private Boolean isFooter(int position) {
        return vFooter != null && position == getItemCount() - 1;
    }

    //是否是 头
    private Boolean isHeader(int position) {
        return vHeader != null && position == 0;
    }

    //尾 显示时候
    protected void onFooterShow() {
        if (onFooterShowListener != null) {
            onFooterShowListener.onFooterShow();
        } else {
            Log.e(TAG, "OnFooterShowListener未配置，为null");
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = Integer.valueOf(v.getTag().toString());
            OnItemClickListener(v, objects.get(position), position);
        }
    };

    /**
     * 还原到初始化状态
     */
    public void resetUI() {
        clearData();
        setvFooter(null);
        notifyDataSetChanged();
    }

    //接口============================

    /**
     * 绑定数据
     *
     * @param holder
     * @param data
     * @param position
     */
    protected abstract void bindView(H holder, T data, int position);

    /**
     * 获取 ViewHolder
     *
     * @param root
     * @param viewType
     * @return
     */
    protected abstract BaseViewHolder getLayoutHoler(View root, int viewType);

    /**
     * 添加控件监听事件
     *
     * @param view
     * @param data
     * @param position
     */
    protected abstract void OnItemClickListener(View view, T data, int position);

}
