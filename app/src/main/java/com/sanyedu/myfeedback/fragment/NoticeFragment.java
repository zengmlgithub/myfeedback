package com.sanyedu.myfeedback.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.activity.NoticeDetailActivity;
import com.sanyedu.myfeedback.adapter.NoticeAdapter;
import com.sanyedu.myfeedback.base.BaseFragment;
import com.sanyedu.myfeedback.listener.EndlessRecyclerOnScrollListener;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.NoticeBean;
import com.sanyedu.myfeedback.mvpimpl.notice.NoticeContacts;
import com.sanyedu.myfeedback.mvpimpl.notice.NoticePresenter;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.StartUtils;
import com.sanyedu.myfeedback.utils.ToastUtil;
import com.sanyedu.myfeedback.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * modified by zengml
 *
 * 通知页面
 *
 *
 */
public class NoticeFragment extends BaseFragment<NoticePresenter> implements NoticeContacts.INoticeUI {
    @BindView(R.id.main_msg_rv)
     RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
     NoticeAdapter noticeAdapter;

    private List<NoticeBean> currList = new ArrayList<>();

    private final int PAGE_COUNT = 4;

    private LoadMoreWrapper loadMoreWrapper;
    private int currentPage = 1;

    //最大页面数,初始化为1
    private int  totalSize = 1;

    @Override
    protected int getLayout() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void init(View view) {
        ButterKnife.bind(this,view);

        initRefreshLayout();
        initRecyclerView();
        setListener();
        getFirstPageData();
    }

    private void getFirstPageData() {
        currentPage = 1;
        currList.clear();
        showLoading();
        getPresenter().getNotices(currentPage+"",PAGE_COUNT + "");
    }

    private void setListener() {
        // 设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currList.clear();
                currentPage = 1;
                getPresenter().getNotices(currentPage+"",PAGE_COUNT + "");
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

        noticeAdapter.setOnItemClickListener(new NoticeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String id) {
                SanyLogs.i("noticeclick~~~~position:" +position + ",id:" + id);
                StartUtils.startActivity(getContext(), NoticeDetailActivity.class,id);
            }
        });
    }

    private void addMoreRecords() {
        currentPage ++;
        getPresenter().getNotices(currentPage+"",PAGE_COUNT + "");
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        noticeAdapter = new NoticeAdapter(getActivity());
        loadMoreWrapper = new LoadMoreWrapper(noticeAdapter);
        recyclerView.setAdapter(loadMoreWrapper);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
    }



    @Override
    public NoticePresenter onBindPresenter() {
        return new NoticePresenter(this);
    }

    @Override
    public void setNotices(ArrayList<NoticeBean> notices,int maxPageCount) {
        SanyLogs.i("recordsList:" + notices.size());
        hideLoading();
        //当列表为空时，这时有可能是下拉刷新,因此要设置下拉刷新的标志
        if (currList.size() == 0){
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }else{ //当列表不为空时，说明这时只是加载更多的数据，只要把loadMoreWrapper的标志设置一下就可以了。
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        }
        //TODO:最大条数要更
        totalSize = maxPageCount;
        currList.addAll(notices);
        noticeAdapter.setNoticeList(currList);
        loadMoreWrapper.notifyDataSetChanged();

        SanyLogs.i("totalSize:" + totalSize + ",maxCount:" + maxPageCount);
    }

    @Override
    public void setNoNotices() {
        SanyLogs.i("shoNoMoreList~~~~");
        hideLoading();
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);

        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError(String msg) {
        SanyLogs.e("showNoNotice~~~~~");
        hideLoading();
        if(!TextUtils.isEmpty(msg)) {
            ToastUtil.showLongToast(msg);
        }
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
    }
}
