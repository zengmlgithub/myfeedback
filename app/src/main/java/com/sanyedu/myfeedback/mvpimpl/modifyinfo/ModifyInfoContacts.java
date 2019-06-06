package com.sanyedu.myfeedback.mvpimpl.modifyinfo;

import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

public final class ModifyInfoContacts {
    public interface IModifyInfoUI extends IBaseView{
       void showModifySuccess(); //修改成功后需要重新获取个人数据
    }

    public interface IModifyInfoPresenter extends IBasePresenter{
        void ModifyObj(String type,String obj);
    }
}
