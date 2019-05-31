package com.sanyedu.myfeedback.mvpimpl.test;

import android.net.Uri;
import com.sanyedu.myfeedback.model.Names;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.io.InputStream;
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
