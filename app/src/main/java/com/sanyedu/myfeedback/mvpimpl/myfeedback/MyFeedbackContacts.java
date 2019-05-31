package com.sanyedu.myfeedback.mvpimpl.myfeedback;

import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.List;

public final class MyFeedbackContacts {
    public interface IMyFeedbackUI extends IBaseView{
        public void setMyFeedbacks(List<Records> recordsList);
    }

    public interface IMyFeedbackPresenter extends IBasePresenter{
        public void getMyFeedbacks(String startPage, String everyPage, String id, String type);
    }
}
