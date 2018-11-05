package com.example.defaultaccount.recyclerviewdemo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MyAdapter adapter;
    SwipeRefreshLayout refreshLayout;
    private int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new MyAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        refreshLayout=(SwipeRefreshLayout) findViewById(R.id.refresh);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getData();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                refreshLayout.setRefreshing(false);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalItemCount;
            private int firstVisibleItem;
            private int visibleItemCount;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                visibleItemCount = recyclerView.getChildCount();
                if ( ((totalItemCount - visibleItemCount) <= firstVisibleItem)) {
                    onLoadMore();

                }
            }
            private void onLoadMore() {
                    getData();
            }
        });
    }
    private void getData(){
        for (int i = 1; i <= 20; i++) {
            num++;
            String s="第" + num + "项";
            adapter.addItem(s);
        }
    }
    private void refreshData(){
        List<String> textList=new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            num++;
            String s="第" + num + "项";
            textList.add(s);
        }
        adapter.refreshItems(textList);
    }
}
