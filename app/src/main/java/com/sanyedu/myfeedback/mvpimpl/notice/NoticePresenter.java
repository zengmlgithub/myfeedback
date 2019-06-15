package com.sanyedu.myfeedback.mvpimpl.notice;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.model.NoticeBean;
import com.sanyedu.myfeedback.model.PageNoticeBean;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpParasLegalityUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;

import java.util.ArrayList;

import okhttp3.Call;

public class NoticePresenter extends BasePresenter<NoticeContacts.INoticeUI> implements NoticeContacts.INoticePresenter {
    public NoticePresenter(@NonNull NoticeContacts.INoticeUI view) {
        super(view);
    }

    @Override
    public void getNotices(@NonNull String startPage,@NonNull String pageCount) {

        if(!HttpParasLegalityUtils.isParasLegality(startPage,pageCount)){
            SanyLogs.e("para is null,return!");
            return;
        }

        String url = HttpUtil.getPort(HttpUtil.NOTICE_PORT);

        OkHttpUtils
                .post()
                .url(url)
                .addParams(HttpUtil.Notice.START_PAGE, startPage)
                .addParams(HttpUtil.Notice.EVERY_PAGE, pageCount)
                .build()
                .execute(new BaseModelCallback<PageNoticeBean>(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SanyLogs.e(e.toString());
                        getView().showError(ErrorUtils.PARSE_ERROR);
                    }

                    @Override
                    public void onResponse(BaseModel<PageNoticeBean> response, int id) {
                        //TODO:这个通知有点简单，需要进一步加强
                        if(response == null){
//                            ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                            getView().showError(ErrorUtils.SERVER_ERROR);
                            return ;
                        }

                        String code = response.getCode();
                        if (TextUtils.isEmpty(code ) || !"1".equals(code)){
                            ToastUtil.showLongToast(response.getInfo());

                        }else{
                            PageNoticeBean pageNoticeBean= response.getObj();
                            if(pageNoticeBean != null){
                                ArrayList<NoticeBean> noticeList = pageNoticeBean.getpNotice();
                                if(noticeList != null && noticeList.size() > 0){
                                    int totalPageCount = Integer.valueOf(pageNoticeBean.getTotal());
                                    getView().setNotices(noticeList,totalPageCount);
                                }else{
//                                    getView().setNotices(null);
                                    getView().setNoNotices();
                                }
                            }
                        }


                    }
                });
    }
}
