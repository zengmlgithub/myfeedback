package com.sanyedu.feedback.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.base.SanyBaseActivity;
import com.sanyedu.feedback.mvpimpl.login.LoginContacts;
import com.sanyedu.feedback.mvpimpl.login.LoginPresenter;
import com.sanyedu.feedback.utils.StartUtils;

public class LoginActivity extends SanyBaseActivity<LoginPresenter> implements LoginContacts.ILoginUI, View.OnClickListener{
    private EditText usernameEt;
    private EditText passwordEt;

    private Button commitBtn;


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.commit_btn){
            String username = usernameEt.getText().toString();
            String password = passwordEt.getText().toString();
            getPresenter().getToken(username,password, "2");
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void findViews() {
        usernameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);
        commitBtn =  findViewById(R.id.commit_btn);
    }

    @Override
    protected void setListeners() {
        commitBtn.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter onBindPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void startMain() {
        StartUtils.startActivity(LoginActivity.this, MainActivity.class);
    }
}
