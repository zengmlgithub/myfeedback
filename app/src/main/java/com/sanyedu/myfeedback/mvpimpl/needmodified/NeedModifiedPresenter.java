package com.sanyedu.myfeedback.mvpimpl.needmodified;

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

import java.util.List;

import okhttp3.Call;

import static com.sanyedu.myfeedback.utils.HttpUtil.TodayFeedback;

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
                                getView().showError(ErrorUtils.PARSE_ERROR);
                            }

                            @Override
                            public void onResponse(BaseModel<PageRecordBean> response, int id) {
                                if (response == null){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().showError(ErrorUtils.PARSE_ERROR);
                                    return;
                                }
//                                SanyLogs.i(response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().showError(ErrorUtils.PARSE_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
//                                    ToastUtil.showLongToast(response.getInfo());
                                    getView().showError(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                PageRecordBean noticeBean = response.getObj();
                                if (noticeBean != null){
                                    List<Records> recordsList = noticeBean.getRecords();
//                                    SanyLogs.i("sanyLog~~~~~~111111");
                                    if (recordsList != null && recordsList.size() > 0){
//                                        SanyLogs.i("sanyLog~~~~~~222222");
                                        getView().setRecords(recordsList,Integer.valueOf(noticeBean.getTotal()));
                                    }else{
                                        //这个不是错误，是没有更多记录了
//                                        ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                        getView().showNoMoreList();
                                    }
                                }else{
                                   getView().showError(ErrorUtils.SERVER_ERROR);
                                }
                            }
                        }
                );
    }
}
