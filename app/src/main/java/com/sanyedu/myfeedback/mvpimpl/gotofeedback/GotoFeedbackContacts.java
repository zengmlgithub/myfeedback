package com.sanyedu.myfeedback.mvpimpl.gotofeedback;

import com.sanyedu.myfeedback.model.DepartBean;
import com.sanyedu.myfeedback.model.FeedbackBean;
import com.sanyedu.myfeedback.model.FeedbackItem;
import com.sanyedu.myfeedback.model.PersonBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.io.File;
import java.util.List;

public final class GotoFeedbackContacts {
    public interface IGotoFeedbackUI extends IBaseView{
        void setDepartList(List<DepartBean> departList);
        void setPersonList(List<PersonBean> personList);

        FeedbackItem getCurrentItem();

        void updateFeedbackSuccess();
        void updateFeedbackFailure(String failureReson);

        //设置部门为空时的人员
        void setNullPersons();
    }

    public interface IGoToFeedbackPresenter extends IBasePresenter{
        void getDepart();
        void getPersonOfDepart(String departId);
        void postFeedbackToServer(FeedbackItem feedbackBean);
        void postFeedbackToServer(List<String> pathList,final FeedbackItem item);
     }
}
