package com.sanyedu.myfeedback.activity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.mvpimpl.modifypwd.ModifyPwdContacts;
import com.sanyedu.myfeedback.mvpimpl.modifypwd.ModifyPwdPresenter;

/**
 * 修改密码
 */
public class ModifyPwdActivity extends SanyBaseActivity<ModifyPwdPresenter> implements ModifyPwdContacts.IModifyPwdUI {

    @BindView(R.id.input_first_pwd_et)
    EditText originPwdEt;

    @BindView(R.id.input_second_pwd_et)
    EditText newPwdEt;

    @BindView(R.id.input_third_pwd_et)
    EditText confirmPwdEt;

    @BindView(R.id.first_hint_tv)
    TextView originPwdTv;

    @BindView(R.id.second_hint_tv)
    TextView newPwdTv;

    @BindView(R.id.third_hint_tv)
    TextView confirmPwdTv;


    @OnClick(R.id.confirm_btn)
    public void onClick(View v) {
        SanyLogs.i("you have click confirm");
    }


    @Override
    protected void initData() {
        ButterKnife.bind(this);

    }

//    @Override
//    protected void findViews() {
//        //TODO:采用butterknife后，不需要findviews了
//    }

//    @Override
//    protected void setListeners() {
//        confirmBtn.setOnClickListener(this);
//    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public ModifyPwdPresenter onBindPresenter() {
        return new ModifyPwdPresenter(this);
    }
}
