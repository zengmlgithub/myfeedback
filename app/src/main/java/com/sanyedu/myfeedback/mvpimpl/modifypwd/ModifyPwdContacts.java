package com.sanyedu.myfeedback.mvpimpl.modifypwd;

import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

/**
 * 修改密码接口
 */
public final class ModifyPwdContacts {
    public interface IModifyPwdUI extends IBaseView{
        void showSuccess();
    }

    public interface IModifyPwdPresenter extends IBasePresenter{
        void modifyPwd(String type,String id,String userName,String oldpwd,String newPwd);
    }
}
