package com.sanyedu.feedback.mvpimpl.needmodified;

import com.sanyedu.feedback.model.Records;
import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

import java.util.List;

public final class NeedModifiedContacts {
    public interface INeedModifiedUI extends IBaseView{
        public void setRecords(List<Records> recordsList);
    }

    public interface INeedModifiedPresenter extends IBasePresenter{
        public void getRecords(String startPage, String everyPage, String type);
    }
}
