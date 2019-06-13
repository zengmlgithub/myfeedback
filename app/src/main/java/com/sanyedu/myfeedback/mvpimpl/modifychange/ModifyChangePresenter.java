package com.sanyedu.myfeedback.mvpimpl.modifychange;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.sanyedu.myfeedback.activity.ModifyChangeActivity;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.*;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.mvpimpl.UpdatePicture.UpdatePictureService;
import com.sanyedu.myfeedback.mvpimpl.modifyinfo.ModifyInfoContacts;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;
import okhttp3.Call;

import java.util.List;

public class ModifyChangePresenter extends BasePresenter<ModifyChangeContacts.IModifyChangeUI> implements ModifyChangeContacts.IModifyChangePresenter {
    public ModifyChangePresenter(@NonNull ModifyChangeContacts.IModifyChangeUI view) {
        super(view);
    }

    @Override
    public void updateFeedback(ChangeFeedbackBean changeFeedbackBean){
        SanyLogs.i("ChangeFeedbackBean:" + changeFeedbackBean.toString());
        String url = HttpUtil.getPort(HttpUtil.UPLOAD_SUBRECTIFICATION_PORT);

        OkHttpUtils
                .post()
                .url(url)
               .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_ID,changeFeedbackBean.getFeedbackId())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_STATUS,changeFeedbackBean.getFeedbackStatus())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_CONTENT,changeFeedbackBean.getFeedbackContent())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_PERID,changeFeedbackBean.getFeedbackPerid())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_PERNAME,changeFeedbackBean.getFeedbackPername())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_PERDEPT,changeFeedbackBean.getFeedbackPerdept())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_FILEA,changeFeedbackBean.getFeedbackFilea())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_FILEB,changeFeedbackBean.getFeedbackFileb())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_FILEC,changeFeedbackBean.getFeedbackFilec())
                .build()
                .execute(
                        new BaseModelCallback<String>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                getView().updateFeedbackResult(ModifyChangeContacts.IModifyChangeUI.UPDATE＿FAILURE);
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().updateFeedbackResult(ModifyChangeContacts.IModifyChangeUI.UPDATE＿FAILURE);
                                    return;
                                }
//                                SanyLogs.i(response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().updateFeedbackResult(ModifyChangeContacts.IModifyChangeUI.UPDATE＿FAILURE);
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    getView().updateFeedbackResult(ModifyChangeContacts.IModifyChangeUI.UPDATE＿FAILURE);
                                    return;
                                }

//                                String noticeBean = response.getObj();
//                                ToastUtil.showLongToast("反馈成功");
                                getView().updateFeedbackResult(ModifyChangeContacts.IModifyChangeUI.UPDATE＿SUCCESS);
                            }
                        }
                );
    }


    @Override
    public void updateFeedback(List<String> files,final ChangeFeedbackBean changeFeedbackBean) {
        if (files == null || files.size() <= 0) {
            SanyLogs.e("file is null,return");
            return;
        }

        UpdatePictureService updatePictureService = new UpdatePictureService(files, new UpdatePictureService.UpdateFinishedListener() {
            @Override
            public void updateFinished(UpdatePictureService service,List<String> serverPathList) {
                if(service.hasPhoto()){
                    if(changeFeedbackBean != null){
                        changeFeedbackBean.setFeedbackFilea(service.getServicePathA(serverPathList));
                        changeFeedbackBean.setFeedbackFileb(service.getServicePathB(serverPathList));
                        changeFeedbackBean.setFeedbackFilec(service.getServicePathC(serverPathList));
                        updateFeedback(changeFeedbackBean);
                    }
                }
            }
        });

        updatePictureService.postFiles();
    }
}
