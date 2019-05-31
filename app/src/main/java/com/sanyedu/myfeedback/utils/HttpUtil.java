package com.sanyedu.myfeedback.utils;

import android.text.TextUtils;

import com.sanyedu.myfeedback.log.SanyLogs;

/**
 * 网络相关的协议
 */
public class HttpUtil {

    public static final boolean IS_TEST = true;
    private static final boolean IS_HTTP = true;

    private static final String FINAL_SERVER = "";  //正式服务器地址
    private static final String TEST_SERVER = "172.16.9.10:8082";  //测试服务器地址

    public static final String HTTPS = "https://";
    public static final String HTTP = "http://";

    public static final String TEST_PORT = "testClass/testSel";

    public static final String AUTH_PORT = "auth";
    public static final String LOGIN_PORT = "loginUser/log";
    public static final String NOTICE_PORT = "info/sel_dicat";
    public static final String NOTICE_DETAIL_PORT = "info/selDis_info"; //单条通知详情
    public static final String TODYA_FEEDBACK_PORT = "academic/selInfo_day"; //今日反馈条数
    public static final String MODIFIED_DETAIL_PORT = "academic/selInfo_det"; //整改详情
    public static final String MY_FEEDBACK_PORT = "academic/myDectis";//我的反馈
    public static final String FEEDBACK_MY_PORT = "academic/selMyInfo";//反馈我的

    public static final String SUCCESS = "1";
    public static final String ERROR_ACCOUNT = "0"; //账号密码错误
    public static final String ERROR_SYSTEM_EXCEPTION = "400";//系统异常

    private static final String getServerHost() {
        String prot = IS_HTTP ? HTTP : HTTPS;
        String server = IS_TEST ? TEST_SERVER : FINAL_SERVER;
        String protServer = prot + server;
        SanyLogs.i("protServer:" + protServer);
        if(protServer == null ){
            return null;
        }else{
            if(protServer.endsWith("/")){
                return protServer;
            }else{
                return protServer + "/";
            }
        }
    }


//    public static final String getTestPort(){
//        String server = getServerHost();
//        String url = server + TEST_PORT;
//        return url;
//    }
//
//    public static final String getTokenPort(){
//        String server = getServerHost();
//        String url = server + AUTO_PORT;
//        return url;
//    }

    public static final String getPort(String serverAddress){
        String url = null;
        if(!TextUtils.isEmpty(serverAddress)){
            String server = getServerHost();
            url = server + serverAddress;
        }
        return url;
    }


    public  class Notice{
        public final static String START_PAGE="startPage";
        public final static String EVERY_PAGE="everyPage";
    }

    public class NoticeDetail{
        public final static  String ID = "id";
    }

    public static class TodayFeedback{
        public final static String START_PAGE="startPage";
        public final static String EVERY_PAGE="everyPage";
        public final static String TYPE = "type";
    }

    public static class MoDifiedDetail{
        public final static String ID = "id";
    }

    public static class MyFeedback{
        public final static String START_PAGE = "startPage";
        public final static String EVERY_PAGE = "everyPage";
        public final static String ID = "id";
        public final static String TYPE = "type";

    }

}
