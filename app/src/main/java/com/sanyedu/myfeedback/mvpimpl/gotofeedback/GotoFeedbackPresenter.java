package com.sanyedu.myfeedback.mvpimpl.gotofeedback;

import android.support.annotation.NonNull;

import android.text.TextUtils;
import com.nanchen.compresshelper.CompressHelper;
import com.sanyedu.myfeedback.app.FeedbackApplication;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.*;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.mvpimpl.UpdatePicture.UpdatePictureService;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;
import okhttp3.Call;

import java.util.List;

public class GotoFeedbackPresenter extends BasePresenter<GotoFeedbackContacts.IGotoFeedbackUI> implements GotoFeedbackContacts.IGoToFeedbackPresenter {


    public GotoFeedbackPresenter(@NonNull GotoFeedbackContacts.IGotoFeedbackUI view) {
        super(view);
    }

    @Override
    public void getDepart() {
        String url = HttpUtil.getPort(HttpUtil.GET_ALL_DEPART_PORT);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<List<DepartBean>>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<List<DepartBean>> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                List<DepartBean> departList = response.getObj();
                                if (departList != null && departList.size() > 0) {
                                    getView().setDepartList(departList);
                                } else {
                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                }
                            }
                        }
                );
    }

    @Override
    public void getPersonOfDepart(String departId) {
        String url = HttpUtil.getPort(HttpUtil.GET_ONE_DEPART_TEACHER_PORT);
        OkHttpUtils
                .post()
                .addParams(HttpUtil.OneDepartTeacher.DEPART_ID, departId)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<List<PersonBean>>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<List<PersonBean>> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                List<PersonBean> personBeans = response.getObj();
                                if (personBeans != null && personBeans.size() > 0) {
                                    getView().setPersonList(personBeans);
                                } else {
                                    ToastUtil.showLongToast(response.getInfo());
                                }
                            }
                        }
                );
    }

    @Override
    public void postFeedbackToServer(FeedbackItem item) {
        if (item == null) {
            SanyLogs.i("item is null,return!");
            return;
        }

        SanyLogs.i(item.toString());

        String url = HttpUtil.getPort(HttpUtil.POST_FEEDBACK_TO_SERVER_PORT);
        OkHttpUtils
                .post()
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_TITLE, item.getFeedbackTitle())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_ADDRESS, item.getFeedbackAddress())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_CONTENT, item.getFeedbackContent())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_DEPT, item.getFeedbackDept())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_PERSON_ID, item.getFeedbackPersonid())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_PERSON_NAME, item.getFeedbackPersonname())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_A, item.getFeedbackA())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_B, item.getFeedbackB())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_C, item.getFeedbackC())
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBL_NAME, item.getToResponsiblename())
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBLE_DEPT, item.getToResponsibledept())
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBLE_ID, item.getToResponsibleid())
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<String>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                String feedbackId = response.getObj();
                                if (!TextUtils.isEmpty(feedbackId)) {
                                    ToastUtil.showLongToast("反馈上传成功！");
                                }
                            }
                        }
                );
    }


    @Override
    public void postFeedbackToServer(List<String> files,final FeedbackItem item) {
        if (files == null || files.size() <= 0) {
            SanyLogs.e("file is null,return");
            return;
        }

        UpdatePictureService updatePictureService = new UpdatePictureService(files, new UpdatePictureService.UpdateFinishedListener() {
            @Override
            public void updateFinished(List<String> serverPathList) {
                if(item != null){
                    item.setFeedbackA(UpdatePictureService.getServicePathA(serverPathList));
                    item.setFeedbackB(UpdatePictureService.getServicePathB(serverPathList));
                    item.setFeedbackC(UpdatePictureService.getServicePathC(serverPathList));
                    postFeedbackToServer(item);
                }
            }
        });

        updatePictureService.postFiles();
    }



}
