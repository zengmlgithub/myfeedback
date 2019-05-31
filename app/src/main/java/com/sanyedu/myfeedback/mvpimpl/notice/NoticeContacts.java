package com.sanyedu.myfeedback.mvpimpl.notice;

import com.sanyedu.myfeedback.model.NoticeBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.ArrayList;

public final class NoticeContacts {
    public interface INoticeUI extends IBaseView {
        public void setNotices(ArrayList<NoticeBean> notices);
    }

    public interface INoticePresenter extends IBasePresenter{
        public void getNotices();
    }
}
