package com.sanyedu.feedback.fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.base.BaseFragment;
import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvpimpl.todayfeedback.TodayFeedbackContacts;
import com.sanyedu.feedback.mvpimpl.todayfeedback.TodayFeedbackPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 今日反馈fragment页面
 */
public class MainFkFragment extends BaseFragment<TodayFeedbackPresenter> implements TodayFeedbackContacts.ITodayFeedbackUI {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private ArrayList<String> titles;
    private FeedbackAdapter feedbackAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_fk;
    }

    @Override
    protected void init(View view) {

        viewPager = view.findViewById(R.id.vp_content);
        tabLayout = view.findViewById(R.id.title_tl);

        fragments = new ArrayList<>();
        fragments.add(new MainNeedModifyFragment());
        fragments.add(new MainHasModifiedFragment());

        titles = new ArrayList<>();
        titles.add("待整改");
        titles.add("已整改");

        feedbackAdapter = new FeedbackAdapter(getChildFragmentManager(), getActivity(), fragments, titles,tabLayout);
        viewPager.setAdapter(feedbackAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public TodayFeedbackPresenter onBindPresenter() {
        return new TodayFeedbackPresenter(this);
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
