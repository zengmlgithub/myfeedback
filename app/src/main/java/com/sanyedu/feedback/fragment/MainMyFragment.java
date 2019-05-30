package com.sanyedu.feedback.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.activity.FeedbackMyActivity;
import com.sanyedu.feedback.activity.MyFeedbackActivity;
import com.sanyedu.feedback.base.BaseFragment;
import com.sanyedu.feedback.model.TeacherBean;
import com.sanyedu.feedback.model.UserInfo;
import com.sanyedu.feedback.mvpimpl.mainmy.MainMyContacts;
import com.sanyedu.feedback.mvpimpl.mainmy.MainMyPresenter;
import com.sanyedu.feedback.share.SpHelper;
import com.sanyedu.feedback.utils.ConstantUtil;
import com.sanyedu.feedback.utils.StartUtils;

/**
 * 我的页面
 */
public class MainMyFragment extends BaseFragment<MainMyPresenter> implements MainMyContacts.IMainMyUI, View.OnClickListener {

    private TextView nameTv;
    private TextView departTv;

    private TextView myFeedbackTv; //我的反馈
    private TextView feedbackMyTv; //反馈我的

    private TextView telTv;
    private TextView emailTv;
    private TextView cardTv;
    private TextView posTv;

    private RelativeLayout myFeedbackRl;
    private RelativeLayout feedbackMyRl;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_my;
    }

    @Override
    protected void init(View view) {
        findViews(view);
        setData();
    }

    private void setData() {
        TeacherBean userInfo = SpHelper.getObj(ConstantUtil.USERINFO);

        nameTv.setText(userInfo.getUsername());
        departTv.setText(userInfo.getTeDept() + "|" + userInfo.getTePosi());

        emailTv.setText(userInfo.getTeEmail());
        telTv.setText(userInfo.getTePhone());
        cardTv.setText(userInfo.getTeJobnum());
        posTv.setText(userInfo.getTeComp());

//        myFeedbackTv.setText();
//        feedbackMyTv.setText();

        myFeedbackRl.setOnClickListener(this);
        feedbackMyRl.setOnClickListener(this);
    }

    private void findViews(View view){
        nameTv = view.findViewById(R.id.name_tv);
        departTv = view.findViewById(R.id.depart_tv);
        feedbackMyTv = view.findViewById(R.id.feedback_main_number_tv);
        myFeedbackTv = view.findViewById(R.id.main_fk_number_tv);

        telTv = view.findViewById(R.id.tel_tv);
        emailTv = view.findViewById(R.id.email_tv);
        cardTv = view.findViewById(R.id.card_tv);
        posTv = view.findViewById(R.id.pos_tv);

        feedbackMyRl = view.findViewById(R.id.fk_main_ll);
        myFeedbackRl = view.findViewById(R.id.main_fk_ll);
    }

    @Override
    public MainMyPresenter onBindPresenter() {
        return new MainMyPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.main_fk_ll){
            StartUtils.startActivity(getContext(), MyFeedbackActivity.class);
        }else if(v.getId() == R.id.fk_main_ll){  //反馈我的
            StartUtils.startActivity(getContext(), FeedbackMyActivity.class);
        }
    }
}
