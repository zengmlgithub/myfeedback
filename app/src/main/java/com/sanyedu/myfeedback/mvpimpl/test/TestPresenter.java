package com.sanyedu.myfeedback.mvpimpl.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.JsonGenericsSerializator;
import com.sanyedu.myfeedback.model.TokenModel;

import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.okhttp.callback.GenericsCallback;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.FileUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.Call;

import static com.sanyedu.myfeedback.utils.ConstantUtil.IMAGE_FILE_NAME;

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


    private  void saveToken(String tokenString) {
        SanyLogs.i("save token:" + tokenString);
        if (tokenString == null || "".equals(tokenString)) {
            return;
        } else {
            SpHelper.putString(ConstantUtil.TOKEN,tokenString);
        }
    }
}
