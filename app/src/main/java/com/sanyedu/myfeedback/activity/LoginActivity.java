package com.sanyedu.myfeedback.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.mvpimpl.login.LoginContacts;
import com.sanyedu.myfeedback.mvpimpl.login.LoginPresenter;
import com.sanyedu.myfeedback.utils.StartUtils;

public class LoginActivity extends SanyBaseActivity<LoginPresenter> implements LoginContacts.ILoginUI/*, View.OnClickListener*/{
    @BindView(R.id.username_et)
     EditText usernameEt;
    @BindView(R.id.password_et)
     EditText passwordEt;


//    private Button commitBtn;


//    usernameEt = findViewById(R.id.username_et);
//    passwordEt = findViewById(R.id.password_et);
//    commitBtn =  findViewById(R.id.commit_btn);

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.commit_btn){
//            String username = usernameEt.getText().toString();
//            String password = passwordEt.getText().toString();
//            getPresenter().getToken(username,password, "2");
//        }
//
//    }

    @OnClick(R.id.commit_btn)
    public void commit(){
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        getPresenter().getToken(username,password, "2");
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
    }

//    @Override
//    protected void findViews() {
//
//    }
//
//    @Override
//    protected void setListeners() {
//        commitBtn.setOnClickListener(this);
//    }

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
