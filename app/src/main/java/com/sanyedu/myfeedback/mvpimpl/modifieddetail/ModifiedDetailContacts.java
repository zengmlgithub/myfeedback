package com.sanyedu.myfeedback.mvpimpl.modifieddetail;

import com.sanyedu.myfeedback.model.DetailBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

public final class ModifiedDetailContacts {
    public interface IModifiedDetailUI extends IBaseView{

        void setDetail(DetailBean bean);
        void getDetailFailure(String msg);
        void getDetailSuccess();
        void modifySuccess();
    }

    public interface IModifiedDetailPresenter extends IBasePresenter{
        void getDetail(String id);
        void closeFeedback(String id,String feedbackContent,String feedbackPerid,String feedbackPername,String feedbackPerdept);
    }
}
