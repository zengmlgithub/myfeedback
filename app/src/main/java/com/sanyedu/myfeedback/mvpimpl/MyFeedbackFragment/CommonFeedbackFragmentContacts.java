package com.sanyedu.myfeedback.mvpimpl.MyFeedbackFragment;

import android.support.annotation.NonNull;
import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.List;

public final class CommonFeedbackFragmentContacts {
    public interface ICommonFeedbackFragmentUI extends IBaseView{
        void setFeebacks(List<Records> recordsList,int maxCount);
        void showNoMoreList();
        void showError(@NonNull String serverErrorMsg);
    }

    public interface ICommonFeedbacFragmentPresenter extends IBasePresenter{
         void getFeedbacks(@NonNull String startPage,@NonNull String everyPage,@NonNull String id,@NonNull String type);
    }
}
