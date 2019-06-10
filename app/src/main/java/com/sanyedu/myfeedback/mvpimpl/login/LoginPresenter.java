package com.sanyedu.myfeedback.mvpimpl.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.model.JsonGenericsSerializator;
import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.model.TokenModel;
import com.sanyedu.myfeedback.model.UserInfo;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.okhttp.callback.GenericsCallback;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.MD5Utils;

import java.util.List;

import okhttp3.Call;

public class LoginPresenter extends BasePresenter<LoginContacts.ILoginUI> implements LoginContacts.ILoginPresenter {

    public LoginPresenter(@NonNull LoginContacts.ILoginUI view) {
        super(view);
    }


    @Override
    public void getToken(final String userName, final String password, final String regFlag) {
        String url = HttpUtil.getPort(HttpUtil.AUTH_PORT);
//        String tokenValue = "Bearer " + SpHelper.getString(ConstantUtil.TOKEN);
        OkHttpUtils
                .post()
                .url(url)
//                .addHeader(ConstantUtil.AUTHORIZATION,tokenValue)
                .addParams("userName", userName)
                .addParams("password", MD5Utils.getMD5(password))
                .addParams("loginFlag", regFlag)
                .build()
                .execute(new GenericsCallback<TokenModel>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SanyLogs.e("testUrl:" + e.getMessage());
                        getView().loginFailure("登录失败");
                    }

                    @Override
                    public void onResponse(TokenModel tokenModel, int id) {
                        SanyLogs.i("getToken:" + tokenModel.toString());
                        if (tokenModel != null) {
                            String code = tokenModel.getCode();
                            if (!TextUtils.isEmpty(code)) {
                                if (HttpUtil.SUCCESS.equals(code)) {
                                    SpHelper.putString(ConstantUtil.TOKEN, tokenModel.getToken());
                                    getLogin(userName, password, regFlag);
                                } else if (HttpUtil.ERROR_ACCOUNT.equals(code)) {
                                    //TODO:error
                                    SanyLogs.i("getToken:error");
                                    getView().loginFailure(tokenModel.getToken());
                                }
                            }
                        }

                    }
                });
    }

    @Override
    public void getLogin(String userName, String password, String regFlag) {
        String url = HttpUtil.getPort(HttpUtil.LOGIN_PORT);
        String tokenValue = "Bearer " + SpHelper.getString(ConstantUtil.TOKEN);
        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
                .addHeader(ConstantUtil.AUTHORIZATION, tokenValue)
                .addParams("userName", userName)
                .addParams("password", MD5Utils.getMD5(password))
                .addParams("loginFlag", regFlag)
                .build()
                .execute(
                        new BaseModelCallback<List<TeacherBean>>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                getView().loginFailure(HttpUtil.ERROR_SERVER);
                            }

                            @Override
                            public void onResponse(BaseModel<List<TeacherBean>> response, int id) {
                                SanyLogs.i("userInfo:" + response.getObj().get(0));
                                try {
                                    TeacherBean userInfo = response.getObj().get(0);
                                    SpHelper.putObj(ConstantUtil.USERINFO,userInfo);
                                    getView().startMain();
                                }catch (Exception e){
                                    SanyLogs.i(e.toString());
                                    getView().loginFailure(HttpUtil.ERROR_SERVER);
                                }

                            }
                        }
                );
    }

    private void saveUser(UserInfo userInfo) {
        //TODO:saveUser
    }


}
