package com.hy.mystudydemo.listview;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hy.mystudydemo.R;

import java.util.ArrayList;
import java.util.List;

public class LoadMoreActivity extends AppCompatActivity implements LoadListView.ILoadMoreListener{


    private LoadListView mListView;
    private MyBaseAdapter mAdapter;
    private List<String> dataList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=(LoadListView) findViewById(R.id.listView);
        dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("测试" + (i + 1));
        }
        setAdapter();
        mListView.setLoadMoreListener(this);
    }

    @Override
    public void onLoadMore() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {//这一段代码在主线程中执行,这里相当于把runable发送到消息队列中处理，handleMeaasge,使用场景：子线程太多，使用Messagte+handler太麻烦,直接在子线程中,handler.postDelayed(),即可更新UI
                for (int i = 0; i < 20; i++) {
                    dataList.add("测试" + (i + 1));
                }
                setAdapter();
                mListView.loadCompelete();
            }
        },2000);
    }

    private void setAdapter() {
        if(mAdapter!=null){
            mAdapter.notifyDataSetChanged();
        }else {
            mAdapter=new MyBaseAdapter(LoadMoreActivity.this,dataList);
            mListView.setAdapter(mAdapter);
        }
    }
}
