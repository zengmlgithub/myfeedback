package com.sanyedu.myfeedback.mvpimpl.modifychange;

import com.sanyedu.myfeedback.model.ChangeFeedbackBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.List;

public final class ModifyChangeContacts {
    public interface IModifyChangeUI extends IBaseView{
        int UPDATE＿SUCCESS = 0;
        int UPDATE＿FAILURE = 1;
        void updateFeedbackFailure(String msg);
        void updateFeedbackSuccess();
    }

    public interface IModifyChangePresenter extends IBasePresenter{
         void updateFeedback(List<String> files, ChangeFeedbackBean changeFeedbackBean);
    }


}
