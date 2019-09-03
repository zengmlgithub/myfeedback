package com.sanyedu.myfeedback.update;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.app.FeedbackApplication;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.VersionInfoBean;
import com.sanyedu.myfeedback.mvpimpl.update.UpdateContacts;
import com.sanyedu.myfeedback.mvpimpl.update.UpdatePresenter;
import com.sanyedu.myfeedback.utils.SystemUtils;
import com.sanyedu.myfeedback.utils.TypeUtils;

import java.io.File;

public class UpdateService implements UpdateContacts.IUpdateView {

    private Context context;
//    private VersionInfoBean versionInfoBean;
    private UpdateContacts.IUpdatePresenter presenter;

    public UpdateService(Context context){
        this.context = context;
        presenter = new UpdatePresenter(this);
    }
    /**
     * 版本号比较
     *0代表相等，1代表serverVersion 大于 appVrsion，2 代表appVersion大于serverVersion
     * @param appVersion   当前应用的版本
     * @param serverVersion 服务器上的版本
     * @return   1 表示要升级  -1 表示不要升级
     */
    private  int compareVersion(long appVersion, long serverVersion) {

       int result = 0;
       if(serverVersion > appVersion){
           result = 1;
       }else if (serverVersion == appVersion){
           result = 0;
       }else if(appVersion < appVersion){
           result = -1;
       }
       SanyLogs.i("result--->" + result);
       return result;
    }

    /**
     * 从服务器获取版本最新的版本信息
     */
    public void getVersionInfoFromServer(){
       presenter.getVersionInfo(context);
    }

    private  void showDialog(Context context, final VersionInfoBean bean){
        if(context == null || bean == null){
            SanyLogs.e("param error,return!");
            return;
        }
        final Dialog dialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView version,content;
        Button left,right;
        View view = inflater.inflate(R.layout.layout_dialog_version_update,null,false);
        version = view.findViewById(R.id.version_name_tv);
        content = view.findViewById(R.id.msg_tv);
        left = view.findViewById(R.id.cancel_btn);
        right = view.findViewById(R.id.install_btn);
        content.setText(bean.getMsg());

        content.setMovementMethod(LinkMovementMethod.getInstance());
        version.setText("版本号："+bean.getVersionNum());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:强制更新，不支持多版本apk,不更新直接退出
                dialog.dismiss();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                downloadNewVersionFromServer(bean);
            }
        });
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        //dialogWindow.setWindowAnimations(R.style.ActionSheetDialogAnimation);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        lp.width =wm.getDefaultDisplay().getWidth()/10*9;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    /**
     * 启动服务后台下载
     */
    private void downloadNewVersionFromServer(VersionInfoBean bean){
        if(bean == null){
            return;
        }
        String picPath = bean.getApkPic();
        File picFile = new File(picPath);
        if(picFile.exists()){
            picFile.delete();
        }
        Toast.makeText(context,"开始下载...",Toast.LENGTH_SHORT).show();
        startLoadingService(bean.getAddressDow());


//        LoadingService.startUploadImg(context);
    }

    private void startLoadingService(String addressDow) {
        if(addressDow == null){
            SanyLogs.e("down url is null ,return");
            return;
        }
        Intent intent = new Intent(context, LoadingService.class);
        intent.putExtra("APK_ADDRESS",addressDow);
        context.startService(intent);

    }

    @Override
    public void compareVersionAndShowDialog(VersionInfoBean versionInfoBean) {
        try {
            long version = TypeUtils.str2Int(versionInfoBean.getVersionNum());
            int result = compareVersion(SystemUtils.getVersionCode(FeedbackApplication.getApp()),version);
            if(result==1){//不是最新版本
                showDialog(context,versionInfoBean);
            }else{
                Toast.makeText(context,"已经是最新版本",Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void installApk(File file) {
        //此处无需实现,实现的代码在LoadingServicew
    }

    @Override
    public void loading(long total, long current, boolean isloading) {
        //这个在LoadingService中实现
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
