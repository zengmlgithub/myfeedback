package com.sanyedu.myfeedback.mvpimpl.modifypwd;

import android.support.annotation.NonNull;

import com.sanyedu.myfeedback.mvp.BasePresenter;

public class ModifyPwdPresenter extends BasePresenter<ModifyPwdContacts.IModifyPwdUI> implements ModifyPwdContacts.IModifyPwdPresenter {
    public ModifyPwdPresenter(@NonNull ModifyPwdContacts.IModifyPwdUI view) {
        super(view);
    }
}
