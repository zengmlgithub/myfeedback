package com.sanyedu.myfeedback.activity;

import android.view.View;

import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.mvpimpl.modifypwd.ModifyPwdContacts;
import com.sanyedu.myfeedback.mvpimpl.modifypwd.ModifyPwdPresenter;

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
