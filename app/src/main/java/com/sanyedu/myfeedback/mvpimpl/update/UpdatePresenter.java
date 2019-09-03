package com.sanyedu.myfeedback.mvpimpl.update;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.sanyedu.myfeedback.app.FeedbackApplication;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.*;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.DownloadUtil;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.SystemUtils;
import okhttp3.Call;

import java.io.File;
import java.util.List;

public class UpdatePresenter extends BasePresenter<UpdateContacts.IUpdateView> implements UpdateContacts.IUpdatePresenter {

    public UpdatePresenter(@NonNull UpdateContacts.IUpdateView view) {
        super(view);
    }

    @Override
    public void getVersionInfo(Context context) {
        //TODO：从网络上获取app信息
        String url = HttpUtil.getPort(HttpUtil.GET_APP_VERSION_PORT);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<VersionInfoBean>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
//                                getView().showError(ErrorUtils.PARSE_ERROR);
                            }

                            @Override
                            public void onResponse(BaseModel<VersionInfoBean> response, int id) {
                                if (response == null) {
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
//                                    getView().showError(ErrorUtils.PARSE_ERROR);
                                    return;
                                }
//                                SanyLogs.i(response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
//                                    getView().showError(ErrorUtils.PARSE_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
//                                    ToastUtil.showLongToast(response.getInfo());
//                                    getView().showError(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                VersionInfoBean bean = response.getObj();
                                SanyLogs.i("bean:" + bean.toString());
                                if (bean != null) {
                                    getView().compareVersionAndShowDialog(bean);
                                } else {

                                }
                            }
                        }
                );
    }

    @Override
    public void downLoadApk(String apkUrl) {
        //TODO:从服务器上下载apk
        SanyLogs.i("downLoadApk--->start--->down:" + apkUrl);
        if (TextUtils.isEmpty(apkUrl)) {
            SanyLogs.i("apkUrl is null");
            return;
        }

//        File file = new File(Environment.getExternalStorageDirectory() + "/myfeedback_" + SystemUtils.getCurrentDate() + ".apk");
//        if (file.exists()) {
//            file.delete();
//        }

        DownloadUtil.get().download(apkUrl, Environment.getExternalStorageDirectory().getAbsolutePath(), "/myfeedback_" + SystemUtils.getCurrentDate() + ".apk",
                new DownloadUtil.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess(File file) {
//                        if (progressDialog != null && progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                        }
//                        //下载完成进行相关逻辑操作
//                        Message msg = mHandler.obtainMessage();
//                        msg.what = 0;
//                        mHandler.sendMessage(msg);
                        SanyLogs.i("onDownloadSuccess!!!!");
                          //TODO:install apk
//                        getView().installApk(file);
                        installApk(file);
                    }

                    @Override
                    public void onDownloading(int progress) {
//                        progressDialog.setProgress(progress);
//                        Message msg = mHandler.obtainMessage();
//                        msg.what = 11;
//                        msg.obj = progress;
//                        mHandler.sendMessage(msg);
                        SanyLogs.i("progress-->" + progress);
                    }

                    @Override
                    public void onDownloadFailed(Exception e) {
//                        //下载异常进行相关提示操作
////                        Message msg = mHandler.obtainMessage();
////                        msg.what = 1;
////                        msg.obj = e;
////                        mHandler.sendMessage(msg);
                        SanyLogs.e(e.toString());
                    }
                });
    }

    private void installApk(File file) {
        if(file.exists()) {
            SanyLogs.i("file  exist,start install");
            Intent intent = new Intent();
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            Uri uri = Uri.fromFile(file);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            FeedbackApplication.getApp().startActivity(intent);
        }else{
            SanyLogs.i("file not exist!");
        }
    }
}
