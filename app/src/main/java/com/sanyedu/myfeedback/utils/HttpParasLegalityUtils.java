package com.sanyedu.myfeedback.utils;

import android.text.TextUtils;
import com.sanyedu.myfeedback.log.SanyLogs;

import java.lang.reflect.Field;

public class HttpParasLegalityUtils {
    /**
     * 判断参数是否为空，如果为空，则返回非法
     *
     * @param params
     * @return
     */
    public static boolean isParasLegality(String... params) {
        boolean isLegality = true;
        for (String para : params) {
            if (TextUtils.isDigitsOnly(para)) {
                isLegality = false;
                SanyLogs.e("param is legacy ,return.");
            }
        }

        return isLegality;
    }

    public static boolean isAllObjFieldLegacity(Object obj) throws Exception {
        Class stuCla = (Class) obj.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(obj);// 得到此属性的值
            if (val == null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }
}
