package com.sanyedu.myfeedback.mvpimpl.modifieddetail;

import com.sanyedu.myfeedback.model.DetailBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

public final class ModifiedDetailContacts {
    public interface IModifiedDetailUI extends IBaseView{
        int CLOSE_SUCCESS = 1;
        int CLOSE_FAILURE = 0;
        public void setDetail(DetailBean bean);
        void closeFeedbackResult(int resultCode);
    }

    public interface IModifiedDetailPresenter extends IBasePresenter{
        void getDetail(String id);
        void closeFeedback(String id,String feedbackContent,String feedbackPerid,String feedbackPername,String feedbackPerdept);
    }
}
