package com.sanyedu.myfeedback.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.adapter.PullAdapter;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.listener.EndlessRecyclerOnScrollListener;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvpimpl.needmodified.NeedModifiedContacts;
import com.sanyedu.myfeedback.mvpimpl.needmodified.NeedModifiedPresenter;
import com.sanyedu.myfeedback.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;


public class RecyclerDemoActivity extends SanyBaseActivity<NeedModifiedPresenter> implements NeedModifiedContacts.INeedModifiedUI{
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<Records> currList = new ArrayList<>();

    private final int PAGE_COUNT = 4;
//    private GridLayoutManager mLayoutManager;
    private PullAdapter pullAdapter;

    private LoadMoreWrapper loadMoreWrapper;
    private int currentPage = 1;

    private int  totalSize = 1; //最大的条数,初始化为PAGE_COUNT
    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initRefreshLayout();
        initRecyclerView();
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
    }

    private void initRecyclerView(){
        SanyLogs.i("initRecyclerView~~~");
        pullAdapter = new PullAdapter(this);
        loadMoreWrapper = new LoadMoreWrapper(pullAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(loadMoreWrapper);

        // 设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currList.clear();
                currentPage = 1;
                getPresenter().getRecords(currentPage+"",PAGE_COUNT + "","2");
            }
        });

        // 设置加载更多监听
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                SanyLogs.i("onLoadMore~~~~");
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                if ( currentPage < totalSize) {
                   addMoreRecords();
                } else {
                    // 显示加载到底的提示
                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
                }
            }
        });

        initRecycleData();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_recycle_demo;
    }

    @Override
    public NeedModifiedPresenter onBindPresenter() {
        return new NeedModifiedPresenter(this);
    }

    @Override
    public void setRecords(List<Records> recordsList,int maxCount) {
        SanyLogs.i("recordsList:" + recordsList.size());

        //当列表为空时，这时有可能是下拉刷新,因此要设置下拉刷新的标志
        if (currList.size() == 0){
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }else{ //当列表不为空时，说明这时只是加载更多的数据，只要把loadMoreWrapper的标志设置一下就可以了。
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        }
        //TODO:最大条数要更
        totalSize = maxCount;
        currList.addAll(recordsList);
        pullAdapter.setDatas(currList);
        loadMoreWrapper.notifyDataSetChanged();

        SanyLogs.i("totalSize:" + totalSize + ",maxCount:" + maxCount);
    }

    @Override
    public void showNoMoreList() {
        SanyLogs.i("shoNoMoreList~~~~");
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
    }

    @Override
    public void showError(String msg) {
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
    }

    private void addMoreRecords(){
        currentPage ++;
        getPresenter().getRecords(currentPage+"",PAGE_COUNT + "","2");
    }

    private void initRecycleData() {
        currentPage = 1;
        currList.clear();
        getPresenter().getRecords(currentPage+"",PAGE_COUNT + "","2");
    }
}
