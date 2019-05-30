package com.sanyedu.feedback.fragment;

import android.view.View;
import android.widget.ExpandableListView;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.adapter.HasModifiedAdapter;
import com.sanyedu.feedback.adapter.WaitingModifiedAdapter;
import com.sanyedu.feedback.base.BaseFragment;
import com.sanyedu.feedback.model.FeedbackBean;
import com.sanyedu.feedback.model.WaitingModifiedBean;
import com.sanyedu.feedback.mvpimpl.hasmodified.HasModifiedContacts;
import com.sanyedu.feedback.mvpimpl.hasmodified.HasModifiedPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 已整改页面
 */
public class MainHasFeedbackFragment extends BaseFragment<HasModifiedPresenter> implements HasModifiedContacts.IHasModifiedUI {

    private ExpandableListView listView;
    private HasModifiedAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_has_modify;
    }

    @Override
    protected void init(View view) {
        listView = view.findViewById(R.id.wait_el);
        List<WaitingModifiedBean> beanList = getTestList();
        adapter = new HasModifiedAdapter(getActivity(),beanList);
        listView.setAdapter(adapter);

        for (int i=0; i<  beanList.size(); i++)
        {
            listView.expandGroup(i);
        };
    }

    @Override
    public HasModifiedPresenter onBindPresenter() {
        return new HasModifiedPresenter(this);
    }

    private List<WaitingModifiedBean> getTestList() {
        ArrayList<WaitingModifiedBean> tempList = new ArrayList<>();

        ArrayList<FeedbackBean> beanArrayList = new ArrayList<FeedbackBean>();
        beanArrayList.add(new FeedbackBean("","","","","","","","",""));
        beanArrayList.add(new FeedbackBean("","","","","","","","",""));
        beanArrayList.add(new FeedbackBean("","","","","","","","",""));

        tempList.add(new WaitingModifiedBean("5月11日",beanArrayList));

        return tempList;
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
