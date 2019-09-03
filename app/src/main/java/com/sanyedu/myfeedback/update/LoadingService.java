package com.sanyedu.myfeedback.update;

import android.Manifest;
import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.app.FeedbackApplication;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.VersionInfoBean;
import com.sanyedu.myfeedback.mvp.BaseActivity;
import com.sanyedu.myfeedback.mvpimpl.update.UpdateContacts;
import com.sanyedu.myfeedback.mvpimpl.update.UpdatePresenter;

import java.io.File;

public class LoadingService extends IntentService implements UpdateContacts.IUpdateView {
//    private HttpUtils httpUtils;
    NotificationManager nm;
    private String url, path;
    private SharedPreferences sharedPreferences;
    private UpdatePresenter presenter;

    public LoadingService(String name) {
        super(name);
    }

    public LoadingService() {
        super("MyService");
    }

    public static void startUploadImg(Context context) {
        SanyLogs.i("startUploadImg---->");


    }


    public void onCreate() {
        super.onCreate();
//        httpUtils = new HttpUtils();
//        httpUtils.configCurrentHttpCacheExpiry(0);
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        presenter = new UpdatePresenter(this);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent == null){
            return;
        }
        SanyLogs.i("intent--->" + intent);

        url = intent.getStringExtra("APK_ADDRESS");
        //创建一个路径

        updateApk(url);
    }

    //开始下载apk  网络请求使用的是xutils框架
    private void updateApk(String url) {
        presenter.downLoadApk(url);
        //TODO:下载apk
//        httpUtils.download(url,
//                path, new RequestCallBack<File>() {
//                    @Override
//                    public void onLoading(final long total, final long current,
//                                          boolean isUploading) {
//                        createNotification(total, current);
//                        sendBroadcast(new Intent().setAction("android.intent.action.loading"));//发送正在下载的广播
//                        super.onLoading(total, current, isUploading);
//                    }
//
//                    @Override
//                    public void onSuccess(ResponseInfo<File> arg0) {
//                        nm.cancel(R.layout.notification_item);
//                        Toast.makeText(LoadingService.this, "下载成功...", Toast.LENGTH_SHORT).show();
//                        installApk();//下载成功 打开安装界面
//                        stopSelf();//结束服务
//                        sendBroadcast(new Intent().setAction("android.intent.action.loading_over"));//发送下载结束的广播
//                    }
//
//                    @Override
//                    public void onFailure(HttpException arg0, String arg1) {
//                        Toast.makeText(LoadingService.this, "下载失败...", Toast.LENGTH_SHORT).show();
//                        sendBroadcast(new Intent().setAction("android.intent.action.loading_over"));//发送下载结束的广播
//                        nm.cancel(R.layout.notification_item);
//                        stopSelf();
//                    }
//                });
    }

    @Override
    public void compareVersionAndShowDialog(VersionInfoBean versionInfoBean) {
        //此处无需实现,实现的内容在UpdateService
    }

    /**
     * 安装下载的新版本
     */
    @Override
    public void installApk(File file) {
//        if(file.exists()) {
//            SanyLogs.i("file  exist,start install");
//            Intent intent = new Intent();
//            intent.addCategory(Intent.CATEGORY_DEFAULT);
//            Uri uri = Uri.fromFile(file);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setDataAndType(uri, "application/vnd.android.package-archive");
//            this.startActivity(intent);
//        }else{
//            SanyLogs.i("file not exist!");
//        }

        if(Build.VERSION.SDK_INT>=24) {//判读版本是否在7.0以上
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 启动新的activity
            // 设置Uri和类型
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            intent.setDataAndType(FileProvider7.getUriForFile(getBaseContext(), file), "application/vnd.android.package-archive");
            startActivity(intent);
        } else{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 启动新的activity
            // 设置Uri和类型
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            startActivity(intent);
        }
    }

    @Override
    public void loading(long total, long current,boolean isLoading) {
        //
    }

    //发送通知 实时更新通知栏下载进度
    private void createNotification(final long total, final long current) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);//必须要设置这个属性，否则不显示
        RemoteViews contentView = new RemoteViews(this.getPackageName(), R.layout.progress_loading);
        contentView.setProgressBar(R.id.download_pb, (int) total, (int) current, false);
        builder.setOngoing(true);//设置左右滑动不能删除
        Notification notification = builder.build();
        notification.contentView = contentView;
        nm.notify(R.layout.progress_loading, notification);//发送通知
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public <T extends Activity> T getSelfActivity() {
        return null;
    }
}