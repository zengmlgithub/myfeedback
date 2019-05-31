package com.sanyedu.myfeedback.mvpimpl.modifieddetail;

import com.sanyedu.myfeedback.model.DetailBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

public final class ModifiedDetailContacts {
    public interface IModifiedDetailUI extends IBaseView{
        public void setDetail(DetailBean bean);
    }

    public interface IModifiedDetailPresenter extends IBasePresenter{
        public void getDetail(String id);
    }
}
