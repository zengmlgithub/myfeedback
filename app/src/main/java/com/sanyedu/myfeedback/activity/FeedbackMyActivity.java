package com.sanyedu.myfeedback.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.fragment.BaseFeedbackMyFragment;
import com.sanyedu.myfeedback.fragment.FeedbackAdapter;
import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvpimpl.myfeedback.MyFeedbackContacts;
import com.sanyedu.myfeedback.mvpimpl.myfeedback.MyFeedbackPresenter;
import com.sanyedu.myfeedback.utils.StartUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 反馈我的
 */
public class FeedbackMyActivity extends SanyBaseActivity implements MyFeedbackContacts.IMyFeedbackUI {
    @BindView(R.id.type_tl)
    TabLayout tabLayout;

    @BindView(R.id.vp_content)
    ViewPager viewPager;

    private List<Fragment> fragments;
    private ArrayList<String> titles;
    private FeedbackAdapter feedbackAdapter;


    @Override
    protected void initData() {
        ButterKnife.bind(this);
        fragments = new ArrayList<>();

        BaseFeedbackMyFragment submitedFragment = BaseFeedbackMyFragment.newInstance("1");
        BaseFeedbackMyFragment checkedFragment = BaseFeedbackMyFragment.newInstance("2");
        BaseFeedbackMyFragment waittingFragment = BaseFeedbackMyFragment.newInstance("3");
        BaseFeedbackMyFragment finishFragment =  BaseFeedbackMyFragment.newInstance("4");

        fragments.add(submitedFragment);
        fragments.add(checkedFragment);
        fragments.add(waittingFragment);
        fragments.add(finishFragment);

        titles = new ArrayList<>();
        titles.add("待整改");
        titles.add("整改中");
        titles.add("已整改");
        titles.add("已关闭");

        feedbackAdapter = new FeedbackAdapter(getSupportFragmentManager(), this, fragments, titles,tabLayout);
        viewPager.setAdapter(feedbackAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    @OnClick(R.id.goback_iv)
    public void close(){
        finish();
    }

    @OnClick(R.id.go_feedback_tv)
    public void goFeebackOnClick() {
        StartUtils.startActivity(this,GotoFeedbackActivity2.class);
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
