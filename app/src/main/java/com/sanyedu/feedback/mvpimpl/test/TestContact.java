package com.sanyedu.feedback.mvpimpl.test;

import android.net.Uri;
import com.sanyedu.feedback.model.Names;
import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

import java.io.InputStream;
import java.util.List;

public final class TestContact {
    //view接口
    public interface ITestUI extends IBaseView {
       void setUserInfo(List<Names> nameList);
       void showHeadImage();
       void startActivityWithResult(Uri uri,Uri cropUri);
       InputStream openInputStream(Uri uri);
    }

    //presenter
    public interface ITestPresenter extends IBasePresenter {
        void getToken();
        void getUser(String userName, String password);
        void setPicToView(Uri uri);
        void startPhotoZoom(Uri uri);
    }

    /**
     * model 层接口
     */
    public interface IMainMdl {

    }
}
