package com.sanyedu.myfeedback.mvpimpl.modifieddetail;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.model.DetailBean;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;

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
