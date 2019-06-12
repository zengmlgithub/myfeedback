package com.sanyedu.myfeedback.utils;

import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.share.SpHelper;

public class UserInfoHelper {
    public static String getPersonId(){
        TeacherBean bean = SpHelper.getObj(ConstantUtil.USERINFO);
        String tempId = "";
        if(bean != null){
            tempId = bean.getId();
        }

        return tempId;
    }

    public static String getPersonName() {
        TeacherBean bean = SpHelper.getObj(ConstantUtil.USERINFO);
        String tempName = "";
        if(bean != null){
            tempName = bean.getTeName();
        }
        return tempName;
    }

    public static String getPersonDept(){
        TeacherBean bean = SpHelper.getObj(ConstantUtil.USERINFO);
        String tempDept = "";
        if(bean != null){
            tempDept = bean.getTeDept();
        }
        return tempDept;
    }

}
