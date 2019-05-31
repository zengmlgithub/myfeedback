package com.sanyedu.myfeedback.mvpimpl.MyFeedbackFragment;

import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.List;

public final class MyFeedbackFragmentContacts {
    public interface IMyFeedbackFragmentUI extends IBaseView{
        public void setFeebacks(List<Records> recordsList);
    }

    public interface IMyFeedbacFragmentPresenter extends IBasePresenter{
        public void getFeedbacks(String startPage,String everyPage,String id,String type);
    }
}
