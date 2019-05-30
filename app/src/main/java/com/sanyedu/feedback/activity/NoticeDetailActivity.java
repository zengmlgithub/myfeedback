package com.sanyedu.feedback.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.base.SanyBaseActivity;
import com.sanyedu.feedback.model.NoticeDetailBean;
import com.sanyedu.feedback.mvpimpl.gotofeedback.GotoFeedbackContacts;
import com.sanyedu.feedback.mvpimpl.gotofeedback.GotoFeedbackPresenter;
import com.sanyedu.feedback.mvpimpl.noticedetail.NoticeDetailContacts;
import com.sanyedu.feedback.mvpimpl.noticedetail.NoticeDetailPresenter;
import com.sanyedu.feedback.utils.HttpUtil;

public class NoticeDetailActivity extends SanyBaseActivity<NoticeDetailPresenter> implements NoticeDetailContacts.INoticeDetailUI, View.OnClickListener {
    private TextView titleTv;
    private TextView contentTv;
    private TextView pubPersonTv;
    private TextView dateTv;
    private ImageButton gobackIb;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goback_iv){
            finish();
        }
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if(intent != null){
            String id = intent.getStringExtra(HttpUtil.NoticeDetail.ID);
            getPresenter().getNoticeDetail(id);
        }
    }

    @Override
    protected void findViews() {
        titleTv = findViewById(R.id.feedback_single_tv);
        contentTv = findViewById(R.id.content_tv);
        pubPersonTv = findViewById(R.id.pub_person_tv);
        dateTv = findViewById(R.id.notice_date_time_tv);
        gobackIb = findViewById(R.id.goback_iv);
    }

    @Override
    protected void setListeners() {
        gobackIb.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_notice_detail;
    }

    @Override
    public NoticeDetailPresenter onBindPresenter() {
        return new NoticeDetailPresenter(this);
    }

    @Override
    public void setNoticeDetail(NoticeDetailBean detailBean) {
        if(detailBean != null){
            titleTv.setText(detailBean.getTitle());
            contentTv.setText(detailBean.getContent());
            pubPersonTv.setText(detailBean.getPubName());
            dateTv.setText(detailBean.getCreatetime());
        }
    }
}
