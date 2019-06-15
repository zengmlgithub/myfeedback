package com.sanyedu.myfeedback.mvpimpl.needmodified;

import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.List;

public final class NeedModifiedContacts {
    public interface INeedModifiedUI extends IBaseView{
         void setRecords(List<Records> recordsList,int maxCount);
         void showNoMoreList();
         void showError(String msg);
    }

    public interface INeedModifiedPresenter extends IBasePresenter{
         void getRecords(String startPage, String everyPage, String type);
    }
}
