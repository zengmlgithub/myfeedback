package com.sanyedu.feedback.mvpimpl.notice;

import com.sanyedu.feedback.model.NoticeBean;
import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

import java.util.ArrayList;

public final class NoticeContacts {
    public interface INoticeUI extends IBaseView {
        public void setNotices(ArrayList<NoticeBean> notices);
    }

    public interface INoticePresenter extends IBasePresenter{
        public void getNotices();
    }
}
