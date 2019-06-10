package com.sanyedu.myfeedback.mvpimpl.MyFeedbackFragment;

import android.support.annotation.NonNull;

import android.text.TextUtils;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.model.PageRecordBean;
import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;
import okhttp3.Call;

import java.util.List;

public class MyFeedbackFragmentPresenter extends BasePresenter<MyFeedbackFragmentContacts.IMyFeedbackFragmentUI> implements MyFeedbackFragmentContacts.IMyFeedbacFragmentPresenter  {
    public MyFeedbackFragmentPresenter(@NonNull MyFeedbackFragmentContacts.IMyFeedbackFragmentUI view) {
        super(view);
    }

    @Override
    public void getFeedbacks(String startPage, String everyPage, String id, String type) {
        String url = HttpUtil.getPort(HttpUtil.MY_FEEDBACK_PORT);
        SanyLogs.i("request:" + "id:" + id + "====type:" + type);
//        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
//                .addHeader(ConstantUtil.AUTHORIZATION, tokenValue)
                .addParams(HttpUtil.TodayFeedback.START_PAGE, startPage)
                .addParams(HttpUtil.TodayFeedback.EVERY_PAGE,everyPage)
                .addParams(HttpUtil.TodayFeedback.TYPE,type)
                .addParams(HttpUtil.MyFeedback.ID,id)
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
                                SanyLogs.i("getfeedback:" + response.toString());
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
                                    SanyLogs.i("sanyLog~~~~~~111111");
                                    if (recordsList != null && recordsList.size() > 0){
                                        SanyLogs.i("sanyLog~~~~~~222222");
                                        getView().setFeebacks(recordsList);
                                    }else{
                                        SanyLogs.i("sanyLog~~~~~~333333");
                                        //这时是没有出错，但没有记录的情况
//                                        ToastUtil.showLongToast(response.getInfo());
                                    }
                                }else{
                                    //TODO:这时是没有出错，但没有记录的情况
                                    SanyLogs.i("sanyLog~~~~~~444444444444444");
//                                    ToastUtil.showLongToast(response.getInfo());
                                }
                            }
                        }
                );
    }
}
