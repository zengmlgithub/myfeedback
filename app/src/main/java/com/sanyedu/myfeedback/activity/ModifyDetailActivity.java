package com.sanyedu.myfeedback.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.StartUtils;
import com.sanyedu.myfeedback.utils.ToastUtil;
import com.sanyedu.myfeedback.widget.CloseFeedbackDialog;

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

    private String feedbackId = "";

    @BindView(R.id.modify_fk_ib)
    ImageButton modifyFkIb;

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

        initFeedbackId();
        initOperator();

        modifyTv = opeartorRl.findViewById(R.id.modify_pwd_tv);
        closeTv = opeartorRl.findViewById(R.id.logout_tv);
        modifyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:去修改
//                StartUtils.startActivity();
                StartUtils.startActivity(ModifyDetailActivity.this,ModifyChangeActivity.class,feedbackId);
            }
        });

        closeTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                finish();
                showCloaseDialog();
            }
        });



        adapter = new ModifyDetailAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    private void initFeedbackId() {
        Intent intent = getIntent();
        if(intent != null){
            feedbackId = intent.getStringExtra(ConstantUtil.ID);
            SanyLogs.i("feedbackId:" + feedbackId);
        }
    }

    private void initOperator() {
        if("3".equals(feedbackId) || "4".equals(feedbackId)){
            modifyFkIb.setVisibility(View.VISIBLE);
        }else{
            modifyFkIb.setVisibility(View.INVISIBLE);
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

    private CloseFeedbackDialog closeFeedbackDialog;
    private void showCloaseDialog(){
        if(closeFeedbackDialog == null){
            closeFeedbackDialog = new CloseFeedbackDialog(ModifyDetailActivity.this, R.style.sany_dialog, new CloseFeedbackDialog.OnClickListener() {
                @Override
                public void onPositive(Dialog dialog, boolean cancel) {
                    //TODO:提交
                    submit(closeFeedbackDialog.getContent());
                    dialog.dismiss();
                }

                @Override
                public void onNevige(Dialog dialog, boolean confirm) {
                    //关闭
                    dialog.dismiss();
                }
            });
        }

        if(!closeFeedbackDialog.isShowing()){
            closeFeedbackDialog.show();
        }
    }

    //提交内容
    private void submit(String content){
        if(TextUtils.isEmpty(content)){
            ToastUtil.showLongToast("请输入内容");
            return;
        }

        //TODO:将内容反馈到服务器上
    }
}
