package com.sanyedu.myfeedback.mvpimpl.modifieddetail;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.*;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.CheckUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;

import okhttp3.Call;

public class ModifiedDetailPresenter extends BasePresenter<ModifiedDetailContacts.IModifiedDetailUI> implements ModifiedDetailContacts.IModifiedDetailPresenter {
    public ModifiedDetailPresenter(@NonNull ModifiedDetailContacts.IModifiedDetailUI view) {
        super(view);
    }

    @Override
    public void getDetail(@NonNull String id) {

        if(CheckUtils.isParasLegality(id) == false){
            SanyLogs.e("param is illegac,return!");
            return;
        }

        String url = HttpUtil.getPort(HttpUtil.MODIFIED_DETAIL_PORT);
        OkHttpUtils
                .post()
                .url(url)
                .addParams(HttpUtil.MoDifiedDetail.ID, id)
                .build()
                .execute(
                        new BaseModelCallback<DetailBean>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<DetailBean> response, int id) {
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
                                }

                                DetailBean detailBean = response.getObj();
                                if (detailBean != null){
                                    getView().setDetail(detailBean);
                                }else{
                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                }
                            }
                        }
                );
    }

    @Override
    public void closeFeedback(String id, String feedbackContent, String feedbackPerid, String feedbackPername, String feedbackPerdept){
        String url = HttpUtil.getPort(HttpUtil.CLOASE_FEEDBACK_PORT);
        OkHttpUtils
                .post()
                .url(url)
                .addParams(HttpUtil.CloseFeedback.FEEDBACK_ID, id)
                .addParams(HttpUtil.CloseFeedback.FEEDBACK_CONTENT, feedbackContent)
                .addParams(HttpUtil.CloseFeedback.FEEDBACK_PERSON_ID, feedbackPerid)
                .addParams(HttpUtil.CloseFeedback.FEEDBACK_PERSON_NAME,feedbackPername)
                .addParams(HttpUtil.CloseFeedback.FEEDBACK_PERSON_DEPT,feedbackPerdept)
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
                                }


                            }
                        }
                );
    }
}
