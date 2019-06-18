package com.sanyedu.myfeedback.mvpimpl.mainmy;

import android.net.Uri;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.io.InputStream;

public final class MainMyContacts {
    public interface IMainMyUI extends IBaseView {
        InputStream openInputStream(Uri uri);
        void showHeadImage();
        void startActivityWithResult(Uri uri,Uri cropUri);
        void showMyFeedbackNumber(int count);
        void showFeedbackMyNumber(int count);
    }

    public interface IMainMyPresenter extends IBasePresenter {
        void mkdir();

        void setPicToView(Uri uri);

        void startPhotoZoom(Uri uri);

        void getMyInfoNum(String id,String infoType);
    }
}
