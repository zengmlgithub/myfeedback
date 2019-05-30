package com.sanyedu.feedback.activity;

import android.view.View;


import android.widget.ImageView;
import android.widget.TextView;
import com.sanyedu.feedback.R;
import com.sanyedu.feedback.base.SanyBaseActivity;
import com.sanyedu.feedback.mvpimpl.gotofeedback.GotoFeedbackContacts;
import com.sanyedu.feedback.mvpimpl.gotofeedback.GotoFeedbackPresenter;


/**
 * 提交反馈的页面
 */
public class GotoFeedbackActivity extends SanyBaseActivity<GotoFeedbackPresenter> implements GotoFeedbackContacts.IGotoFeedbackUI, View.OnClickListener {

    private TextView gobackTv;
    private TextView commitTv;

    private ImageView photo1Iv;
    private ImageView photo2Tv;
    private ImageView photo3Tv;

    @Override
    public void onClick(View v) {
        if (v.getId() == gobackTv.getId()){
            finish();
        }else if (v.getId() == commitTv.getId()){
            //TODO:提交数据
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void findViews() {
        gobackTv = findViewById(R.id.goback_iv);
        commitTv = findViewById(R.id.submit_tv);
    }

    @Override
    protected void setListeners() {
        gobackTv.setOnClickListener(this);
        commitTv.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_goto_feedback;
    }

    @Override
    public GotoFeedbackPresenter onBindPresenter() {
        return new GotoFeedbackPresenter(this);
    }
}
