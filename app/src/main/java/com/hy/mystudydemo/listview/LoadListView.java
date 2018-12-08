package com.hy.mystudydemo.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.hy.mystudydemo.R;

/**
 * 分页加载更多
 * Created by and huangyu 2018-12-06.
 */

public class LoadListView extends ListView implements AbsListView.OnScrollListener{
    private View footView;
    private int totalItemCount;//总条数
    private int lastVisibleItem;//最后可见的item
    private boolean isLoading=true;//正在加载

    private ILoadMoreListener mLoadMoreListener;
    public LoadListView(Context context) {
        super(context);
        initView(context);
    }


    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //为ListView添加footer布局,和滑动监听
        LayoutInflater inflater=LayoutInflater.from(context);
        footView=inflater.inflate(R.layout.footer,null);
        footView.findViewById(R.id.load_layout).setVisibility(View.GONE);
        this.addFooterView(footView);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(lastVisibleItem==totalItemCount&&scrollState==SCROLL_STATE_IDLE){//最后一个Item,并且停止滚动
            if(isLoading){//正在加载
                isLoading=false;
                footView.findViewById(R.id.load_layout).setVisibility(VISIBLE);
                //加载更多
                mLoadMoreListener.onLoadMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem=firstVisibleItem+visibleItemCount;
        this.totalItemCount=totalItemCount;
    }

    public void loadCompelete(){
        isLoading=true;
        footView.findViewById(R.id.load_layout).setVisibility(GONE);
    }

    public interface ILoadMoreListener{
        void  onLoadMore();
    }

    public void setLoadMoreListener(ILoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }
}
