package com.sanyedu.myfeedback.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.mvpimpl.modifypwd.ModifyPwdContacts;
import com.sanyedu.myfeedback.mvpimpl.modifypwd.ModifyPwdPresenter;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.MD5Utils;
import com.sanyedu.myfeedback.utils.StartUtils;
import com.sanyedu.myfeedback.utils.ToastUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @OnClick(R.id.goback_ib)
    public void goback(){
        finish();
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);

        setListeners();
    }

    private void setListeners() {
        originPwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = originPwd();
                SanyLogs.i("pwd:" + password);
                if(!TextUtils.isEmpty(password)){
                    String conPwd = MD5Utils.getMD5(s.toString());
                    if(!password.equals(conPwd.toString())) {
                        originPwdTv.setText("你输入的密码不正确");
                        originPwdTv.setTextColor(getResources().getColor(R.color.red));
                    }else{
                        originPwdTv.setText("");
                    }
                }else{
                    originPwdTv.setText("暂时无原密码");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        newPwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //pwd
                String password = s.toString();

                Pattern p = Pattern.compile("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}");
                Matcher m = p.matcher(password);

                if (m.matches()){
                    newPwdTv.setText("");

                }else{
                    newPwdTv.setText("密码格式不正确");
                    newPwdTv.setTextColor(getResources().getColor(R.color.red));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmPwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //pwd
                String password = s.toString();
                String newPwdStr = newPwdEt.getText().toString();
                if(newPwdStr != null && newPwdStr.equals(password)){
                    confirmPwdTv.setText("");
                }else{
                    confirmPwdTv.setText("再次密码不一致");
                    confirmPwdTv.setTextColor(getResources().getColor(R.color.red));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private String originPwd(){
        TeacherBean teacherBean = SpHelper.getObj(ConstantUtil.USERINFO);
        String password = teacherBean.getPassword();
        return password;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public ModifyPwdPresenter onBindPresenter() {
        return new ModifyPwdPresenter(this);
    }

    @OnClick(R.id.confirm_btn)
    public void submitPwd(){
        String type = "1";
        TeacherBean bean = SpHelper.getObj(ConstantUtil.USERINFO);
        if(bean != null){
            String id =  bean.getId();
            String username = bean.getUsername();
            String pwd = originPwdEt.getText().toString().trim();
            String md5Pwd = MD5Utils.getMD5(pwd);
            String newPwd = newPwdEt.getText().toString().trim();
            String md5NewPwd = MD5Utils.getMD5(newPwd);
            getPresenter().modifyPwd(type,id,username,md5Pwd,md5NewPwd);
        }
    }

    @Override
    public void showSuccess() {
        ToastUtil.showLongToast("密码修改成功,请重新登录");
        SpHelper.clear(); //清空sp中的所有数据

        //将跳到登录界面
        StartUtils.startActivity(ModifyPwdActivity.this,LoginActivity.class);
    }
}
