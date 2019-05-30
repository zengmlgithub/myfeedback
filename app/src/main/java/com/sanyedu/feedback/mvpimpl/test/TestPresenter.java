package com.sanyedu.feedback.mvpimpl.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import android.util.Log;
import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.model.BaseModel;
import com.sanyedu.feedback.model.JsonGenericsSerializator;
import com.sanyedu.feedback.model.Names;
import com.sanyedu.feedback.model.TokenModel;

import com.sanyedu.feedback.mvp.BasePresenter;
import com.sanyedu.feedback.okhttp.OkHttpUtils;
import com.sanyedu.feedback.okhttp.callback.GenericsCallback;
import com.sanyedu.feedback.share.SpHelper;
import com.sanyedu.feedback.utils.ConstantUtil;
import com.sanyedu.feedback.utils.FileUtils;
import com.sanyedu.feedback.utils.HttpUtil;
import com.sanyedu.feedback.utils.MD5Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;

import static com.sanyedu.feedback.utils.ConstantUtil.IMAGE_FILE_NAME;

public class TestPresenter extends BasePresenter<TestContact.ITestUI> implements TestContact.ITestPresenter {

    public TestPresenter(@NonNull TestContact.ITestUI view) {
        super(view);
    }

    @Override
    public void getToken() {
        String testUrl = HttpUtil.getPort(HttpUtil.TEST_PORT);
        SanyLogs.i("testUrl:" + testUrl);

        OkHttpUtils
                .get()
                .url(testUrl)
                .addParams("userName", "admin")
                .addParams("password", "admin")
                .build()
                .execute(new GenericsCallback (new JsonGenericsSerializator())
                {
                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        SanyLogs.e("testUrl:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        SanyLogs.i("getToken:" + response.toString());
                        if( response instanceof TokenModel){
                            saveToken(((TokenModel)response).getToken());
                        }

                    }
                });
    }

    @Override
    public void getUser(String userName, String password) {

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

    private  void saveToken(String tokenString) {
        SanyLogs.i("save token:" + tokenString);
        if (tokenString == null || "".equals(tokenString)) {
            return;
        } else {
            SpHelper.putString(ConstantUtil.TOKEN,tokenString);
        }
    }



}
