package com.sanyedu.myfeedback.mvpimpl.modifychange;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.*;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.mvpimpl.UpdatePicture.UpdatePictureService;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.CheckUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;
import okhttp3.Call;

import java.util.List;

public class ModifyChangePresenter extends BasePresenter<ModifyChangeContacts.IModifyChangeUI> implements ModifyChangeContacts.IModifyChangePresenter {
    public ModifyChangePresenter(@NonNull ModifyChangeContacts.IModifyChangeUI view) {
        super(view);
    }


    private void updateFeedback(@NonNull ChangeFeedbackBean changeFeedbackBean,String pathA,String pathB,String pathC){

        String url = HttpUtil.getPort(HttpUtil.UPLOAD_SUBRECTIFICATION_PORT);
        SanyLogs.i("updateFeedback:" + changeFeedbackBean.toString());

        try {
            if(!CheckUtils.isAllObjFieldLegacity(changeFeedbackBean)){
                SanyLogs.e("ChangeFeedbackBean is null,return!");
                return;
            }
        }catch (Exception e){
            SanyLogs.e(e.toString());
            return;
        }

        OkHttpUtils
                .post()
                .url(url)
               .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_ID,changeFeedbackBean.getFeedbackId())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_STATUS,changeFeedbackBean.getFeedbackStatus())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_CONTENT,changeFeedbackBean.getFeedbackContent())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_PERID,changeFeedbackBean.getFeedbackPerid())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_PERNAME,changeFeedbackBean.getFeedbackPername())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_PERDEPT,changeFeedbackBean.getFeedbackPerdept())
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_FILEA,pathA)
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_FILEB,pathB)
                .addParams(HttpUtil.UpdateFeedbackState.FEEDBACK_FILEC,pathC)
                .build()
                .execute(
                        new BaseModelCallback<String>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
//                                getView().updateFeedbackResult(ModifyChangeContacts.IModifyChangeUI.UPDATE＿FAILURE);
                                getView().updateFeedbackFailure("上传失败");
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().updateFeedbackFailure("上传失败");
                                    return;
                                }
//                                SanyLogs.i(response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().updateFeedbackFailure("上传失败");
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    getView().updateFeedbackFailure("上传失败");
                                    return;
                                }

//                                String noticeBean = response.getObj();
//                                ToastUtil.showLongToast("反馈成功");
                                getView().updateFeedbackSuccess();
                            }
                        }
                );
    }



    @Override
    public void updateFeedback(final List<String> files, final ChangeFeedbackBean changeFeedbackBean) {


        if (files == null || files.size() <= 0) {
            SanyLogs.e("file is null,return");
//            return;
            getView().updateFeedbackFailure("请上传图片");

        }else{
            UpdatePictureService updatePictureService = new UpdatePictureService(files, new UpdatePictureService.UpdateFinishedListener() {
                @Override
                public void updateFinished(UpdatePictureService service,List<String> serverPathList) {
                    if(service.hasPhoto()){
                        if(changeFeedbackBean != null){
                            updateFeedback(changeFeedbackBean,UpdatePictureService.getServicePathA(serverPathList),UpdatePictureService.getServicePathB(serverPathList),UpdatePictureService.getServicePathC(serverPathList));
                        }
                    }
                }

                @Override
                public void updateFailure(UpdatePictureService service, String msg) {
                    getView().updateFeedbackFailure(msg);
                }
            });

            updatePictureService.postFiles();
        }
    }
}
