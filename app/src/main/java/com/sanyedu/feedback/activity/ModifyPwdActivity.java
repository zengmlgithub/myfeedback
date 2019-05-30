package com.sanyedu.feedback.activity;

import android.view.View;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.base.SanyBaseActivity;
import com.sanyedu.feedback.mvpimpl.modifypwd.ModifyPwdContacts;
import com.sanyedu.feedback.mvpimpl.modifypwd.ModifyPwdPresenter;

public class ModifyPwdActivity extends SanyBaseActivity<ModifyPwdPresenter> implements ModifyPwdContacts.IModifyPwdUI, View.OnClickListener {
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
        return R.layout.activity_modify_pwd;
    }

    @Override
    public ModifyPwdPresenter onBindPresenter() {
        return new ModifyPwdPresenter(this);
    }
}
