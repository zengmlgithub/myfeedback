package com.sanyedu.feedback.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.adapter.ModifyDetailAdapter;
import com.sanyedu.feedback.base.SanyBaseActivity;
import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.model.DetailBean;
import com.sanyedu.feedback.mvpimpl.modifieddetail.ModifiedDetailContacts;
import com.sanyedu.feedback.mvpimpl.modifieddetail.ModifiedDetailPresenter;
import com.sanyedu.feedback.utils.HttpUtil;

/**
 * 整改详情
 */
public class ModifyDetailActivity extends SanyBaseActivity<ModifiedDetailPresenter> implements ModifiedDetailContacts.IModifiedDetailUI, View.OnClickListener {

    private TextView titleTv;  //标题
    private TextView contentTv;
    private ImageButton gobackBtn;
    private ImageButton modifyBtn;
    private TextView addressTv;
    private TextView dateTv;

    private ImageView photo1Iv;
    private ImageView photo2Iv;
    private ImageView photo3Iv;

    private RecyclerView recyclerView;
    private ModifyDetailAdapter adapter;



    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.goback_ib){
            finish();
       }
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if(intent != null){
            String id = intent.getStringExtra(HttpUtil.NoticeDetail.ID);
            SanyLogs.i("get id from NoticeDetailActivity:" + id);
            getPresenter().getDetail(id);
        }
    }

    @Override
    protected void findViews() {
        titleTv = findViewById(R.id.fk_single_title);
        contentTv = findViewById(R.id.fk_single_detail);
        gobackBtn = findViewById(R.id.goback_ib);
        modifyBtn = findViewById(R.id.modify_fk_ib);
        addressTv = findViewById(R.id.fk_single_pos);
        dateTv = findViewById(R.id.date_tv);
        photo1Iv = findViewById(R.id.detail_1);
        photo2Iv = findViewById(R.id.detail_2);
        photo3Iv = findViewById(R.id.detail_3);

        recyclerView = findViewById(R.id.fk_info_rv);
        adapter = new ModifyDetailAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);


    }

    @Override
    protected void setListeners() {
        gobackBtn.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_fk_detail;
    }

    @Override
    public ModifiedDetailPresenter onBindPresenter() {
        return new ModifiedDetailPresenter(this);
    }


    @Override
    public void setDetail(DetailBean bean) {
        SanyLogs.i(bean.toString());
        if (bean != null){
            titleTv.setText(bean.getFeedbackTitle());
            contentTv.setText(bean.getFeedbackContent());
            addressTv.setText(bean.getFeedbackAdress());
            dateTv.setText(bean.getFeedbackPubtime());

            if(adapter != null){
                adapter.setList(bean.getDetailedList());
            }
        }
    }
}
