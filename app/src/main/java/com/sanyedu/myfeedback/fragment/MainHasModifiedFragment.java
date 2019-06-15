package com.sanyedu.myfeedback.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.activity.ModifyDetailActivity;
import com.sanyedu.myfeedback.adapter.NeedModifyAdapter;
import com.sanyedu.myfeedback.base.BaseFragment;
import com.sanyedu.myfeedback.listener.EndlessRecyclerOnScrollListener;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvpimpl.needmodified.NeedModifiedContacts;
import com.sanyedu.myfeedback.mvpimpl.needmodified.NeedModifiedPresenter;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.StartUtils;
import com.sanyedu.myfeedback.utils.ToastUtil;
import com.sanyedu.myfeedback.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 已整改 fragment
 */
public class MainHasModifiedFragment extends BaseFragment<NeedModifiedPresenter> implements NeedModifiedContacts.INeedModifiedUI {

    @BindView(R.id.need_rv)
    RecyclerView recyclerView;
    @BindView(R.id.pulldown_srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private NeedModifyAdapter recordAdapter;
    private List<Records> currList = new ArrayList<>();
    private final int PAGE_COUNT = 4;
    private LoadMoreWrapper loadMoreWrapper;
    private int currentPage = 1;

    //最大页面数,初始化为1
    private int  totalSize = 1;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_need_modify;
    }

    @Override
    protected void init(View view) {
        ButterKnife.bind(this,view);
        initRefreshLayout();
        initRecyclerView();
        setListener();
        getFirstPageData();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recordAdapter = new NeedModifyAdapter(getActivity());
        loadMoreWrapper = new LoadMoreWrapper(recordAdapter);
        recyclerView.setAdapter(loadMoreWrapper);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
    }
    private void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
    }

    private void setListener() {
        // 设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currList.clear();
                currentPage = 1;
                getDataFromServer();
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

        recordAdapter.setItemClickListener(new NeedModifyAdapter.OnItemClickListener(){
            @Override
            public void onClick(View view, int position, String id) {
                SanyLogs.i("noticeclick~~~~position:" +position + ",id:" + id);
                StartUtils.startActivity(getContext(), ModifyDetailActivity.class,id);
            }
        });
    }

    private void addMoreRecords() {
        currentPage ++;
        getDataFromServer();
    }

    private void getDataFromServer(){
        getPresenter().getRecords(currentPage+"",PAGE_COUNT + "","2");
    }

    @Override
    public NeedModifiedPresenter onBindPresenter() {
        return new NeedModifiedPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
        recordAdapter.setRecordsList(currList);
        loadMoreWrapper.notifyDataSetChanged();

        SanyLogs.i("totalSize:" + totalSize + ",maxCount:" + totalSize);
    }

    @Override
    public void showNoMoreList() {
        SanyLogs.i("shoNoMoreList~~~~");
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
    }

    @Override
    public void showError(String serverErrorMsg) {
        SanyLogs.e("showNoNotice~~~~~");
        if(!TextUtils.isEmpty(serverErrorMsg)) {
            ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
        }
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
    }

    private void getFirstPageData() {
        currentPage = 1;
        currList.clear();
        getPresenter().getRecords(currentPage+"",PAGE_COUNT + "","1");
    }

}
