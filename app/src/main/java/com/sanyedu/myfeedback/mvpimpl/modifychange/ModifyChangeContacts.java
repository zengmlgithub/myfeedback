package com.sanyedu.myfeedback.mvpimpl.modifychange;

import com.sanyedu.myfeedback.model.ChangeFeedbackBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.List;

public final class ModifyChangeContacts {
    public interface IModifyChangeUI extends IBaseView{
        int UPDATE＿SUCCESS = 0;
        int UPDATE＿FAILURE = 1;
        void updateFeedbackResult(int result); //result 只能在update_success 和  update_failure中取值
    }

    public interface IModifyChangePresenter extends IBasePresenter{
         void updateFeedback(List<String> files, ChangeFeedbackBean changeFeedbackBean);
        void updateFeedback(ChangeFeedbackBean changeFeedbackBean);
    }


}
