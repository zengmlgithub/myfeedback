package com.sanyedu.feedback.utils;

/**
 * 常量管理类
 * <p>
 * Created by 邹峰立 on 2018/3/5.
 */
public class ConstantUtil {
    /**
     * 读取手机状态权限
     */
    public final static int PERMISSIONS_REQUEST_READ_PHONE_STATE = 211;

    /**
     * 字体缩放比例
     */
    public final static int TEXTVIEWSIZE = 1;

    public final static int TIMEMILL = 5000 ;

    public final static String TOKEN = "token";

    public final static String AUTHORIZATION = "Authorization";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String TEL = "tel" ;

    //sp---个人信息
    public static final String USERINFO = "userinfo";

    public static final String FK_STATE_SUBMITED = "1";  //已提交
    public static final String FK_STATE_CHECKED = "2"; //已审核
    public static final String FK_STATE_WATIIING_MODIFED = "3";
    public static final String FK_STATE_FINISHED = "4";
    public static final String FK_STATE_NULL = "0";
}
