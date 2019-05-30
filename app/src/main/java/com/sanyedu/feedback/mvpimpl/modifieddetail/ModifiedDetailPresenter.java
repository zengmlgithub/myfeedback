package com.sanyedu.feedback.mvpimpl.modifieddetail;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.model.BaseModel;
import com.sanyedu.feedback.model.BaseModelCallback;
import com.sanyedu.feedback.model.DetailBean;
import com.sanyedu.feedback.model.PageRecordBean;
import com.sanyedu.feedback.model.Records;
import com.sanyedu.feedback.mvp.BasePresenter;
import com.sanyedu.feedback.okhttp.OkHttpUtils;
import com.sanyedu.feedback.utils.ErrorUtils;
import com.sanyedu.feedback.utils.HttpUtil;
import com.sanyedu.feedback.utils.ToastUtil;

import java.util.List;

import okhttp3.Call;

public class ModifiedDetailPresenter extends BasePresenter<ModifiedDetailContacts.IModifiedDetailUI> implements ModifiedDetailContacts.IModifiedDetailPresenter {
    public ModifiedDetailPresenter(@NonNull ModifiedDetailContacts.IModifiedDetailUI view) {
        super(view);
    }

    @Override
    public void getDetail(String id) {
        String url = HttpUtil.getPort(HttpUtil.MODIFIED_DETAIL_PORT);
        OkHttpUtils
                .post()
                .url(url)
                .addParams(HttpUtil.MoDifiedDetail.ID, id)
                .build()
                .execute(
                        new BaseModelCallback<DetailBean>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<DetailBean> response, int id) {
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

                                DetailBean detailBean = response.getObj();
                                if (detailBean != null){
                                    getView().setDetail(detailBean);
                                }else{
                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                }
                            }
                        }
                );
    }
}
