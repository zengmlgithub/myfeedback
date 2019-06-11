package com.sanyedu.myfeedback.mvpimpl.modifychange;

import android.support.annotation.NonNull;
import com.sanyedu.myfeedback.mvp.BasePresenter;

public class ModifyChangePresenter extends BasePresenter<ModifyChangeContacts.IModifyChangeUI> implements ModifyChangeContacts.IModifyChangePresenter {
    public ModifyChangePresenter(@NonNull ModifyChangeContacts.IModifyChangeUI view) {
        super(view);
    }
}
