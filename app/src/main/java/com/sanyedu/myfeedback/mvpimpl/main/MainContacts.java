package com.sanyedu.myfeedback.mvpimpl.main;

import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

public final class MainContacts {

    //view接口
    public interface IMainUI extends IBaseView {
        void setImage(String url, int width, int height);
    }

    //presenter
    public interface IMainPresenter extends IBasePresenter {
        void getImageParam();
    }

    /**
     * model 层接口
     */
    public interface IMainMdl {

    }
}
