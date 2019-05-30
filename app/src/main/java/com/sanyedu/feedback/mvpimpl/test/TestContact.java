package com.sanyedu.feedback.mvpimpl.test;

import com.sanyedu.feedback.model.Names;
import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

import java.util.List;

public final class TestContact {
    //view接口
    public interface ITestUI extends IBaseView {
       void setUserInfo(List<Names> nameList);
    }

    //presenter
    public interface ITestPresenter extends IBasePresenter {
        void getToken();
        void getUser(String userName, String password);
    }

    /**
     * model 层接口
     */
    public interface IMainMdl {

    }
}
