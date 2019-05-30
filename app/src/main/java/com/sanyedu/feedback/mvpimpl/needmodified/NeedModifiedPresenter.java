package com.sanyedu.feedback.mvpimpl.needmodified;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.model.BaseModel;
import com.sanyedu.feedback.model.BaseModelCallback;
import com.sanyedu.feedback.model.PageNoticeBean;
import com.sanyedu.feedback.model.PageRecordBean;
import com.sanyedu.feedback.model.Records;
import com.sanyedu.feedback.model.TeacherBean;
import com.sanyedu.feedback.mvp.BasePresenter;
import com.sanyedu.feedback.okhttp.OkHttpUtils;
import com.sanyedu.feedback.share.SpHelper;
import com.sanyedu.feedback.utils.ConstantUtil;
import com.sanyedu.feedback.utils.ErrorUtils;
import com.sanyedu.feedback.utils.HttpUtil;
import com.sanyedu.feedback.utils.MD5Utils;
import com.sanyedu.feedback.utils.ToastUtil;

import java.util.List;

import okhttp3.Call;

import static com.sanyedu.feedback.utils.HttpUtil.TodayFeedback;

public class NeedModifiedPresenter extends BasePresenter<NeedModifiedContacts.INeedModifiedUI> implements NeedModifiedContacts.INeedModifiedPresenter {
    public NeedModifiedPresenter(@NonNull NeedModifiedContacts.INeedModifiedUI view) {
        super(view);
    }

    @Override
    public void getRecords(String startPage, String everyPage,String type) {
        String url = HttpUtil.getPort(HttpUtil.TODYA_FEEDBACK_PORT);

//        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
//                .addHeader(ConstantUtil.AUTHORIZATION, tokenValue)
                .addParams(TodayFeedback.START_PAGE, startPage)
                .addParams(TodayFeedback.EVERY_PAGE,everyPage)
                .addParams(TodayFeedback.TYPE,type)
                .build()
                .execute(
                        new BaseModelCallback<PageRecordBean>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<PageRecordBean> response, int id) {
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

                                PageRecordBean noticeBean = response.getObj();
                                if (noticeBean != null){
                                    List<Records> recordsList = noticeBean.getRecords();
//                                    SanyLogs.i("sanyLog~~~~~~111111");
                                    if (recordsList != null && recordsList.size() > 0){
//                                        SanyLogs.i("sanyLog~~~~~~222222");
                                        getView().setRecords(recordsList);
                                    }else{
                                        ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                    }
                                }else{
                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                }
                            }
                        }
                );
    }
}
