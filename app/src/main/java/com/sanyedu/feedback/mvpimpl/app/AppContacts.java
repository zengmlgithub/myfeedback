package com.sanyedu.feedback.mvpimpl.app;

import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

public final class AppContacts {
    public interface IAppUI extends IBaseView{
        //TODO:app user
    }

    public interface IAppPresenter extends IBasePresenter{
        //TODO:get token
        public void getToken();
    }
}
