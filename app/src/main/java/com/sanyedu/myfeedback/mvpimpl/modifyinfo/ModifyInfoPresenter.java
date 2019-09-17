package com.sanyedu.myfeedback.mvpimpl.modifyinfo;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.CheckUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;
import okhttp3.Call;

public class ModifyInfoPresenter extends BasePresenter<ModifyInfoContacts.IModifyInfoUI> implements ModifyInfoContacts.IModifyInfoPresenter {
    public ModifyInfoPresenter(@NonNull ModifyInfoContacts.IModifyInfoUI view) {
        super(view);
    }


    @Override
    public void ModifyObj(final String id, final String tel) {

        if(!CheckUtils.isParasLegality(id,tel)){
            SanyLogs.e("params is null ,return");
            return;
        }

        SanyLogs.i("teUser:" + tel);
        String url = HttpUtil.getPort(HttpUtil.UPDATE_PERSON_OBJ_PORT);
        OkHttpUtils
                .post()
                .url(url)
//                .addParams(HttpUtil.UpdateObj.TYPE, type)
                .addParams(HttpUtil.UpdateObj.TE_ID,id)
                .addParams(HttpUtil.UpdateObj.TE_PHONE,tel)
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
                                    //刷新本地用户信息
                                    TeacherBean bean = SpHelper.getObj(ConstantUtil.USERINFO);
                                    bean.setTePhone(tel);
                                    try {
                                        SpHelper.putObj(ConstantUtil.USERINFO, bean);
                                    }catch (Exception e){
                                        SanyLogs.e(e.toString());
                                    }

                                    getView().showModifySuccess();

                                }
                            }
                        }
                );
    }
}
