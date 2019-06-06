package com.sanyedu.myfeedback.mvpimpl.modifyinfo;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.model.DetailBean;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;
import okhttp3.Call;

public class ModifyInfoPresenter  extends BasePresenter<ModifyInfoContacts.IModifyInfoUI> implements ModifyInfoContacts.IModifyInfoPresenter {
    public ModifyInfoPresenter(@NonNull ModifyInfoContacts.IModifyInfoUI view) {
        super(view);
    }


    @Override
    public void ModifyObj(String type, String obj) {
        SanyLogs.i("type:" + type);
        SanyLogs.i("teUser:" + obj);
        String url = HttpUtil.getPort(HttpUtil.UPDATE_PERSON_OBJ_PORT);
        OkHttpUtils
                .post()
                .url(url)
                .addParams(HttpUtil.UpdateObj.TYPE, type)
                .addParams(HttpUtil.UpdateObj.TE_USER,obj)
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

                                SanyLogs.i(response.toString());

                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }else{
                                    getView().showModifySuccess();
                                }
                            }
                        }
                );
    }
}
