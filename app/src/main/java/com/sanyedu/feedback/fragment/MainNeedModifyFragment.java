package com.sanyedu.feedback.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.activity.ModifyDetailActivity;
import com.sanyedu.feedback.adapter.NeedModifyAdapter;
import com.sanyedu.feedback.adapter.WaitingModifiedAdapter;
import com.sanyedu.feedback.base.BaseFragment;

import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.model.FeedbackBean;
import com.sanyedu.feedback.model.Records;
import com.sanyedu.feedback.model.WaitingModifiedBean;
import com.sanyedu.feedback.mvpimpl.needmodified.NeedModifiedContacts;
import com.sanyedu.feedback.mvpimpl.needmodified.NeedModifiedPresenter;
import com.sanyedu.feedback.utils.StartUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 待整改 fragment
 */
public class MainNeedModifyFragment extends BaseFragment<NeedModifiedPresenter> implements NeedModifiedContacts.INeedModifiedUI {
    private RecyclerView listView;
    private NeedModifyAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_need_modify;
    }

    @Override
    protected void init(View view) {
        listView = view.findViewById(R.id.wait_el);
        adapter = new NeedModifyAdapter(getContext());
        listView.setHasFixedSize(true);
        listView.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layout);

        setData();
    }

    private void setData() {
        getPresenter().getRecords("1","10","1");
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
    public void showToast(String msg) {

    }

    @Override
    public void setRecords(List<Records> recordsList) {
//        for(Records records : recordsList){
//            SanyLogs.i(records);
//        }
        if(adapter != null) {
            adapter.setRecordsList(recordsList);
            adapter.setItemClickListener(new NeedModifyAdapter.OnItemClickListener() {
                @Override
                public void onClick(View view, int position, String id) {
                    StartUtils.startActivity(getActivity(), ModifyDetailActivity.class,id);
                }
            });
        }else{
            SanyLogs.i("adapter is null");
        }


    }
}
