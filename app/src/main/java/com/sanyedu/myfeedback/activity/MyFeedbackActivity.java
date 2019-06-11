package com.sanyedu.myfeedback.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.fragment.BaseMyFeedbackFragment;
import com.sanyedu.myfeedback.fragment.FeedbackAdapter;
import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvpimpl.myfeedback.MyFeedbackContacts;
import com.sanyedu.myfeedback.mvpimpl.myfeedback.MyFeedbackPresenter;
import com.sanyedu.myfeedback.utils.StartUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的反馈
 */
public class MyFeedbackActivity extends SanyBaseActivity<MyFeedbackPresenter> implements MyFeedbackContacts.IMyFeedbackUI/*, View.OnClickListener*/ {

    private List<Fragment> fragments;
    private ArrayList<String> titles;
    private FeedbackAdapter feedbackAdapter;

    @BindView(R.id.type_tl)
    TabLayout tabLayout;

    @BindView(R.id.vp_content)
    ViewPager viewPager;

    @OnClick(R.id.goback_iv)
    public void closePage() {
        finish();
    }


    @OnClick(R.id.go_feedback_tv)
    public void gotoFeedback() {
        StartUtils.startActivity(this, GotoFeedbackActivity2.class);
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        BaseMyFeedbackFragment submitedFragment = BaseMyFeedbackFragment.newInstance("1");
        BaseMyFeedbackFragment checkedFragment = BaseMyFeedbackFragment.newInstance("2");
        BaseMyFeedbackFragment waittingFragment = BaseMyFeedbackFragment.newInstance("3");
        BaseMyFeedbackFragment finishFragment = BaseMyFeedbackFragment.newInstance("4");

        fragments.add(submitedFragment);
        fragments.add(checkedFragment);
        fragments.add(waittingFragment);
        fragments.add(finishFragment);

        titles = new ArrayList<>();

        titles.add("已提交");
        titles.add("已审核");
        titles.add("整改中");
        titles.add("已完成");

        feedbackAdapter = new FeedbackAdapter(getSupportFragmentManager(), this, fragments, titles, tabLayout);
        viewPager.setAdapter(feedbackAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_feedback;
    }

    @Override
    public MyFeedbackPresenter onBindPresenter() {
        return new MyFeedbackPresenter(this);
    }

    @Override
    public void setMyFeedbacks(List<Records> recordsList) {

    }
}
