package com.sanyedu.feedback.mvpimpl.modifieddetail;

import com.sanyedu.feedback.model.DetailBean;
import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

public final class ModifiedDetailContacts {
    public interface IModifiedDetailUI extends IBaseView{
        public void setDetail(DetailBean bean);
    }

    public interface IModifiedDetailPresenter extends IBasePresenter{
        public void getDetail(String id);
    }
}
