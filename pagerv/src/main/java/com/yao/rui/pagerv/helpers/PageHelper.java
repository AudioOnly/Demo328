package com.yao.rui.pagerv.helpers;

import android.view.View;

import com.yao.rui.pagerv.R;
import com.yao.rui.pagerv.refresh.OnPageRefreshListener;
import com.yao.rui.pagerv.refresh.PageRefreshView;
import com.yao.rui.pagerv.rv.BaseRecyclerViewAdapter;
import com.yao.rui.pagerv.rv.BaseViewHolder;
import com.yao.rui.pagerv.rv.OnFooterShowListener;
import com.yao.rui.pagerv.status.PageStatusView;

import java.util.List;

/**
 * 分页帮助类
 * Created by Rny on 2017/4/1.
 */

public abstract class PageHelper<K,T,H> {

    private BaseRecyclerViewAdapter<T,H> adapter;//适配器
    private PageStatusView contentStatusView;//内容控件
    private PageRefreshView pageRefreshView;//下来刷新接口
    private PageStatusView footerStatusView;//底部控件
    private OnNextPageListener<K> nextPageListener;//分页加载监听

    //FooterView创建的Holder
    private BaseViewHolder mFooterHolder;
    //默认页的Key，当前页Key
    private K mDefaultKey,mCurrentKey;
    //是否加载中
    private boolean isLoading=false;

