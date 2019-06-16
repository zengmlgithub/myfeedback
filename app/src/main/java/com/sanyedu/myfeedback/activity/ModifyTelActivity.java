package com.sanyedu.myfeedback.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.mvpimpl.modifyinfo.ModifyInfoContacts;
import com.sanyedu.myfeedback.mvpimpl.modifyinfo.ModifyInfoPresenter;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.CheckUtils;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ModifyTelActivity extends SanyBaseActivity<ModifyInfoPresenter> implements ModifyInfoContacts.IModifyInfoUI {

    @BindView(R.id.modify_content_etw)
    EditText modifyEt;

    @BindView(R.id.check_tv)
    TextView checkTv;

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        modifyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkTv.getVisibility() == View.VISIBLE){
                    checkTv.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modify_tel;
    }

    @Override
    public ModifyInfoPresenter onBindPresenter() {
        return new ModifyInfoPresenter(this);
    }

    @Override
    public void showModifySuccess() {
        ToastUtil.showLongToast(R.string.modify_tel_success);
        //TODO：反回主页时对主页进行刷新
    }

    @OnClick(R.id.confirm_btn)
    public void confirm(){
        String telStr = modifyEt.getText().toString().trim();

        if(TextUtils.isEmpty(telStr)){
            checkTv.setVisibility(View.VISIBLE);
            checkTv.setText(getResources().getText(R.string.tel_null));
            return;
        }

        if(!CheckUtils.isMobile(telStr)){
            checkTv.setVisibility(View.VISIBLE);
            checkTv.setText(getResources().getText(R.string.error_tel));
        }

        TeacherBean newBean = createNewTeacher();
        if(newBean != null) {
            newBean.setTePhone(telStr);
            List<TeacherBean> beanList = new ArrayList<>();
            beanList.add(newBean);
            String str = new Gson().toJson(beanList);
            getPresenter().ModifyObj("1",str);
        }else{
            //TODO:当email没有输入的时候，这个时候是不需要改东西的时候，可以做一个用户提示
        }
    }

    private TeacherBean createNewTeacher() {
        TeacherBean bean = SpHelper.getObj(ConstantUtil.USERINFO);
        TeacherBean tempBean = new TeacherBean();
        tempBean.setId(bean.getId());
        return tempBean;
    }

    @OnClick(R.id.goback_tv)
    public void goback(){
        finish();
    }
}
