package com.sanyedu.myfeedback.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.mvpimpl.MyFeedbackFragment.FeedbackMyFragmentPresenter;
import com.sanyedu.myfeedback.mvpimpl.MyFeedbackFragment.MyFeedbackFragmentContacts;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.StartUtils;
import com.sanyedu.myfeedback.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 *  反馈我的
 */
public class BaseFeedbackMyFragment extends BaseFragment<FeedbackMyFragmentPresenter> implements MyFeedbackFragmentContacts.IMyFeedbackFragmentUI{

    @BindView(R.id.feedback_rl)
    RecyclerView recyclerView;

    @BindView(R.id.pulldown_srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

//    private RecyclerView listView;
    private NeedModifyAdapter recordAdapter;
    private List<Records> currList = new ArrayList<>();
    private final int PAGE_COUNT = 4;
    private LoadMoreWrapper loadMoreWrapper;
    private int currentPage = 1;
    //最大页面数,初始化为1
    private int  totalSize = 1;


    public BaseFeedbackMyFragment() {

    }

    public static BaseFeedbackMyFragment newInstance(String param1, String param2) {
        BaseFeedbackMyFragment fragment = new BaseFeedbackMyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BaseFeedbackMyFragment newInstance(String param1) {
        return newInstance(param1,null);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_feedback;
    }

    @Override
    protected void init(View view) {
        ButterKnife.bind(this,view);
        initParams();
        initRefreshLayout();
        initRecycleView();
        setListener();
        getFirstPageData();
    }

    private void initParams() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }else{
            mParam1 = ConstantUtil.FK_STATE_SUBMITED;
            mParam2 = null;
        }
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

    private void initRecycleView() {
        recordAdapter = new NeedModifyAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recordAdapter);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
    }

    @Override
    public FeedbackMyFragmentPresenter onBindPresenter() {
        return new FeedbackMyFragmentPresenter(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
////        if (mListener != null) {
////            mListener.onFragmentInteraction(uri);
////        }
////    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setFeebacks(List<Records> recordsList) {
        recordAdapter.setRecordsList(recordsList);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getDataFromServer() {
        TeacherBean teacherBean = SpHelper.getObj(ConstantUtil.USERINFO);
        if(teacherBean != null){
            SanyLogs.i("get id:" + teacherBean.getId() + ",type:" + mParam1);
            getPresenter().getFeedbacks(currentPage + "",PAGE_COUNT + "",teacherBean.getId(),mParam1);
        }
    }

    private void getFirstPageData() {
        currentPage = 1;
        currList.clear();
        getDataFromServer();
    }
}