    /**
     * 初始化
     *
     * @param adapter 适配器
     * @param contentStatusView 内容显示状态View
     * @param pageRefreshView 下拉刷新
     * @param footerStatusView 上拉加载 Footer
     * @param nextPageListener 加载下一页监听
     */
    public void init(BaseRecyclerViewAdapter<T, H> adapter, PageStatusView contentStatusView, PageRefreshView pageRefreshView, PageStatusView footerStatusView, OnNextPageListener<K> nextPageListener) {
        this.adapter = adapter;
        this.contentStatusView = contentStatusView;
        this.pageRefreshView = pageRefreshView;
        this.footerStatusView = footerStatusView;
        this.nextPageListener = nextPageListener;

        //初始化下拉控件监听
        if(pageRefreshView!=null){
            pageRefreshView.setListener(new OnPageRefreshListener() {
                @Override
                public void onRefresh() {
                   //下拉刷新
                    refresh(true);
                }
            });
        }
        //初始化ContentStatusView
        if (contentStatusView!=null){
            contentStatusView.onFailedClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //第一次加载失败，点击重新加载
                    refresh(false);
                }
            });
        }
        //创建FooterHolder
        if (footerStatusView!=null){
            mFooterHolder=new BaseViewHolder(footerStatusView);
            footerStatusView.onFailedClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击加载下一页
                    loadNextPage();
                }
            });
        }
        //初始化下一页加载监听 (上拉加载更多)
        adapter.setOnFooterShowListener(new OnFooterShowListener() {
            @Override
            public void onFooterShow() {
                //加载下一页
                loadNextPage();
            }
        });
    }

    /**
     * 开始加载
     *
     * @param defaultKey 默认页的key
     * @param contentProgress 内容显示的进度信息
     * @param footerProgress 上拉加载的进度信息
     * @param contentEmpty 内容显示空间的空信息
     * @param footerEmpty 上拉加载 没有更多数据的空信息
     */
    public void start(K defaultKey,String contentProgress,String footerProgress,String contentEmpty,String footerEmpty){
        //缓存默认的Key
        this.mDefaultKey=defaultKey;
        //初始化状态View
        if (contentStatusView != null) {
            contentStatusView.setDefaultMsg(contentProgress,contentEmpty);
            contentStatusView.resetUI();
        }
        //初始化FooterView
        if(footerStatusView!=null){
            footerStatusView.setDefaultMsg(footerProgress,footerEmpty);
            footerStatusView.resetUI();
        }
        //第一次加载，类似于下拉刷新，只不过不显示下来刷新效果
        refresh(false);

    }
    //刷新
    public void refresh(boolean showRefreshView){
        if (showRefreshView){
            //需要显示效果，不需要显示效果保持当前下来刷新的显示效果
            if (pageRefreshView!=null)
                pageRefreshView.showRefreshView(true);
        }
        //恢复状态控制进度状态，但不该病其他属性：1.第一次加载失败，下拉刷新，此时会显示进度控件；2.加载数据成功过，下拉刷新，此时不显示进度控件
        if(contentStatusView!=null){
            contentStatusView.progress();
        }
        //加载第一页数据
        loadPage(mDefaultKey);
    }

    /**
     * 加载下一页数据
     */
    private void loadNextPage(){
        if(footerStatusView!=null){
            //判断Footer状态
            if (footerStatusView.isEmptyShow()) { //没有更多数据了
                return;
            }
            //设置footer的显示
            footerStatusView.progress();
        }
        //加载下一页
        loadPage(getNextPageKey(mCurrentKey,adapter.list()));
    }

    /**
     * 加载数据
     * @param key
     */
    private void loadPage(K key){
        //判断加载状态
        if (isLoading)
            return ;
        isLoading=true;
        nextPageListener.loadPage(key);
    }

    /**
     * 加载失败
     * @param key 当前页
     * @param contentFailed 内容失败信息
     * @param footerFailed footer失败信息
     */
    public void loadFailed(K key,String contentFailed,String footerFailed){
        if (isFirstPage(key,mDefaultKey)) {
            //第一页数据：第一次进入加载或下拉刷新加载
            //只通知状态控件失败，不改变其隐藏属性。
            //1、第一次加载数据或者第一次加载失败后下拉刷新控件本来就是显示的，直接通知失败即可
            //2、加载成功一页数据后，控件被隐藏掉，下拉刷新失败，仍然通知失败，此时控件是隐藏的，界面不会发生改变，依然显示现有数据
            if (contentStatusView != null) {
                contentStatusView.failed(contentFailed);
            }
        }else{//其他页数据：第二页以上de
            //直接通知footer失败即可
            if (footerStatusView != null) {
                footerStatusView.failed(footerFailed);
            }
        }
        if (pageRefreshView!=null)
            pageRefreshView.showRefreshView(false);
        isLoading=false;
    }

    /**
     * 空
     * @param key
     */
    public void loadEmpty(K key){
        if (isFirstPage(key, mDefaultKey)) {
            //第一页数据：第一次进入加载或者下拉刷新
            adapter.resetUI(false);
            if (contentStatusView != null) {
                contentStatusView.resetUI();
                contentStatusView.isEmpty();
            }
        } else {//其他页
            if (footerStatusView!=null){
                footerStatusView.isEmpty();
            }

        }
        if (pageRefreshView!=null)
            pageRefreshView.showRefreshView(false);
        isLoading=false;
    }

    /**
     * 成功
     * @param key
     * @param data
     */
    public void loadSuccess(K key,List<T> data){
        List<T> list=adapter.list();
        if (isFirstPage(key, mDefaultKey)) {
            if (contentStatusView != null) {
                contentStatusView.success();
            }
            if (footerStatusView != null) {
                footerStatusView.resetUI();
            }
            adapter.setvFooter(footerStatusView);//=========================error
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
        } else {
            int currentSize=list.size();
            list.addAll(data);
            //通知插入，Footer改变
            adapter.notifyItemRangeInserted(currentSize,data.size());
            adapter.notifyItemChanged(adapter.getItemCount()-1);
        }
        //缓存key
        mCurrentKey=key;
        if (pageRefreshView!=null){
            pageRefreshView.showRefreshView(false);
        }
        isLoading=false;
    }



    /**
     * 判断是否是第一页，
     * @param key 当前页
     * @param mDefaultKey 默认第一页
     * @return
     */
    protected abstract boolean isFirstPage(K key,K mDefaultKey);

    /**
     * 获取下一页 标识
     * @param key 当前页
     * @param data 当前数据列表
     * @return 下一页 Key 标识
     */
    protected abstract K getNextPageKey(K key, List<T> data);

}
