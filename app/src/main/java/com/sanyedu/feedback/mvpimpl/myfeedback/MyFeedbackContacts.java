package com.sanyedu.feedback.mvpimpl.myfeedback;

import com.sanyedu.feedback.model.Records;
import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

import java.util.List;

public final class MyFeedbackContacts {
    public interface IMyFeedbackUI extends IBaseView{
        public void setMyFeedbacks(List<Records> recordsList);
    }

    public interface IMyFeedbackPresenter extends IBasePresenter{
        public void getMyFeedbacks(String startPage, String everyPage, String id, String type);
    }
}
