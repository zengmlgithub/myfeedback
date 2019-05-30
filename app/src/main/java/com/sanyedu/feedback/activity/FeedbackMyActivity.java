package com.sanyedu.feedback.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.sanyedu.feedback.R;
import com.sanyedu.feedback.base.SanyBaseActivity;
import com.sanyedu.feedback.fragment.BaseFeedbackMyFragment;
import com.sanyedu.feedback.fragment.BaseMyFeedbackFragment;
import com.sanyedu.feedback.fragment.FeedbackAdapter;
import com.sanyedu.feedback.model.Records;
import com.sanyedu.feedback.mvpimpl.myfeedback.MyFeedbackContacts;
import com.sanyedu.feedback.mvpimpl.myfeedback.MyFeedbackPresenter;
import com.sanyedu.feedback.utils.StartUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 反馈我的
 */
public class FeedbackMyActivity extends SanyBaseActivity implements MyFeedbackContacts.IMyFeedbackUI, View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private ArrayList<String> titles;
    private FeedbackAdapter feedbackAdapter;

    private ImageButton gobackIB;
    private TextView gotoFeedbackTv;
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.goback_iv){
            finish();
        }else if (v.getId() == R.id.go_feedback_tv){
            StartUtils.startActivity(this,GotoFeedbackActivity.class);
        }
    }

    @Override
    protected void initData() {

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

    @Override
    protected void findViews() {
        tabLayout = findViewById(R.id.type_tl);
        viewPager = findViewById(R.id.vp_content);
        gobackIB = findViewById(R.id.goback_iv);
        gotoFeedbackTv = findViewById(R.id.go_feedback_tv);
    }

    @Override
    protected void setListeners() {
        gobackIB.setOnClickListener(this);
        gotoFeedbackTv.setOnClickListener(this);
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
