package com.sanyedu.myfeedback.mvpimpl.noticedetail;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.model.NoticeDetailBean;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;

import okhttp3.Call;

public class NoticeDetailPresenter extends BasePresenter<NoticeDetailContacts.INoticeDetailUI> implements NoticeDetailContacts.INoticeDetaiPresenter {
    public NoticeDetailPresenter(@NonNull NoticeDetailContacts.INoticeDetailUI view) {
        super(view);
    }

    @Override
    public void getNoticeDetail(@NonNull String id) {
        String url = HttpUtil.getPort(HttpUtil.NOTICE_DETAIL_PORT);

        OkHttpUtils
                .get()
                .url(url)
                .addParams(HttpUtil.NoticeDetail.ID, id)
                .build()
                .execute(new BaseModelCallback<NoticeDetailBean>(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SanyLogs.e(e.toString());
                    }

                    @Override
                    public void onResponse(BaseModel<NoticeDetailBean> response, int id) {
                        SanyLogs.i(response.toString());

                        if(response == null){
                            ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                            return ;
                        }

                        String code = response.getCode();
                        if (TextUtils.isEmpty(code ) || !"1".equals(code)){
                            ToastUtil.showLongToast(response.getInfo());
                        }else{
                            NoticeDetailBean detailBean= response.getObj();
                            getView().setNoticeDetail(detailBean);
                        }
                    }
                });
    }
}
