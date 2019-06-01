package com.sanyedu.myfeedback.mvpimpl.gotofeedback;

import com.sanyedu.myfeedback.model.DepartBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.List;

public final class GotoFeedbackContacts {
    public interface IGotoFeedbackUI extends IBaseView{
        void setDepartList(List<DepartBean> departList);
        void setperson(List<String> personList);
    }

    public interface IGoToFeedbackPresenter extends IBasePresenter{
        void getDepart();
        void getPersonOfDepart(String departId);
    }
}
