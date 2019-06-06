package com.sanyedu.myfeedback.activity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ModifyInfoActivity extends SanyBaseActivity<ModifyInfoPresenter> implements ModifyInfoContacts.IModifyInfoUI {

//    @BindView(R.id.goback_ib)
//    ImageButton gobackIB;

//    @BindView(R.id.title_tv)
//    TextView titleTv;

    @BindView(R.id.modify_content_etw)
    EditText modifyEt;

    @Override
    protected void initData() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modify_info;
    }

    @Override
    public ModifyInfoPresenter onBindPresenter() {
        return new ModifyInfoPresenter(this);
    }

    @Override
    public void showModifySuccess() {
        ToastUtil.showLongToast(R.string.modify_email_success);
        //TODO：反回主页时对主页进行刷新
    }

    @OnClick(R.id.confirm_btn)
    public void confirm(){

        String emailStr = modifyEt.getText().toString().trim();
        if(TextUtils.isEmpty(emailStr)){
            ToastUtil.showLongToast(R.string.please_input_email);
            return;
        }

        TeacherBean newBean = createNewTeacher();
        if(newBean != null) {
            newBean.setTeEmail(emailStr);
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

    @OnClick(R.id.goback_ib)
    public void goback(){
        finish();
    }
}
