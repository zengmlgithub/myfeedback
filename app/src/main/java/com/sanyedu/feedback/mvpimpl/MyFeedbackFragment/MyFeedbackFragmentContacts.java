package com.sanyedu.feedback.mvpimpl.MyFeedbackFragment;

import com.sanyedu.feedback.model.Records;
import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

import java.util.List;

public final class MyFeedbackFragmentContacts {
    public interface IMyFeedbackFragmentUI extends IBaseView{
        public void setFeebacks(List<Records> recordsList);
    }

    public interface IMyFeedbacFragmentPresenter extends IBasePresenter{
        public void getFeedbacks(String startPage,String everyPage,String id,String type);
    }
}
