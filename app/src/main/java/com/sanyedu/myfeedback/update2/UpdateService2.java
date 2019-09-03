package com.sanyedu.myfeedback.update2;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import com.google.gson.Gson;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.SystemUtils;
import com.sanyedu.myfeedback.utils.ToastUtil;
import com.sanyedu.myfeedback.utils.TypeUtils;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UpdateService2 {
    private Activity context;
    private String mUpdateUrl;

    public UpdateService2(Activity context){
       this.context = context;
       this.mUpdateUrl = HttpUtil.getPort(HttpUtil.GET_APP_VERSION_PORT);
   }

    /**
     * 检查是否有新的版本，如果有，进行自动升级
     */
   public void checkNewVersion(){
       String path = Environment.getExternalStorageDirectory().getAbsolutePath();
       Map<String, String> params = new HashMap<>();
       params.put("id","1");
       new UpdateAppManager
               .Builder()
               //必须设置，当前Activity
               .setActivity(context)
               //必须设置，实现httpManager接口的对象
               .setHttpManager(new OkGoUpdateHttpUtil())
               //必须设置，更新地址
               .setUpdateUrl(mUpdateUrl)

               //以下设置，都是可选
               //设置请求方式，默认get
               .setPost(false)
               //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
               .setParams(params)
               //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
               //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
//               .setTopPic(R.mipmap.ic_launcher)
               //为按钮，进度条设置颜色，默认从顶部图片自动识别。
               //.setThemeColor(ColorUtil.getRandomColor())
               //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
               .setTargetPath(path)
               //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
               //.setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
               //不显示通知栏进度条
//               .dismissNotificationProgress()
               //是否忽略版本
               //.showIgnoreVersion()

               .build()
               //检测是否有新版本
               .checkNewApp(new UpdateCallback() {
                   /**
                    * 解析json,自定义协议
                    *
                    * @param json 服务器返回的json
                    * @return UpdateAppBean
                    */
                   @Override
                   protected UpdateAppBean parseJson(String json) {
                       SanyLogs.i("json:" + json);
                       UpdateAppBean updateAppBean = new UpdateAppBean();
                       try {
                           JSONObject jsonObject = new JSONObject(json);
                           JSONObject jsonData = jsonObject.getJSONObject("obj");
                           String updateResult = getUpdateResult(jsonData);
                           String downUrl = getUpdateUrl(jsonData);
                           updateAppBean
                                   //（必须）是否更新Yes,No
                                   .setUpdate(updateResult)
                                   //（必须）新版本号，
                                   .setNewVersion(jsonData.optString("versionName"))
                                   //（必须）下载地址
                                   .setApkFileUrl(downUrl)
                                   //（必须）更新内容
                                   .setUpdateLog(jsonData.optString("versionMsg"))
                                   //大小，不设置不显示大小，可以不设置
//                                   .setTargetSize(jsonObject.optString("target_size"))
                                   //是否强制更新，可以不设置
                                   .setConstraint(true);
                                   //设置md5，可以不设置
//                                   .setNewMd5(jsonObject.optString("new_md51"));
                           SanyLogs.i("addressDow:" + updateAppBean.getApkFileUrl());
                       } catch (JSONException e) {
                           e.printStackTrace();
                       } catch (PackageManager.NameNotFoundException e) {
                           e.printStackTrace();
                       }
                       return updateAppBean;
                   }

                   /**
                    * 网络请求之前
                    */
                   @Override
                   public void onBefore() {
                       CProgressDialogUtils.showProgressDialog(context);
                   }

                   /**
                    * 网路请求之后
                    */
                   @Override
                   public void onAfter() {
                       CProgressDialogUtils.cancelProgressDialog(context);
                   }

                   /**
                    * 没有新版本
                    */
                   @Override
                   public void noNewApp(String error) {
                       ToastUtil.showLongToast("没有新版本");
                   }
               });
   }

    private String getUpdateUrl(JSONObject jsonData) {
       String id = jsonData.optString("id");
       if("1".equals(id)){
           return jsonData.optString("teacherUrl");
       }else if("2".equals(id)){
           return jsonData.optString("studentUrl");
       }else{
           return "";
       }
    }

    private String getUpdateResult(JSONObject obj) throws PackageManager.NameNotFoundException {
       String updateResult = "No";
       String versionCodeString = obj.optString("versionNum");
       SanyLogs.i("server version code:" + versionCodeString);
       long versionCodeLong = TypeUtils.str2Long(versionCodeString);
       long curCodeLong = SystemUtils.getVersionCode(context);
       if(curCodeLong < versionCodeLong){
           updateResult = "Yes";
       }
       SanyLogs.i("update result:" + updateResult);
       return updateResult;
   }
}
