package com.sanyedu.myfeedback.mvpimpl.needmodified;

import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.List;

public final class NeedModifiedContacts {
    public interface INeedModifiedUI extends IBaseView{
        public void setRecords(List<Records> recordsList);
    }

    public interface INeedModifiedPresenter extends IBasePresenter{
        public void getRecords(String startPage, String everyPage, String type);
    }
}
