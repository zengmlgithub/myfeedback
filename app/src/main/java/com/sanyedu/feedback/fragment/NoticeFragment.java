package com.sanyedu.feedback.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.activity.NoticeDetailActivity;
import com.sanyedu.feedback.adapter.NoticeAdapter;
import com.sanyedu.feedback.base.BaseFragment;
import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.model.NoticeBean;
import com.sanyedu.feedback.mvpimpl.notice.NoticeContacts;
import com.sanyedu.feedback.mvpimpl.notice.NoticePresenter;
import com.sanyedu.feedback.utils.StartUtils;

import java.util.ArrayList;

/**
 * modified by zengml
 *
 * 通知页面
 *
 *
 */
public class NoticeFragment extends BaseFragment<NoticePresenter> implements NoticeContacts.INoticeUI {

    private RecyclerView recyclerView;
    private NoticeAdapter noticeAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_main_msg;
    }

    @Override
    protected void init(View view) {
        recyclerView = view.findViewById(R.id.main_msg_rv);
        recyclerView.setHasFixedSize(true);
        noticeAdapter = new NoticeAdapter(getActivity());
        recyclerView.setAdapter(noticeAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        setData();
    }

    private void setData() {
        getPresenter().getNotices();
    }

    @Override
    public NoticePresenter onBindPresenter() {
        return new NoticePresenter(this);
    }

    @Override
    public void setNotices(ArrayList<NoticeBean> notices) {
//        for(NoticeBean bean: notices){
//            SanyLogs.i(bean.toString());
//        }

        noticeAdapter.setNoticeList(notices);
        noticeAdapter.setOnItemClickListener(new NoticeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String id) {
                SanyLogs.i("noticeclick~~~~position:" +position + ",id:" + id);
                StartUtils.startActivity(getContext(), NoticeDetailActivity.class,id);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

}
