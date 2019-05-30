package com.sanyedu.feedback.mvpimpl.test;

import android.support.annotation.NonNull;

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
import com.sanyedu.feedback.utils.HttpUtil;
import com.sanyedu.feedback.utils.MD5Utils;

import java.util.List;

import okhttp3.Call;

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

//                    @Override
//                    public void onResponse(TokenModel tokenModel, int id)
//                    {
//
//                    }
                });

    }

    @Override
    public void getUser(String userName, String password) {
//        String url= HttpUtil.getPort(HttpUtil.AUTO_PORT);
//        String autoStr = "Bearer " +  SpHelper.getString(ConstantUtil.TOKEN);
//        SanyLogs.i("get autoStr" + autoStr);
//        OkHttpUtils
//                .get()
//                .addHeader("Authorization",autoStr)
//                .url(url)
//                .addParams("userName", "admin")
//                .addParams("password",MD5Utils.getMD5("123456"))
//                .build()
//                .execute(new GenericsCallback<BaseModel<List<Names>>>(new JsonGenericsSerializator())
//                {
//                    @Override
//                    public void onError(Call call, Exception e, int id)
//                    {
//                        SanyLogs.e("testUrl:" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(BaseModel<List<Names>> response, int id) {
//                        List<response.getObj();
//                        getView().setUserInfo(names);
//                    }
//                });
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
