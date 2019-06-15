package com.sanyedu.myfeedback.mvpimpl.modifypwd;

import android.support.annotation.NonNull;

import android.text.TextUtils;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.CheckUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;
import okhttp3.Call;

public class ModifyPwdPresenter extends BasePresenter<ModifyPwdContacts.IModifyPwdUI> implements ModifyPwdContacts.IModifyPwdPresenter {
    public ModifyPwdPresenter(@NonNull ModifyPwdContacts.IModifyPwdUI view) {
        super(view);
    }

    @Override
    public void modifyPwd(String type, String id, String userName, String oldpwd, String newPwd) {


        if(!CheckUtils.isParasLegality(type,id,userName,oldpwd,newPwd)){
            SanyLogs.e("params is null ,return");
            return;
        }

        String url = HttpUtil.getPort(HttpUtil.UPDATE_PERSONAL_PASSWORD_PORT);

//        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
//                .addHeader(ConstantUtil.AUTHORIZATION, tokenValue)
                .addParams(HttpUtil.UpdatePwd.TYPE, type)
                .addParams(HttpUtil.UpdatePwd.ID,id)
                .addParams(HttpUtil.UpdatePwd.USERNAME,userName)
                .addParams(HttpUtil.UpdatePwd.PASSWORD,oldpwd)
                .addParams(HttpUtil.UpdatePwd.NewPassword,newPwd)
                .build()
                .execute(
                        new BaseModelCallback<String>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
//                                SanyLogs.i(response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }else{
                                    getView().showSuccess();
                                }
                            }
                        }
                );
    }
}
