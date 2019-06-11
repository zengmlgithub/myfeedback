package com.sanyedu.myfeedback.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.adapter.ModifyDetailAdapter;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.DetailBean;
import com.sanyedu.myfeedback.mvpimpl.modifieddetail.ModifiedDetailContacts;
import com.sanyedu.myfeedback.mvpimpl.modifieddetail.ModifiedDetailPresenter;
import com.sanyedu.myfeedback.utils.HttpUtil;

/**
 * 整改详情
 */
public class ModifyDetailActivity extends SanyBaseActivity<ModifiedDetailPresenter> implements ModifiedDetailContacts.IModifiedDetailUI/*, View.OnClickListener*/ {
    @BindView(R.id.fk_single_title)
    TextView titleTv;  //标题

    @BindView(R.id.fk_single_detail)
    TextView contentTv;

    @BindView(R.id.fk_single_pos)
    TextView addressTv;

    @BindView(R.id.date_tv)
    TextView dateTv;
    @BindView(R.id.detail_1)
    ImageView photo1Iv;

    @BindView(R.id.detail_2)
    ImageView photo2Iv;

    @BindView(R.id.detail_3)
    ImageView photo3Iv;

    @BindView(R.id.fk_info_rv)
    RecyclerView recyclerView;

    @BindView(R.id.operator_rl)
    RelativeLayout opeartorRl;

    TextView modifyTv;
    TextView closeTv;


//    @BindView(R.id.modify_pwd_tv)
//    TextView modifyTv;//整改
//
//    @BindView(R.id.logout_ib)
//    TextView closeTv; //关闭

    //去整改
//    @OnClick(R.id.modify_pwd_tv)
//    public void modifyFeedback() {
//
//    }


    //去关闭
//    @OnClick(R.id.logout_ib)
//    public void closeFeedback() {
//
//    }

    @OnClick(R.id.modify_fk_ib)
    public void setVsibleOfOperator(){
        if(opeartorRl.getVisibility() == View.VISIBLE){
            opeartorRl.setVisibility(View.GONE);
        }else{
            opeartorRl.setVisibility(View.VISIBLE);
        }
    }



    private ModifyDetailAdapter adapter;

    @OnClick(R.id.goback_tv)
    public void closePage() {
        finish();
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);


//        modifyTv.setText("去整改");
//        closeTv.setText("去关闭");

        modifyTv = opeartorRl.findViewById(R.id.modify_pwd_tv);
        closeTv = opeartorRl.findViewById(R.id.logout_tv);
        modifyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:去修改
//                StartUtils.startActivity();
            }
        });

        closeTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        adapter = new ModifyDetailAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra(HttpUtil.NoticeDetail.ID);
            SanyLogs.i("get id from NoticeDetailActivity:" + id);
//            getPresenter().getDetail(id);
        }


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
        if (bean != null) {
            titleTv.setText(bean.getFeedbackTitle());
            contentTv.setText(bean.getFeedbackContent());
            addressTv.setText(bean.getFeedbackAdress());
            dateTv.setText(bean.getFeedbackPubtime());

            if (adapter != null) {
                adapter.setList(bean.getDetailedList());
            }
        }
    }
}
