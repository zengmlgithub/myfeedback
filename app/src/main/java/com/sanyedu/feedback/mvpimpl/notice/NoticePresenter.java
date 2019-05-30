package com.sanyedu.feedback.mvpimpl.notice;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.model.BaseModel;
import com.sanyedu.feedback.model.BaseModelCallback;
import com.sanyedu.feedback.model.JsonGenericsSerializator;
import com.sanyedu.feedback.model.NoticeBean;
import com.sanyedu.feedback.model.PageNoticeBean;
import com.sanyedu.feedback.model.TokenModel;
import com.sanyedu.feedback.mvp.BasePresenter;
import com.sanyedu.feedback.okhttp.OkHttpUtils;
import com.sanyedu.feedback.okhttp.callback.GenericsCallback;
import com.sanyedu.feedback.share.SpHelper;
import com.sanyedu.feedback.utils.ConstantUtil;
import com.sanyedu.feedback.utils.ErrorUtils;
import com.sanyedu.feedback.utils.HttpUtil;
import com.sanyedu.feedback.utils.MD5Utils;
import com.sanyedu.feedback.utils.ToastUtil;

import java.util.ArrayList;

import okhttp3.Call;

public class NoticePresenter extends BasePresenter<NoticeContacts.INoticeUI> implements NoticeContacts.INoticePresenter {
    public NoticePresenter(@NonNull NoticeContacts.INoticeUI view) {
        super(view);
    }

    @Override
    public void getNotices() {
        String url = HttpUtil.getPort(HttpUtil.NOTICE_PORT);

        OkHttpUtils
                .post()
                .url(url)
                .addParams(HttpUtil.Notice.START_PAGE, "1")
                .addParams(HttpUtil.Notice.EVERY_PAGE, "10")
                .build()
                .execute(new BaseModelCallback<PageNoticeBean>(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SanyLogs.e(e.toString());
                    }

                    @Override
                    public void onResponse(BaseModel<PageNoticeBean> response, int id) {
//                        SanyLogs.i(response.toString());

                        if(response == null){
                            ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                            return ;
                        }

                        String code = response.getCode();
                        if (TextUtils.isEmpty(code ) || !"1".equals(code)){
                            ToastUtil.showLongToast(response.getInfo());
                            //TODO:这时弹出错误的原因
                        }else{
                            PageNoticeBean pageNoticeBean= response.getObj();
                            if(pageNoticeBean != null){
                                ArrayList<NoticeBean> noticeList = pageNoticeBean.getpNotice();
                                if(noticeList != null && noticeList.size() > 0){
                                    getView().setNotices(noticeList);
                                }else{
                                    getView().setNotices(null);
                                }
                            }
                        }


                    }
                });
    }
}
