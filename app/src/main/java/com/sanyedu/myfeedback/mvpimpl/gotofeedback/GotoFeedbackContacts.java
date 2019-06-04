package com.sanyedu.myfeedback.mvpimpl.gotofeedback;

import com.sanyedu.myfeedback.model.DepartBean;
import com.sanyedu.myfeedback.model.PersonBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.io.File;
import java.util.List;

public final class GotoFeedbackContacts {
    public interface IGotoFeedbackUI extends IBaseView{
        void setDepartList(List<DepartBean> departList);
        void setPersonList(List<PersonBean> personList);
    }

    public interface IGoToFeedbackPresenter extends IBasePresenter{
        void getDepart();
        void getPersonOfDepart(String departId);
        void postFeedbackToServer(String feedbackTitle,String feedbackAdress,String feedbackContent,String feedbackDept,String feedbackPersonid,String feedbackPersonname,String feedbackA,String feedbackB,String feedbackC,String toResponsiblename,String toResponsibledept);
        void postFiles(List<String> files);
     }
}
