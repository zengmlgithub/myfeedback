package com.sanyedu.myfeedback.mvpimpl.myfeedback;

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
import com.sanyedu.myfeedback.utils.CheckUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;

import java.util.List;

import okhttp3.Call;

/**
 * 我的反馈presenter
 */
public class MyFeedbackPresenter extends BasePresenter<MyFeedbackContacts.IMyFeedbackUI> implements MyFeedbackContacts.IMyFeedbackPresenter {
    public MyFeedbackPresenter(@NonNull MyFeedbackContacts.IMyFeedbackUI view) {
        super(view);
    }


    @Override
    public void getMyFeedbacks(@NonNull String startPage, @NonNull String everyPage, @NonNull String id, @NonNull String type) {


        if(!CheckUtils.isParasLegality(startPage,everyPage,id,type)){
            SanyLogs.e("params is null ,return");
            return;
        }

        String url = HttpUtil.getPort(HttpUtil.MY_FEEDBACK_PORT);

//        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
//                .addHeader(ConstantUtil.AUTHORIZATION, tokenValue)
                .addParams(HttpUtil.MyFeedback.START_PAGE, startPage)
                .addParams(HttpUtil.MyFeedback.EVERY_PAGE,everyPage)
                .addParams(HttpUtil.MyFeedback.ID,id)
                .addParams(HttpUtil.MyFeedback.TYPE,type)
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

                                PageRecordBean noticeBean = response.getObj();
                                if (noticeBean != null){
                                    List<Records> recordsList = noticeBean.getRecords();
//                                    SanyLogs.i("sanyLog~~~~~~111111");
                                    if (recordsList != null && recordsList.size() > 0){
//                                        SanyLogs.i("sanyLog~~~~~~222222");
                                        getView().setMyFeedbacks(recordsList);
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
