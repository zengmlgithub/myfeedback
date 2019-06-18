package com.sanyedu.myfeedback.mvpimpl.mainmy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import android.text.TextUtils;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.model.PageRecordBean;
import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.*;
import okhttp3.Call;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import static com.sanyedu.myfeedback.utils.ConstantUtil.IMAGE_FILE_NAME;

public class MainMyPresenter extends BasePresenter<MainMyContacts.IMainMyUI> implements MainMyContacts.IMainMyPresenter {
    public MainMyPresenter(@NonNull MainMyContacts.IMainMyUI view) {
        super(view);
    }

    @Override
    public void mkdir() {
        FileUtils.mkdirRootDirectory();
    }

    @Override
    public void setPicToView(Uri uri) {
        if (uri != null) {
            Bitmap photo = null;
            try {
                InputStream inputStream = getView().openInputStream(uri);
                photo = BitmapFactory.decodeStream(inputStream);
                // 创建 smallIcon 文件夹
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    //String storage = Environment.getExternalStorageDirectory().getPath();
                    File dirFile = new File(FileUtils.getMyPetRootDirectory(),  "Icon");
                    if (!dirFile.exists()) {
                        if (!dirFile.mkdirs()) {
                            SanyLogs.i("in setPicToView->文件夹创建失败");
                        } else {
                            SanyLogs.i("in setPicToView->文件夹创建成功");
                        }
                    }
                    File file = new File(dirFile, IMAGE_FILE_NAME);
                    //保存图片路径
                    SpHelper.putString(ConstantUtil.HEAD_IMAGE,file.getPath());
                    //Log.d("result",file.getPath());
                    // Log.d("result",file.getAbsolutePath());
                    // 保存图片
                    FileOutputStream outputStream = null;
                    try {
                        outputStream = new FileOutputStream(file);
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 在视图中显示图片
                getView().showHeadImage();
                //circleImageView_user_head.setImageBitmap(InfoPrefs.getData(Constants.UserInfo.GEAD_IMAGE));
            }catch (Exception e){
                SanyLogs.e(e.toString());
                return;
            }

        }
    }

    @Override
    public void startPhotoZoom(Uri uri) {
//        Log.d(TAG,"Uri = "+uri.toString());
        SanyLogs.i("Uri = "+uri.toString());
        //保存裁剪后的图片
        File cropFile=new File(FileUtils.getMyPetRootDirectory(),"crop.jpg");
        try{
            if(cropFile.exists()){
                cropFile.delete();
                SanyLogs.i("delete");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Uri cropUri;
        cropUri = Uri.fromFile(cropFile);
        getView().startActivityWithResult(uri,cropUri);
    }

    @Override
    public void getMyInfoNum(String id, final String infoType) {

        if(!CheckUtils.isParasLegality(id,infoType)){
            SanyLogs.e("params is null,return");
            return;
        }

        String url = HttpUtil.getPort(HttpUtil.GET_MY_FEEDBACK_COUNT_PORT);
//        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
//                .addHeader(ConstantUtil.AUTHORIZATION, tokenValue)
                .addParams(HttpUtil.MyFeedbackCount.ID, id)
                .addParams(HttpUtil.MyFeedbackCount.TYPE,infoType)
                .build()
                .execute(
                        new BaseModelCallback<Integer>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
//                                getView().showError(ErrorUtils.PARSE_ERROR);
                                goToFeedbackError(infoType);
                            }

                            @Override
                            public void onResponse(BaseModel<Integer> response, int id) {
                                if (response == null){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
//                                    getView().showError(ErrorUtils.PARSE_ERROR);
                                    goToFeedbackError(infoType);
                                    return;
                                }
//                                SanyLogs.i(response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    goToFeedbackError(infoType);
                                    return;
                                }

                                if (!"1".equals(code)){
//                                    ToastUtil.showLongToast(response.getInfo());
                                    goToFeedbackError(infoType);
                                    return;
                                }

                                int count = response.getObj();

                                goToFeedbacSuccess(infoType,count);
                            }
                        }
                );
    }

    private void goToFeedbackError(String infoType) {
        if(ConstantUtil.FEEDBACK_MAIN.equals(infoType)){
            getView().showFeedbackMyNumber(0);
        }else if(ConstantUtil.MAIN_FEEDBACK.equals(infoType)){
            getView().showMyFeedbackNumber(0);
        }
    }

    private void goToFeedbacSuccess(String infoType,int count){
        if(ConstantUtil.FEEDBACK_MAIN.equals(infoType)){
            getView().showFeedbackMyNumber(count);
        }else if(ConstantUtil.MAIN_FEEDBACK.equals(infoType)){
            getView().showMyFeedbackNumber(count);
        }
    }


    private  void saveToken(String tokenString) {
        SanyLogs.i("save token:" + tokenString);
        if (tokenString == null || "".equals(tokenString)) {
            return;
        } else {
            SpHelper.putString(ConstantUtil.TOKEN,tokenString);
        }
    }
}
