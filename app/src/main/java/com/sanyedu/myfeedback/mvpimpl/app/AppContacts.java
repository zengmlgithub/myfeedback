package com.sanyedu.myfeedback.mvpimpl.app;

import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

public final class AppContacts {
    public interface IAppUI extends IBaseView{

    }

    public interface IAppPresenter extends IBasePresenter{
        void getToken();
    }
}
