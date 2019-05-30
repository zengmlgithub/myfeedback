package com.sanyedu.feedback.activity;

import android.view.View;


import com.sanyedu.feedback.R;
import com.sanyedu.feedback.base.SanyBaseActivity;
import com.sanyedu.feedback.mvpimpl.gotofeedback.GotoFeedbackContacts;
import com.sanyedu.feedback.mvpimpl.gotofeedback.GotoFeedbackPresenter;


/**
 * 提交反馈的页面
 */
public class GotoFeedbackActivity extends SanyBaseActivity<GotoFeedbackPresenter> implements GotoFeedbackContacts.IGotoFeedbackUI, View.OnClickListener {
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

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
