package com.sanyedu.myfeedback.mvpimpl.update;

import android.content.Context;
import com.sanyedu.myfeedback.model.VersionInfoBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.io.File;

public final class UpdateContacts {
    public interface IUpdateView extends IBaseView{
        void compareVersionAndShowDialog(VersionInfoBean versionInfoBean);
        void installApk(File file);

        void loading(long total,long current,boolean isloading);
    }

    public interface IUpdatePresenter extends IBasePresenter{
        void getVersionInfo(Context context);
        void downLoadApk(String apkUrl);
    }
}
