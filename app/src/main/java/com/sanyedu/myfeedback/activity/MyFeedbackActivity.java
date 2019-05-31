package com.sanyedu.myfeedback.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;
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
public class MyFeedbackActivity extends SanyBaseActivity<MyFeedbackPresenter> implements MyFeedbackContacts.IMyFeedbackUI, View.OnClickListener {

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
        }else if(v.getId() == R.id.go_feedback_tv){
            StartUtils.startActivity(this,GotoFeedbackActivity.class);
        }
    }

    @Override
    protected void initData() {

        fragments = new ArrayList<>();
        BaseMyFeedbackFragment submitedFragment = BaseMyFeedbackFragment.newInstance("1");
        BaseMyFeedbackFragment checkedFragment = BaseMyFeedbackFragment.newInstance("2");
        BaseMyFeedbackFragment waittingFragment = BaseMyFeedbackFragment.newInstance("3");
        BaseMyFeedbackFragment finishFragment =  BaseMyFeedbackFragment.newInstance("4");

        fragments.add(submitedFragment);
        fragments.add(checkedFragment);
        fragments.add(waittingFragment);
        fragments.add(finishFragment);

        titles = new ArrayList<>();
        titles.add("已提交");
        titles.add("已审核");
        titles.add("整改中");
        titles.add("已完成");

        feedbackAdapter = new FeedbackAdapter(getSupportFragmentManager(), this, fragments, titles,tabLayout);
        viewPager.setAdapter(feedbackAdapter);
        tabLayout.setupWithViewPager(viewPager);

        gobackIB.setOnClickListener(this);
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
